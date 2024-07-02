package com.example.timesheet.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.timesheet.controller.EmployeeMasterController;
import com.example.timesheet.pojo.AdminDashboardResponse;
import com.example.timesheet.pojo.GetTimeResponse;
import com.example.timesheet.pojo.ManagerDashboardResponse;
import com.example.timesheet.pojo.MissingDatesResponse;
import com.example.timesheet.pojo.UserDashboardResponse;
import com.example.timesheet.pojo.UserNameAndYesterdayResponse;
import com.example.timesheet.repository.EmployeeMasterRepository;

@Service
public class EmployeeMasterService {

	private static final Logger logger = LogManager.getLogger(EmployeeMasterService.class);

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	public List<AdminDashboardResponse> fetchUserRegistrationData() {
		logger.info("Inside EmployeeMasterService class fetchUserRegistrationData() method");
		List<Object[]> result = null;
		ArrayList<AdminDashboardResponse> returnList = new ArrayList<AdminDashboardResponse>();
		result = employeeMasterRepository.getUserRegistrationData();
		try {
			result.stream().forEach((record) -> {
				AdminDashboardResponse adminDashboardResponse = new AdminDashboardResponse();
				adminDashboardResponse.setUserId((String) record[0]);
				adminDashboardResponse.setResourceName((String) record[1]);
				adminDashboardResponse.setAbhiManager((String) record[2]);
				adminDashboardResponse.setSmtManager((String) record[3]);
				adminDashboardResponse.setIsOnroll((boolean) record[4]);
				returnList.add(adminDashboardResponse);

			});
		} catch (Exception e) {
			logger.info("Error occured while fetching UserRegistrationData data");
			logger.info(e.getMessage());
		}

		return returnList;
	}

	public List<AdminDashboardResponse> fetchDateWiseTimesheetData(String fromDate) {
		logger.info("Inside EmployeeMasterService class fetchDateWiseTimesheetData() method");
		List<Object[]> result = null;
		ArrayList<AdminDashboardResponse> returnList = new ArrayList<AdminDashboardResponse>();
		result = employeeMasterRepository.getDateWiseTimesheetData(fromDate);
		try {
			result.stream().forEach((record) -> {
				AdminDashboardResponse adminDashboardResponse = new AdminDashboardResponse();
				adminDashboardResponse.setUserId((String) record[0]);
				adminDashboardResponse.setResourceName((String) record[1]);
				adminDashboardResponse.setAbhiManager((String) record[2]);
				adminDashboardResponse.setSmtManager((String) record[3]);
				adminDashboardResponse.setIsOnroll((boolean) record[4]);
				returnList.add(adminDashboardResponse);

			});
		} catch (Exception e) {
			logger.info("Error occured while fetching dateWiseTimesheetData data");
			logger.info(e.getMessage());
		}

		return returnList;
	}

	public List<ManagerDashboardResponse> fetchManagerWiseTimesheetData(String manager, String fromDate,
			String toDate) {
		logger.info("Inside EmployeeMasterService class fetchManagerWiseTimesheetData() method");
		List<Object[]> result = null;
		ArrayList<ManagerDashboardResponse> returnList = new ArrayList<ManagerDashboardResponse>();
		result = employeeMasterRepository.getManagerWiseTimesheetData(manager, fromDate, toDate);
		try {
			result.stream().forEach((record) -> {
				ManagerDashboardResponse managerDashboardResponse = new ManagerDashboardResponse();
				managerDashboardResponse.setUserId((String) record[0]);
				managerDashboardResponse.setResourceName((String) record[1]);
				managerDashboardResponse.setAbhiManager((String) record[2]);
				managerDashboardResponse.setSmtManager((String) record[3]);
				managerDashboardResponse.setOnroll((boolean) record[4]);
				managerDashboardResponse.setCategory((String) record[5]);
				managerDashboardResponse.setComments((String) record[6]);
				managerDashboardResponse.setBookedEfforts((String) record[7]);
				if (record[8] != null)
					managerDashboardResponse.setCreatedAt((Date) record[8]);
				managerDashboardResponse.setWbsCode((String) record[9]);

				returnList.add(managerDashboardResponse);

			});
		} catch (Exception e) {
			logger.info("Error occured while fetching managerWiseTimesheetData data");
			logger.info(e.getMessage());
		}

		return returnList;
	}

