package com.example.timesheet.pojo;

import java.util.Date;

public class UserNameAndYesterdayResponse {
	
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
	
	
}
