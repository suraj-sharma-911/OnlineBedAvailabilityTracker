package com.inkathon.OnlineBedAvailabilityTracker.dto;

public class AddressDto {

	private long hospitalId;
	private String addressLine;
	private long cityId;
	private double latitude;
	private double longitude;
	private CityDto city;

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

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

}
