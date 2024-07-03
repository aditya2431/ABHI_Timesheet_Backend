package com.example.timesheet.service;

import java.lang.System.Logger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.timesheet.pojo.PunchInSumResponse;
import com.example.timesheet.repository.PunchInRepository;

@Service
public class PunchInTimeService {
//	private static final Logger logger = LoggerFactory.getLogger(PunchInTimeService.class);

	@Autowired
	private PunchInRepository punchInRepository;

	public List<PunchInSumResponse> getPunchinSummaryForToday() {
		List<Object[]> result = null;
		List<PunchInSumResponse> returnList = new ArrayList<PunchInSumResponse>();

		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		try {
			result = punchInRepository.findSummaryByDate(today);
			System.out.print(result.get(0));

			if (result != null && result.size() > 0) {
				result.stream().forEach(record -> {
					PunchInSumResponse response = new PunchInSumResponse();
					response.setCount9To10((String) record[0]);
					response.setCount10To11((String) record[1]);
					response.setCountAfter11((String) record[2]);
					returnList.add(response);
				});
			}
		} catch (Exception e) {
			// Optionally, you might want to throw or handle the exception
		}

		return returnList;
	}
}