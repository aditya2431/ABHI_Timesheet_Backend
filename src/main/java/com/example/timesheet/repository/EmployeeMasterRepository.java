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

	@Query("select a.userId, a.resourceName, a.abhiManager,a.smtManager, a.isonroll " + "from EmployeeMaster a "
			+ "where a.userId not in (select distinct userName from User) " + "order by a.isonroll desc ")
	public List<Object[]> getUserRegistrationData();

	@Query("select a.userId, a.resourceName, a.abhiManager,a.smtManager, a.isonroll " + "from EmployeeMaster a "
			+ "where a.userId not in (select distinct userName from Timesheet "
			+ "where date_format(created_at, '%Y-%m-%d') = date(:fromDate)) " + "order by a.isonroll desc ")
	public List<Object[]> getDateWiseTimesheetData(String fromDate);

	@Query("select a.userId, a.resourceName, a.abhiManager, a.smtManager, a.isonroll ,\r\n"
			+ "b.category, b.comments,b .bookedEfforts, b.createdAt, b.wbsCode\r\n"
			+ "from EmployeeMaster a, Timesheet b\r\n" + "where a.userId = b.userName\r\n"
			+ "and (a.abhiManagerId = :manager or a.smtId = :manager)\r\n"
			+ "and date_format(created_at,'%Y-%m-%d') between date(:fromDate) and date(:toDate)\r\n"
			+ "order by a.isonroll, b.createdAt desc")
	public List<Object[]> getManagerWiseTimesheetData(String manager, String fromDate, String toDate);

	@Query("select a.userName, a.category, sum(a.bookedEfforts) \r\n" + "from Timesheet a \r\n"
			+ "where a.userName = :name\r\n"
			+ "and date_format(created_at,'%Y-%m-%d') between  date(:fromDate) and date(:toDate) \r\n"
			+ "group by a.userName, a.category ")
	public List<Object[]> getUserDashboard(String name, String fromDate, String toDate);

	@Query(value = "SELECT a.user_Name, TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(a.booked_Efforts))), '%H:%i') AS total_time "
			+ "FROM timesheet_details a " + "WHERE a.user_Name = ?1 "
			+ "AND DATE_FORMAT(a.created_at, '%Y-%m-%d') BETWEEN DATE(?2) AND DATE(?3) "
			+ "GROUP BY a.user_Name", nativeQuery = true)
	public List<Object[]> getTotalTime(String name, String fromDate, String toDate);

	@Query(value = "SELECT DISTINCT DATE_FORMAT(all_dates.date, '%Y-%m-%d') AS missing_date " + "FROM ("
			+ "    SELECT DATE_SUB(LAST_DAY(CURDATE()), INTERVAL DAY(LAST_DAY(CURDATE()))-1 DAY) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date "
			+ "    FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a "
			+ "    CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b "
			+ "    CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c "
			+ ") AS all_dates "
			+ "LEFT JOIN timesheet_details a ON all_dates.date = DATE(a.created_at) AND a.user_Name = ?1 "
			+ "WHERE MONTH(all_dates.date) = MONTH(CURDATE()) " + "AND YEAR(all_dates.date) = YEAR(CURDATE()) "
			+ "AND a.user_Name IS NULL", nativeQuery = true)
	List<Object[]> findMissingDates(String userName);

	@Query(value = "SELECT a.category, a.booked_efforts, a.comments, a.created_at, a.wbs_code "
			+ "FROM timesheet_details a "
			+ "WHERE DATE_FORMAT(a.created_at, '%Y-%m-%d') = DATE(DATE_SUB(CURDATE(), INTERVAL 1 DAY)) "
			+ "AND a.user_name = ?1", nativeQuery = true)
	List<Object[]> findByUserNameAndYesterday(String userName);

}
