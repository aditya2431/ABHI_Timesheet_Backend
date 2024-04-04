package com.example.timesheet.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee_master_data")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@JsonIgnoreProperties(allowGetters = true)
public class EmployeeMaster {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SeqNo;
	private String resourceName;
	private String userId;
	private String emailId;
	private String mobileNo;
	private String userRole;
	private String applicationName;
	private String vendorName;
	private String abhiManager;	
	private String abhiManagerId;
	private String smtManager;
	private String smtId; 
	private boolean isonroll;
	public int getSeqNo() {
		return SeqNo;
	}
	public void setSeqNo(int seqNo) {
		SeqNo = seqNo;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getAbhiManager() {
		return abhiManager;
	}
	public void setAbhiManager(String abhiManager) {
		this.abhiManager = abhiManager;
	}
	public String getAbhiManagerId() {
		return abhiManagerId;
	}
	public void setAbhiManagerId(String abhiManagerId) {
		this.abhiManagerId = abhiManagerId;
	}
	public String getSmatManager() {
		return smtManager;
	}
	public void setSmatManager(String smtManager) {
		this.smtManager = smtManager;
	}
	public String getSmtId() {
		return smtId;
	}
	public void setSmtId(String smtId) {
		this.smtId = smtId;
	}
	public boolean isIsonroll() {
		return isonroll;
	}
	public void setIsonroll(boolean isonroll) {
		this.isonroll = isonroll;
	}

}
