package com.example.backend.controller;

import com.example.backend.domain.dto.CreditDecisionDto;
import com.example.backend.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credit")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }


    @PostMapping("/decision")
    public CreditDecisionDto makeDecision(@RequestBody CreditDecisionDto request) {
        return creditService.makeDecision(request);
    }
}
