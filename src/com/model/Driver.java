package com.model;

import java.util.Date;

public class Driver {
	//private int id;
	private String name;
	private String driver_id;
	private String sex;
	private String birthday;
	private String validity_period;
	private String phone_number;
	//private String engine_id;
/*
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDriverId() {
		return driver_id;
	}

	public void setDriverId(String driver_id) {
		this.driver_id = driver_id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getValidityPeriod() {
		return validity_period;
	}

	public void setValidityPeriod(String validity_period) {
		this.validity_period = validity_period;
	}

	public String getPhoneNumber() {
		return phone_number;
	}

	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}
	//public String getEngineId() {
	//	return engine_id;
	//}

	//public void setEngineId(String engine_id) {
	//	this.engine_id = engine_id;
	//}

	@Override
	public String toString() {
		//return "User [id=" + id + ", username=" + name + ", driver_id=" + driver_id + "]";
		return "User [username=" + name + ", driver_id=" + driver_id + "]";
	}

	public Driver(String name, String driver_id, String sex, String birthday, String validity_period, String phone_number) {
		super();
		this.name = name;
		this.driver_id = driver_id;
		this.sex = sex;
		this.birthday = birthday;
		this.validity_period = validity_period;
		this.phone_number = phone_number;
		//this.engine_id = engine_id;
	}

	public Driver() {
		super();
	}

}

