package com.model;

public class RegVehicle {
	private String device_id;
	private String carImg;
	private String owner;
	private String chepai;
	private String brand;
	private String model;
	private String engine_id;
	
	public String getCarImg() {
		return carImg;
	}
	public void setCarImg(String carImg) {
		this.carImg = carImg;
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
		return "RegVehicle [device_id=" + device_id + ", carImg=" + carImg + ", owner=" + owner + "]";
	}

	public RegVehicle(String device_id, String carImg, String owner, String chepai, String brand, String model, String engine_id) {
		super();
		this.device_id = device_id;
		this.carImg = carImg;
		this.owner = owner;
		this.chepai = chepai;
		this.brand = brand;
		this.model = model;
		this.engine_id = engine_id;
	}

	public RegVehicle() {
		super();
	}
}
