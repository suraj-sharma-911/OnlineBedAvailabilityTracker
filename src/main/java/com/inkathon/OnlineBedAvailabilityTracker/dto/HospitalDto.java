package com.inkathon.OnlineBedAvailabilityTracker.dto;

import java.util.Date;
import java.util.List;

public class HospitalDto {
	private long hospitalId;
	private String hospitalName;
	private long contactNumber;
	private String email;
	private String ownership;
	private String category;
	private Date lastUpdated;
	private long userId;
	private AddressDto addressDto;
	private List<BedsDto> bedsListDto;

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	public List<BedsDto> getBedsListDto() {
		return bedsListDto;
	}

	public void setBedsListDto(List<BedsDto> bedsListDto) {
		this.bedsListDto = bedsListDto;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
