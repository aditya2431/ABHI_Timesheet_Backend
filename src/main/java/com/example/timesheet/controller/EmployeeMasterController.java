package com.example.timesheet.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.example.timesheet.pojo.GetTimeResponse;
import com.example.timesheet.pojo.ManagerDashboardResponse;
import com.example.timesheet.pojo.MissingDatesResponse;
import com.example.timesheet.pojo.UserDashboardResponse;
import com.example.timesheet.pojo.UserNameAndYesterdayResponse;
import com.example.timesheet.repository.EmployeeMasterRepository;
import com.example.timesheet.service.EmployeeMasterService;

@RestController
@RequestMapping("/api")
public class EmployeeMasterController {
	
	private static final Logger logger = LogManager.getLogger(EmployeeMasterController.class);

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
		logger.info("Inside EmployeeMasterController fetchUserRegistrationData() method");
		return employeeMasterService.fetchUserRegistrationData();
	}
	
	@GetMapping({ "/getDateWiseTimesheetData/{date}" })
	public List<AdminDashboardResponse> getDateWiseTimesheetData(@PathVariable(value = "date") String fromDate) {
		logger.info("Inside EmployeeMasterController fetchUserRegistrationData() method");
		return employeeMasterService.fetchDateWiseTimesheetData(fromDate);
	}
	@GetMapping({ "/getManagerWiseTimesheetData/{manager}/{fromDate}/{toDate}" })
	public List<ManagerDashboardResponse> getManagerWiseTimesheetData(@PathVariable(value = "manager") String manager,
			@PathVariable(value = "fromDate") String fromDate, @PathVariable(value = "toDate") String toDate) {
		logger.info("Inside EmployeeMasterController fetchManagerRegistrationData() method");
		return employeeMasterService.fetchManagerWiseTimesheetData(manager,fromDate,toDate);
	}
	
	@GetMapping({ "/getUserDashboard/{name}" })
	public List<UserDashboardResponse> getUserDashboard(@PathVariable(value = "name") String name) {
		logger.info("Inside EmployeeMasterController fetchUserDashboard() method");
		return employeeMasterService.fetchUserDashboard(name);
	}
	
	@GetMapping({ "/getTotalTime/{name}" })
	public List<GetTimeResponse> getTotalTime(@PathVariable(value = "name") String name) {
		logger.info("Inside EmployeeMasterController getTotalTime() method");
		return employeeMasterService.getTotalTime(name);
	}
	@GetMapping({ "/getMissingDate/{name}" })
	public List<MissingDatesResponse> getMissingDate(@PathVariable(value = "name") String name) {
		logger.info("Inside EmployeeMasterController getMissingDate() method");
		return employeeMasterService.getMissingDates(name);
	}
	@GetMapping({ "/getYesterdayTimesheet/{name}" })
	public List<UserNameAndYesterdayResponse> getYesterdayTimesheet(@PathVariable(value = "name") String name) {
		logger.info("Inside EmployeeMasterController getMissingDate() method");
		return employeeMasterService.getYesterdayTimesheet(name);
	}
}
