
package com.example.timesheet.controller;

import com.example.timesheet.model.CRDetails;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.CRDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CRDetailsController {

    @Autowired
    CRDetailsRepository crdetailsRepository;

    @GetMapping("/enterCR")
    public List<CRDetails> getAllRecords() {
        return crdetailsRepository.findAll();
    }

    @PostMapping("/enterCR")
    public List<CRDetails> createRecord(@Valid @RequestBody List<CRDetails> crDetails) {
        return crdetailsRepository.saveAll(crDetails);
    }
    
    @GetMapping("/enterCRbyWbsCode/{wbsCode}")
    public List<CRDetails> getRecordByWbsCode(@PathVariable(value = "wbsCode") String wbsCode) {
        return crdetailsRepository.findBywbsCode(wbsCode);
    }
    
    @GetMapping("/timesheetByAbhiManager/{abhiManager}")
    public List<CRDetails> getRecordByAbhiManager(@PathVariable(value = "abhiManager") String abhiManager) {
        return crdetailsRepository.findByAbhiManager(abhiManager);
    }
}
