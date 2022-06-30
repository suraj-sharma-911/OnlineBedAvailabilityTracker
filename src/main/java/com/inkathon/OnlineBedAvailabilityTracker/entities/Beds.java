package com.inkathon.OnlineBedAvailabilityTracker.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BEDS_TABLE")
public class Beds {

	@EmbeddedId
	private BedId bedId;

	@Column(name = "TOTAL_BEDS")
	private Long totalBeds;
	@Column(name = "OCCUPIED_BEDS")
	private Long occupiedBeds;
	
	@Column(name = "BUDGET")
	private Double budget;

	public Beds() {

	}

	public BedId getBedId() {
		return bedId;
	}

	public void setBedId(BedId bedId) {
		this.bedId = bedId;
	}

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

	public Beds(BedId bedId, Long totalBeds, Long occupiedBeds) {
		super();
		this.bedId = bedId;
		this.totalBeds = totalBeds;
		this.occupiedBeds = occupiedBeds;
	}

	public Beds(Long totalBeds, Long occupiedBeds) {
		super();
		this.totalBeds = totalBeds;
		this.occupiedBeds = occupiedBeds;
	}
	
	

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "Beds [bedId=" + bedId + ", totalBeds=" + totalBeds + ", occupiedBeds=" + occupiedBeds + ", budget="
				+ budget + "]";
	}

	

}
