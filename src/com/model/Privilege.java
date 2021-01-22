package com.model;

/**
 * 
 * @author ����������
 *
 *         Ȩ�ޱ�
 */
public class Privilege {
	/**
	 * @param id
	 *            ���
	 * @param name
	 *            ����
	 * @param url
	 *            �����·��
	 * @param type
	 *            ���ͣ�1�ǲ˵���2�ǰ�ť
	 * @param orderNum
	 *            ����
	 * @param percode
	 *            ��ť��ʶ
	 * @param icon
	 *            ͼ��
	 * @param pid
	 *            �ϼ�Ȩ�ޱ��
	 */
	private Integer id;
	private String name;
	private String url;
	private Integer type;
	private Integer orderNum;
	private String percode;
	private String icon;
	private long pid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getPercode() {
		return percode;
	}

	public void setPercode(String percode) {
		this.percode = percode;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", name=" + name + ", url=" + url + ", icon=" + icon + ", pid=" + pid + "]";
	}

}
