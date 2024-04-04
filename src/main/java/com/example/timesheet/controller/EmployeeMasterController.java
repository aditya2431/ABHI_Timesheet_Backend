package com.example.timesheet.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.timesheet.exception.ResourceNotFoundException;
import com.example.timesheet.model.EmployeeMaster;
import com.example.timesheet.pojo.AdminDashboardResponse;
import com.example.timesheet.repository.EmployeeMasterRepository;
import com.example.timesheet.service.EmployeeMasterService;

@RestController
@RequestMapping("/api")
public class EmployeeMasterController {

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Autowired
	EmployeeMasterService employeeMasterService;

	@GetMapping("/getAllEmployees")
	public List<EmployeeMaster> getAllRecords() {
		return employeeMasterRepository.findAll();
	}

	@PostMapping("/saveEmployee")
	public EmployeeMaster createRecord(@Valid @RequestBody EmployeeMaster employeeMaster) {
		return employeeMasterRepository.save(employeeMaster);
	}

	@GetMapping("/findEmpById/{id}")
	public List<EmployeeMaster> getRecordById(@PathVariable(value = "id") String userName) {
		return employeeMasterRepository.findByUserId(userName);
	}
	
	@GetMapping("/findEmpByAbhiManager/{id}")
	public List<EmployeeMaster> getRecordByAbhiManager(@PathVariable(value = "id") String abhiManager) {
		return employeeMasterRepository.findByabhiManagerId(abhiManager);
	}
	
	@GetMapping({ "/fetchUserRegistrationData" })
	public List<AdminDashboardResponse> fetchUserRegistrationData() {
		System.out.println("Inside EmployeeMasterController fetchUserRegistrationData() method");
		return employeeMasterService.fetchUserRegistrationData();
	}
	
	@GetMapping({ "/getDateWiseTimesheetData/{date}" })
	public List<AdminDashboardResponse> getDateWiseTimesheetData(@PathVariable(value = "date") String fromDate) {
		System.out.println("Inside EmployeeMasterController fetchUserRegistrationData() method");
		return employeeMasterService.fetchDateWiseTimesheetData(fromDate);
	}
}
