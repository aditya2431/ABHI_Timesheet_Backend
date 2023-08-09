package com.example.timesheet.controller;

import com.example.timesheet.exception.ResourceNotFoundException;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TimesheetController {

    @Autowired
    TimesheetRepository timesheetRepository;

    @GetMapping("/timesheet")
    public List<Timesheet> getAllRecords() {
        return timesheetRepository.findAll();
    }

    @PostMapping("/timesheet")
    public Timesheet createRecord(@Valid @RequestBody Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    @GetMapping("/timesheet/{id}")
    public Timesheet getRecordById(@PathVariable(value = "id") Long timesheetId) {
        return timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new ResourceNotFoundException("Timesheet", "id", timesheetId));
    }
    
//    @GetMapping("/timesheetByAbhiManager/{abhiManager}")
//    public List<Timesheet> getRecordByAbhiManager(@PathVariable(value = "wbsCode") String abhiManager) {
//        return timesheetRepository.findByAbhiManager(abhiManager);
//    }
    
    @GetMapping("/timesheetByUserName/{userName}")
    public List<Timesheet> getRecordByUserName(@PathVariable(value = "userName") String userName) {
        return timesheetRepository.findByUserNameOrderByIdDesc(userName);
    }
    
    @PutMapping("/timesheetUpdate/{id}")
    public ResponseEntity<Timesheet> updateTimesheet(@PathVariable(value = "id") Long timesheetId,  @RequestBody Timesheet timesheetDetails){
    	 Timesheet timesheet =  timesheetRepository.findById(timesheetId)
                 .orElseThrow(() -> new ResourceNotFoundException("Timesheet", "id", timesheetId));
    	 timesheet.setUserName(timesheetDetails.getUserName());
    	 timesheet.setBookedEfforts(timesheetDetails.getBookedEfforts());
    	 timesheet.setCategory(timesheetDetails.getCategory());
    	 timesheet.setComments(timesheetDetails.getComments());
    	 timesheet.setCreatedAt(timesheetDetails.getCreatedAt());
    	 timesheet.setEmailId(timesheetDetails.getEmailId());
    	 timesheet.setWbsCode(timesheetDetails.getWbsCode());
    	 
    	 Timesheet updatedTimesheet = timesheetRepository.save(timesheet);
    	 
    	 return ResponseEntity.ok(updatedTimesheet);
    	 
    	
    }
    
    
    @DeleteMapping("/timesheetDelete/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteTimesheet(@PathVariable(value = "id") Long timesheetId){
    	Timesheet timesheet =  timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new ResourceNotFoundException("Timesheet", "id", timesheetId));
    	timesheetRepository.delete(timesheet);
    	Map<String,Boolean> response = new HashMap<>();
    	response.put("deleted", Boolean.TRUE);
    	return ResponseEntity.ok(response);
    	
    	
    }
    
}
