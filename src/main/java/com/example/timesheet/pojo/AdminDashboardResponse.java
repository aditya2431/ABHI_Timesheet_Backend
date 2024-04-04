package com.example.timesheet.pojo;

public class AdminDashboardResponse {
	
	public String userId;
	public String resourceName;
	public String abhiManager;
	public String smtManager;
	public boolean isOnroll;
	
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
	public void setIsOnroll(boolean isOnroll) {
		this.isOnroll = isOnroll;
	}

}
