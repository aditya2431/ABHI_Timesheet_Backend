package com.example.timesheet.repository;

import com.example.timesheet.model.EmployeeMaster;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Integer> {
	
	public List<EmployeeMaster> findByUserId(String userId);
	
	public List<EmployeeMaster> findByabhiManagerId(String abhiManagerId);
	
	@Query("select a.userId, a.resourceName, a.abhiManager,a.smtManager, a.isonroll "
			+ "from EmployeeMaster a "
			+ "where a.userId not in (select distinct userName from User) "
			+ "order by a.isonroll desc ")
	public List<Object[]> getUserRegistrationData();
	
	@Query("select a.userId, a.resourceName, a.abhiManager,a.smtManager, a.isonroll "
			+ "from EmployeeMaster a "
			+ "where a.userId not in (select distinct userName from Timesheet "
			+ "where date_format(created_at, '%Y-%m-%d') = date(:fromDate)) "
			+ "order by a.isonroll desc ")
	public List<Object[]> getDateWiseTimesheetData(String fromDate);
	
	
}
