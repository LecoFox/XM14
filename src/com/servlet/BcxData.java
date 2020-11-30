package com.servlet;

import java.text.*;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

import com.utils.DataBaseUtil;

import org.json.JSONArray;
import org.json.JSONException;
import java.net.*;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BcxData extends HttpServlet {
	Statement statement;
	Connection conn = null;
	Cookie[] cookies;
	String loginSessionId = null;
	String randTID2 = null;
	String randTIDkey = null;
	Timer timer = new Timer();
	HttpServletRequest m_request;
	HttpServletResponse m_response;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF=8");
		response.setContentType("text/html;charset=UTF-8");

		conn = DataBaseUtil.getConn();
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 清空bcx_data表
		try {
			this.truncate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.m_request = request;
		this.m_response = response;

		// 访问伴车星,获取数据
		this.runTimer();
		System.out.println("runTimer");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// 清空bcx_data表
	public void truncate() throws SQLException {
		String sql = "TRUNCATE TABLE bcx_data";
		statement.executeUpdate(sql);
		System.out.println("清空bcx_data表");
	}

	// 定时获取数据
	public void runTimer() {
		this.timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				HttpURLConnection urlConn = null;
				URL url = null;
				try {
					url = new URL("http://www.bcxgps.com/page/vip/showTerminal.action?"
							+ "currentPage=1&type=1&pageCount=1&search=1&startType=1&line=on");
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				try {
					urlConn = (HttpURLConnection) url.openConnection();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (loginSessionId != null) {
					urlConn.setRequestProperty("Cookie", loginSessionId);
					urlConn.setRequestProperty("first", "first");
					urlConn.setRequestProperty("rdMap", "rdBMap");
				}

				try {
					urlConn.setRequestMethod("GET");
				} catch (ProtocolException e2) {
					e2.printStackTrace();
				}
				System.out.println("Method: " + urlConn.getRequestMethod());
				System.out.println("Cookie: " + urlConn.getRequestProperty("Cookie"));
				try {
					urlConn.connect();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				if (loginSessionId != null && randTID2 == null && randTIDkey == null) {
					String cookieVal = null;
					String key;
					for (int i = 1; (key = urlConn.getHeaderFieldKey(i)) != null; i++) {
						if (key.equalsIgnoreCase("set-cookie")) {
							cookieVal = urlConn.getHeaderField(i);
							cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
							System.out.println("set-cookie..." + cookieVal);
							loginSessionId += ";" + cookieVal;
						}
					}
					randTID2 = "get randTID2...";
					randTIDkey = "get randTIDkey...";
					System.out.println("set-cookie loginSessionId: " + loginSessionId);
				} else {
					System.out.println("Already set-cookie loginSessionId: " + loginSessionId);
				}

				try {
					if (urlConn.getResponseCode() == 200) {
						System.out.println("Connect succeed");

					} else {
						System.out.println("Connect failed");
					}
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				int length = urlConn.getContentLength();
				StringBuffer sb = new StringBuffer("");
				if (length > 0) {
					// System.out.println("length > 0");
					BufferedReader br;
					try {
						br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
						String line;
						try {
							while ((line = br.readLine()) != null) {
								sb.append(line);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						System.out.println(sb.toString());
						JSONObject j = new JSONObject(sb.toString());
						JSONArray l = j.getJSONArray("list");
						if (l.length() == 0) {
							System.out.println("log in");
							// 重新登录,清空表
							try {
								truncate();
							} catch (SQLException e) {
								e.printStackTrace();
							}
							URL urlLogin = null;
							try {
								urlLogin = new URL("http://www.bcxgps.com/page/login/BcxLoad.action?r=g");
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							}
							try {
								urlConn = (HttpURLConnection) urlLogin.openConnection();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							try {
								urlConn.setRequestMethod("GET");
							} catch (ProtocolException e2) {
								e2.printStackTrace();
							}
							try {
								urlConn.connect();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							String sessionValue = urlConn.getHeaderField("Set-Cookie");
							// System.out.println("sessionValue: " +
							// sessionValue);
							String[] sessionId = sessionValue.split(";");
							loginSessionId = sessionId[0];
						} else {
							System.out.println("Already log in");
							save_data(l);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("length == 0");
				}

				urlConn.disconnect();
			}
		}, 0, 5000);
	}

	// 保存到数据库
	public void save_data(JSONArray l) {
		try {
			for (int i = 0; i < l.length(); i++) {
				JSONObject jj;
				jj = (JSONObject) l.get(i);
				String deviceId = jj.getString("sn");
				String gpsTime = jj.getString("gpstime");
				String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
				String recvTime = nowTime;
				String lon = jj.getString("lon");
				String lat = jj.getString("lat");
				String oldlon = jj.getString("oldlon");
				String oldlat = jj.getString("oldlat");
				String direction = jj.getString("dir");
				String location = jj.getString("address");
				String speed = jj.getString("spe");
				String start = jj.getString("start");
				String carname = jj.getString("tname");
				String carimg = "carImg.png";
				String warningimg = "warningImg.png";
				int speed1 = 0;
				try {
				    speed1 = Integer.parseInt(speed);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				String sql = "insert into bcx_data(Device_id, GPS_time, Rev_time, Lon, Lat, oldLon, oldLat, Direction, Location, Speed, Start, car_name, carImg)";
				if(speed1 > 60){
					carimg = warningimg;
				}
				sql = sql + "values('" + deviceId + "','" + gpsTime + "','" + recvTime + "','" + lon + "','" + lat
						+ "','" + oldlon + "','" + oldlat + "','" + direction + "','" + location + "','" + speed + "','"
						+ start + "','" + carname + "','" + carimg + "')";
				// System.out.println("name:" + carname + " sn:" + deviceId + "
				// lon:" + lon + " lat:" + lat);
				try {
					statement.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
