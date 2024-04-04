package com.example.timesheet.repository;

import com.example.timesheet.model.Timesheet;
import com.example.timesheet.model.User;
import java.util.Date;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
	
	public List<Timesheet> findByUserNameOrderByIdDesc(String userName);

	public List<Timesheet> findByWbsCodeIn(List<String> wbsCode);
	
	@Query("select a from Timesheet a where userName = :UserName and date_format(created_at, '%Y-%m-%d') between date(:fromDate) and date(:toDate) ")
	public List<Timesheet> findByFromAndTwoDate(String UserName, String fromDate, String toDate);
	
	@Query("select a from Timesheet a where User_name = :usersName and month(created_at) = :month ")
	public List<Timesheet> findByUserNameAndMonth(String usersName, int month);
	
//	@Query("select a from Timesheet a where month(created_at) = :month ")
//	public List<Timesheet> findByMonth(int month);
	
}
