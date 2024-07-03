package com.example.timesheet.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.timesheet.pojo.PunchInSumResponse;
import com.example.timesheet.repository.PunchInRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class PunchInTimeService {

	private static final Logger logger = LogManager.getLogger(PunchInTimeService.class);

	@Autowired
	private PunchInRepository punchInRepository;

	public List<PunchInSumResponse> getPunchinSummaryForToday() {
		logger.info("Inside PunchInTimeService PunchInTimeService() method");
		List<Object[]> result = null;
		List<PunchInSumResponse> returnList = new ArrayList<PunchInSumResponse>();

		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		try {
			result = punchInRepository.findSummaryByDate(today);
			System.out.print(result.get(0));

			if (result != null && result.size() > 0) {
				result.stream().forEach(record -> {
					PunchInSumResponse response = new PunchInSumResponse();
					response.setCount9To10((BigDecimal) record[0]);
					response.setCount10To11((BigDecimal) record[1]);
					response.setCountAfter11((BigDecimal) record[2]);
					returnList.add(response);
				});
			}
		} catch (Exception e) {
			logger.info("Some error occured while fetching getPunchinSummaryForToday records");
			logger.info(e.getMessage());
		}
		return returnList;
	}
}