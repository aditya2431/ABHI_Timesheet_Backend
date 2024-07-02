package com.example.timesheet.controller;

import com.example.timesheet.exception.ResourceNotFoundException;
import com.example.timesheet.model.PunchInDetails;
import com.example.timesheet.repository.PunchInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PunchInController {

	@Autowired
	PunchInRepository punchInRepository;

	@GetMapping("/getAllPunchInRecords")
	public List<PunchInDetails> getAllRecords() {
		return punchInRepository.findAll();
	}

	@PostMapping("/savePunchInRecord")
	public PunchInDetails createRecord(@Valid @RequestBody PunchInDetails punchInDetails) {
		return punchInRepository.save(punchInDetails);
	}

	@GetMapping("/getPunchInByUserId/{userId}")
	public List<PunchInDetails> getPunchInByUserId(@PathVariable(value = "userId") String userId) {
		return punchInRepository.findByUserId(userId);
	}

	@GetMapping("/getPunchInByUserIdAndTime/{userId}/{punchInTime}")
	public List<PunchInDetails> getPunchInByUserIdAndTime(@PathVariable(value = "userId") String userId,
			@PathVariable(value = "punchInTime") String punchInTime) {
		return punchInRepository.findByUserIdAndPunchInTime(userId, punchInTime);
	}
}