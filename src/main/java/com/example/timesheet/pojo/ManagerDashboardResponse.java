package com.example.timesheet.pojo;

import java.util.Date;

public class ManagerDashboardResponse {
	public String userId;
	public String resourceName;
	public String abhiManager;
	public String smtManager;
	public boolean isOnroll;
	public String category;
	public String comments;
	public String bookedEfforts;
	public Date createdAt;
	public String wbsCode;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getBookedEfforts() {
		return bookedEfforts;
	}
	public void setBookedEfforts(String bookedEfforts) {
		this.bookedEfforts = bookedEfforts;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getWbsCode() {
		return wbsCode;
	}
	public void setWbsCode(String wbsCode) {
		this.wbsCode = wbsCode;
	}

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getAbhiManager() {
		return abhiManager;
	}
	public void setAbhiManager(String abhiManager) {
		this.abhiManager = abhiManager;
	}
	public String getSmtManager() {
		return smtManager;
	}
	public void setSmtManager(String smtManager) {
		this.smtManager = smtManager;
	}
	public boolean isOnroll() {
		return isOnroll;
	}
	public void setOnroll(boolean isOnroll) {
		this.isOnroll = isOnroll;
	}
	
	
}
