package com.example.timesheet.repository;

import com.example.timesheet.model.PunchInDetails;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PunchInRepository extends JpaRepository<PunchInDetails, Integer> {
	
	public List<PunchInDetails> findByUserId(String userId);
	
	@Query("select a from PunchInDetails a where userId = :userId and date_format(punchInTime, '%Y-%m-%d') = date(:punchInTime) ")
	public List<PunchInDetails> findByUserIdAndPunchInTime(String userId, String punchInTime);
}