	public List<UserDashboardResponse> fetchUserDashboard(String name) {
		logger.info("Inside EmployeeMasterService class fetchUserDashboard() method");
		List<Object[]> result = null;
		ArrayList<UserDashboardResponse> returnList = new ArrayList<UserDashboardResponse>();

		// Get the first day of the current month
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDayOfMonth = calendar.getTime();

		// Get the last day of the current month
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDayOfMonth = calendar.getTime();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fromDate = dateFormat.format(firstDayOfMonth);
		String toDate = dateFormat.format(lastDayOfMonth);

		result = employeeMasterRepository.getUserDashboard(name, fromDate, toDate);
		try {
			result.stream().forEach((record) -> {
				UserDashboardResponse userDashboardResponse = new UserDashboardResponse();
				userDashboardResponse.setUserName((String) record[0]);
				userDashboardResponse.setLabel((String) record[1]);
				userDashboardResponse.setValue((String) record[2]);

				returnList.add(userDashboardResponse);
			});
		} catch (Exception e) {
			logger.info("Error occurred while fetching userDashboard timesheet data");
			logger.info(e.getMessage());
		}

		return returnList;
	}

	public List<GetTimeResponse> getTotalTime(String name) {
		logger.info("Inside EmployeeMasterService class getTotalTime() method");
		List<Object[]> result = null;
		ArrayList<GetTimeResponse> returnList = new ArrayList<GetTimeResponse>();

		// Get the current day's date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(new Date());

		result = employeeMasterRepository.getTotalTime(name, currentDate, currentDate);
		try {
			result.stream().forEach((record) -> {
				GetTimeResponse getTimeResponse = new GetTimeResponse();
				getTimeResponse.setUserName((String) record[0]);
				getTimeResponse.setSum((String) record[1]);

				returnList.add(getTimeResponse);
			});
		} catch (Exception e) {
			logger.info("Error occurred while fetching totalTime data");
			logger.info(e.getMessage());
		}

		return returnList;
	}

	public List<MissingDatesResponse> getMissingDates(String manager) {
		logger.info("Inside EmployeeMasterService class getMissingDates() method");
		List<Object[]> result = null;
		ArrayList<MissingDatesResponse> returnList = new ArrayList<MissingDatesResponse>();
		result = employeeMasterRepository.findMissingDates(manager);
		try {
			result.stream().forEach((record) -> {
				MissingDatesResponse missingDatesResponse = new MissingDatesResponse();
				missingDatesResponse.setMissingDate((String) record[0]);

				returnList.add(missingDatesResponse);

			});
		} catch (Exception e) {
			logger.info("Error occured while fetching missingDates data");
			logger.info(e.getMessage());
		}

		return returnList;
	}

	public List<UserNameAndYesterdayResponse> getYesterdayTimesheet(String manager) {
		logger.info("Inside EmployeeMasterService class getYesterdayTimesheet() method");
		List<Object[]> result = null;
		ArrayList<UserNameAndYesterdayResponse> returnList = new ArrayList<UserNameAndYesterdayResponse>();
		result = employeeMasterRepository.findByUserNameAndYesterday(manager);
		try {
			result.stream().forEach((record) -> {
				UserNameAndYesterdayResponse userNameAndYesterdayResponse = new UserNameAndYesterdayResponse();
				userNameAndYesterdayResponse.setCategory((String) record[0]);
				userNameAndYesterdayResponse.setComments((String) record[1]);
				userNameAndYesterdayResponse.setBookedEfforts((String) record[2]);
				userNameAndYesterdayResponse.setCreatedAt((Date) record[3]);
				userNameAndYesterdayResponse.setWbsCode((String) record[4]);

				returnList.add(userNameAndYesterdayResponse);

			});
		} catch (Exception e) {
			logger.info("Error occured while fetching yesterdayTimesheet data");
			logger.info(e.getMessage());
		}

		return returnList;
	}

}
