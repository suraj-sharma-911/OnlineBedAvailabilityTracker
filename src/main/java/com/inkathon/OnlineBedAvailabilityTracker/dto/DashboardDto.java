package com.inkathon.OnlineBedAvailabilityTracker.dto;

public class DashboardDto {
	private long totalHospitals;
	private String bedType;
	private long totalBeds;
	private long occupiedBeds;
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public long getTotalBeds() {
		return totalBeds;
	}
	public void setTotalBeds(long totalBeds) {
		this.totalBeds = totalBeds;
	}
	public long getOccupiedBeds() {
		return occupiedBeds;
	}
	public void setOccupiedBeds(long occupiedBeds) {
		this.occupiedBeds = occupiedBeds;
	}
	public long getTotalHospitals() {
		return totalHospitals;
	}
	public void setTotalHospitals(long totalHospitals) {
		this.totalHospitals = totalHospitals;
	}
	
}
