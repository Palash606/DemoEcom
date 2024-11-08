package com.example.insurance_app.controller;

import com.example.insurance_app.model.Executive;
import com.example.insurance_app.service.ExecutiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutiveController {

    @Autowired
    private ExecutiveService executiveService;

    @PostMapping("/executive/add")
    public Executive insert(@RequestBody Executive executive) {
        return executiveService.insert(executive);
    }
}