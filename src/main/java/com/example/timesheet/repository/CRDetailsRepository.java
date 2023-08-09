//package com.example.timesheet.repository;
//
//public class CRDetailsRepository {
//
//}


package com.example.timesheet.repository;

import com.example.timesheet.model.CRDetails;
import com.example.timesheet.model.Timesheet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CRDetailsRepository extends JpaRepository<CRDetails, Long> {
	
	public List<CRDetails> findBywbsCode(String wbsCode);
	public List<CRDetails> findByAbhiManager(String abhiManager);
//	public List<CRDetails> findByUserNameOrderByIdDesc(String userName);
}
