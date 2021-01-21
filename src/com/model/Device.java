package com.model;

public class Device{
	private String device_id;
	private String username;

	public String getDeviceId() {
		return device_id;
	}

	public void setDeviceId(String device_id) {
		this.device_id = device_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public Device(String device_id, String username) {
		super();
		this.device_id = device_id;
		this.username = username;
	}

	public Device() {
		super();
	}

}

