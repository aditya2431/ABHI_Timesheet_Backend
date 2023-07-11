package com.example.timesheet.repository;

import com.example.timesheet.model.Timesheet;
import com.example.timesheet.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
	
	public List<Timesheet> findByWbsCode(String wbsCode);
	public List<Timesheet> findByUserNameOrderByIdDesc(String userName);
}
