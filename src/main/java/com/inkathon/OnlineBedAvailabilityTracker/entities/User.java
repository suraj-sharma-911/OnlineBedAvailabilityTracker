package com.inkathon.OnlineBedAvailabilityTracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity //hibernate
@Table(name="USER_TABLE") //table in our db
public class User {
	
	@Id
	@SequenceGenerator(
			name="user_sequence",
			sequenceName="user_sequence",
			allocationSize=1
			)
	
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator = "user_sequence"
			)
	
	@Column(name="USER_ID")
	private Long userId;
	@Column(name="IS_ADMIN")
	private boolean isAdmin;
	@Column(name="USERNAME")
	private String username;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="EMAIL")
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User() {
		super();
	}
	public User(Long userId, boolean isAdmin, String username, String password,String email) {
		super();
		this.userId = userId;
		this.isAdmin = isAdmin;
		this.username = username;
		this.password = password;
		
		this.email=email;
	}
	public User(boolean isAdmin, String username, String password,String email) {
		super();
		this.isAdmin = isAdmin;
		this.username = username;
		this.password = password;
		this.email=email;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", isAdmin=" + isAdmin + ", username=" + username + ", password=" + password
				+ ", email=" + email + "]";
	}
	
	

}
