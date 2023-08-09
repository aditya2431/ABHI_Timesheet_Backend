package com.example.timesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
//import java.util.Date;

@Entity
@Table(name = "cr_details")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@JsonIgnoreProperties(allowGetters = true)
public class CRDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String wbsCode;
    
   @NotBlank
    private String abhiManager;

    @NotBlank
    private String partnerManager;

    @NotBlank
    private String vendorName;

    @NotBlank
    private String appName;

    @NotBlank
    private String taskID;

    @NotBlank
    private String description;
    
    @NotBlank
    private String assignedTo;
    
    @NotBlank
    private String startDate;
    
    @NotBlank
    private String endDate;
    
    @NotBlank
    private String estimatedEfforts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWbsCode() {
		return wbsCode;
	}

	public void setWbsCode(String wbsCode) {
		this.wbsCode = wbsCode;
	}

	public String getAbhiManager() {
		return abhiManager;
	}

	public void setAbhiManager(String abhiManager) {
		this.abhiManager = abhiManager;
	}

	public String getPartnerManager() {
		return partnerManager;
	}

	public void setPartnerManager(String partnerManager) {
		this.partnerManager = partnerManager;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEstimatedEfforts() {
		return estimatedEfforts;
	}

	public void setEstimatedEfforts(String estimatedEfforts) {
		this.estimatedEfforts = estimatedEfforts;
	}
    
   
    
 
    
    
}