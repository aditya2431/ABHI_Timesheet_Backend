package com.example.timesheet.repository;

import com.example.timesheet.model.PunchInDetails;
import com.example.timesheet.pojo.PunchInSumResponse;

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

//	@Query(value = "SELECT " +
//            "SUM(CASE WHEN TIME(CONVERT_TZ(a.punchInTime, '+00:00', '+05:30')) BETWEEN '09:00:00' AND '10:00:00' THEN 1 ELSE 0 END) " +
//            "SUM(CASE WHEN TIME(CONVERT_TZ(a.punchInTime, '+00:00', '+05:30')) BETWEEN '10:00:00' AND '11:00:00' THEN 1 ELSE 0 END) " +
//            "SUM(CASE WHEN TIME(CONVERT_TZ(a.punchInTime, '+00:00', '+05:30')) > '11:00:00' THEN 1 ELSE 0 END) " +
//            "FROM PunchInDetails a " +  
//            "WHERE DATE(CONVERT_TZ(a.punchInTime, '+00:00', '+05:30')) = :date", nativeQuery = true)

	@Query(value = "SELECT " + "    SUM(CASE "
			+ "        WHEN TIME(CONVERT_TZ(a.punch_in_time, '+00:00', '+05:30')) BETWEEN '09:00:00' AND '10:00:00' THEN 1 "
			+ "        ELSE 0 " + "    END) AS count_9_to_10, " + "    SUM(CASE "
			+ "        WHEN TIME(CONVERT_TZ(a.punch_in_time, '+00:00', '+05:30')) BETWEEN '10:00:00' AND '11:00:00' THEN 1 "
			+ "        ELSE 0 " + "    END) AS count_10_to_11, " + "    SUM(CASE "
			+ "        WHEN TIME(CONVERT_TZ(a.punch_in_time, '+00:00', '+05:30')) > '11:00:00' THEN 1 "
			+ "        ELSE 0 " + "    END) AS count_after_11 " + "FROM " + "    employee_punchin_details a " + "WHERE "
			+ "    DATE(CONVERT_TZ(a.punch_in_time, '+00:00', '+05:30')) = :date", nativeQuery = true)
	public List<Object[]> findSummaryByDate(String date);
}
