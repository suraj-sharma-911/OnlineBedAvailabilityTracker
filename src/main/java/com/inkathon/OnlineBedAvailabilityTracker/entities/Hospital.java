package com.inkathon.OnlineBedAvailabilityTracker.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "HOSPITAL_TABLE")
public class Hospital {
	public Hospital() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "HOSPITAL_ID")
	private long hospitalId;
	@Column(name = "HOSPITAL_NAME")
	private String hospitalName;
	@Column(name = "CONTACT_NUMBER")
	private long contactNumber;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "OWNERSHIP")
	private String ownership;
	@Column(name = "CATEGORY")
	private String category;
	@Column(name = "LAST_UPDATED") // , insertable = false, updatable = false
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;
	@Column(name = "USER_ID")
	private long userId;

	public Hospital(String hospitalName, long contactNumber, String email, String ownership, String category,
			Date lastUpdated) {
		super();
		this.hospitalName = hospitalName;
		this.contactNumber = contactNumber;
		this.email = email;
		this.ownership = ownership;
		this.category = category;
		this.lastUpdated = lastUpdated;
	}

	public Hospital(long hospitalId, String hospitalName, long contactNumber, String email, String ownership,
			String category, Date lastUpdated) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.contactNumber = contactNumber;
		this.email = email;
		this.ownership = ownership;
		this.category = category;
		this.lastUpdated = lastUpdated;
	}

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

	@Override
	public String toString() {
		return "Hospital [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", contactNumber="
				+ contactNumber + ", email=" + email + ", ownership=" + ownership + ", category=" + category
				+ ", lastUpdated=" + lastUpdated + ", userId=" + userId + "]";
	}

}
