package com.inkathon.OnlineBedAvailabilityTracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CITYTABLE")
public class City {
	@Id
	@Column(name = "CITY_ID")
	private long cityId;
	@Column(name = "CITY_NAME")
	private String cityName;
	@Column(name = "STATE")
	private String state;
	public City() {
	}
	public City(String cityName, String state) {
		super();
		this.cityName = cityName;
		this.state = state;
	}

	public City(int cityId, String cityName, String state) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.state = state;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
