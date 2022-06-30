package com.inkathon.OnlineBedAvailabilityTracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS_TABLE")
public class Address {

	// I am making address id to be hospital id as there will be 1 address for
	// each
	// hospital id

	@Id
	private long hospitalId; // hospitalId = hospital.hospital

	// @OneToOne(cascade = CascadeType.ALL, optional = false)
	// @JoinColumn(name = "HOSPITAL_ID")
	// @MapsId
	// private Hospital hospital;

	@Column(name = "ADDRESS_LINE")
	private String addressLine;
	@Column(name = "CITY_ID")
	private long cityId;
	@Column(name = "LATITUDE")
	private double latitude;
	@Column(name = "LONGITUDE")
	private double longitude;

	// constructor
	public Address() {

	}

	public Address(String addressLine, long cityId, double latitude, double longitude) {
		super();
		this.addressLine = addressLine;
		this.cityId = cityId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Address(long hospitalId, String addressLine, long cityId, double latitude, double longitude) {
		super();
		this.hospitalId = hospitalId;
		this.addressLine = addressLine;
		this.cityId = cityId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long gethospitalId() {
		return hospitalId;
	}

	public void sethospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Address [hospitalId=" + hospitalId + ", addressLine=" + addressLine + ", cityId=" + cityId
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
