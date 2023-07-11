package com.example.timesheet.controller;

import com.example.timesheet.exception.ResourceNotFoundException;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    
    @GetMapping("/timesheetByWBS/{wbsCode}")
    public List<Timesheet> getRecordByWbsCode(@PathVariable(value = "wbsCode") String wbsCode) {
        return timesheetRepository.findByWbsCode(wbsCode);
    }
    
    @GetMapping("/timesheetByUserName/{userName}")
    public List<Timesheet> getRecordByUserName(@PathVariable(value = "userName") String userName) {
        return timesheetRepository.findByUserNameOrderByIdDesc(userName);
    }
}
