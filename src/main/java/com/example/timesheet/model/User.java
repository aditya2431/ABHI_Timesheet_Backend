package com.example.timesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "user_details")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class User {
    
	@Id
    @NotBlank
    private String userName;
	@NotBlank
    private String emailId;
    @NotBlank
    private String password;
    private boolean isAdmin;
    @NotBlank
    private String abhiManager;
    @NotBlank
    private String abhiManagerName;
    
    private String partnerManager;
	

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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbhiManagerName() {
		return abhiManagerName;
	}

	public void setAbhiManagerName(String abhiManagerName) {
		this.abhiManagerName = abhiManagerName;
	}

}
