package com.inkathon.OnlineBedAvailabilityTracker.dto;

import com.inkathon.OnlineBedAvailabilityTracker.entities.BedId;

public class BedsDto {
//	private Long hospitalId;
//	private String bedType;
	private BedId bedId;
	private Long totalBeds;
	private Long occupiedBeds;
	private Double budget;


//	public Long getHospitalId() {
//		return hospitalId;
//	}
//
//	public void setHospitalId(Long hospitalId) {
//		this.hospitalId = hospitalId;
//	}
//
//	public String getBedType() {
//		return bedType;
//	}
//
//	public void setBedType(String bedType) {
//		this.bedType = bedType;
//	}

	public Long getTotalBeds() {
		return totalBeds;
	}

	public void setTotalBeds(Long totalBeds) {
		this.totalBeds = totalBeds;
	}

	public Long getOccupiedBeds() {
		return occupiedBeds;
	}

	public void setOccupiedBeds(Long occupiedBeds) {
		this.occupiedBeds = occupiedBeds;
	}

	public BedId getBedId() {
		return bedId;
	}

	public void setBedId(BedId bedId) {
		this.bedId = bedId;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

}
