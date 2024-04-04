package com.example.timesheet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.timesheet.pojo.AdminDashboardResponse;
import com.example.timesheet.repository.EmployeeMasterRepository;

@Service
public class EmployeeMasterService {
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	public List<AdminDashboardResponse> fetchUserRegistrationData() {
		System.out.println("Inside EmployeeMasterService class fetchUserRegistrationData() method");
		List<Object[]> result = null;
		ArrayList<AdminDashboardResponse> returnList = new ArrayList<AdminDashboardResponse>();
		result = employeeMasterRepository.getUserRegistrationData();
		try {
			result.stream().forEach((record) -> {
				AdminDashboardResponse adminDashboardResponse =  new AdminDashboardResponse();
				adminDashboardResponse.setUserId((String) record[0]);
				adminDashboardResponse.setResourceName((String) record[1]);
				adminDashboardResponse.setAbhiManager((String) record[2]);
				adminDashboardResponse.setSmtManager((String) record[3]);
				adminDashboardResponse.setIsOnroll((boolean) record[4]);
				returnList.add(adminDashboardResponse);

			});
		} catch (Exception e) {
			System.out.println("Error occured while fetching user registration data");
			System.out.println(e.getMessage());
		}

		return returnList;
	}
	
	public List<AdminDashboardResponse> fetchDateWiseTimesheetData(String fromDate) {
		System.out.println("Inside EmployeeMasterService class fetchDateWiseTimesheetData() method");
		List<Object[]> result = null;
		ArrayList<AdminDashboardResponse> returnList = new ArrayList<AdminDashboardResponse>();
		result = employeeMasterRepository.getDateWiseTimesheetData(fromDate);
		try {
			result.stream().forEach((record) -> {
				AdminDashboardResponse adminDashboardResponse =  new AdminDashboardResponse();
				adminDashboardResponse.setUserId((String) record[0]);
				adminDashboardResponse.setResourceName((String) record[1]);
				adminDashboardResponse.setAbhiManager((String) record[2]);
				adminDashboardResponse.setSmtManager((String) record[3]);
				adminDashboardResponse.setIsOnroll((boolean) record[4]);
				returnList.add(adminDashboardResponse);

			});
		} catch (Exception e) {
			System.out.println("Error occured while fetching dashboard timesheet data");
			System.out.println(e.getMessage());
		}

		return returnList;
	}


}
