package com.example.timesheet.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee_punchin_details")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@JsonIgnoreProperties(allowGetters = true)
public class PunchInDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SeqNo;
	@NotBlank
	private String userName;
	@NotBlank
	private String userId;
	@NotNull
	private Date punchInTime;
	
	public int getSeqNo() {
		return SeqNo;
	}
	public void setSeqNo(int seqNo) {
		SeqNo = seqNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getPunchInTime() {
		return punchInTime;
	}
	public void setPunchInTime(Date punchInTime) {
		this.punchInTime = punchInTime;
	}
	
}