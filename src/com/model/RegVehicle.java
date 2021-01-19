package com.model;

public class RegVehicle {
	private String device_id;
	//private String carImg;
	private String owner;
	private String chepai;
	private String brand;
	private String model;
	private String engine_id;
	private String driver_id;
	
	public String getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getChepai() {
		return chepai;
	}
	public void setChepai(String chepai) {
		this.chepai = chepai;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getEngine_id() {
		return engine_id;
	}
	public void setEngine_id(String engine_id) {
		this.engine_id = engine_id;
	} 
	
	@Override
	public String toString() {
		return "RegVehicle [device_id=" + device_id + ", owner=" + owner + "]";
	}

	public RegVehicle(String device_id, String owner, String chepai, String brand, String model, String engine_id, String driver_id) {
		super();
		this.device_id = device_id;
		//this.carImg = carImg;
		this.owner = owner;
		this.chepai = chepai;
		this.brand = brand;
		this.model = model;
		this.engine_id = engine_id;
		this.driver_id = driver_id;
	}

	public RegVehicle() {
		super();
	}
}
