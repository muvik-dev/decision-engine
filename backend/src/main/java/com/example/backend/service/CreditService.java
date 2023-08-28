package com.example.backend.service;

import com.example.backend.domain.dto.CreditDecisionDto;
import com.example.backend.domain.enums.DecisionStatus;
import org.springframework.stereotype.Service;

@Service
public class CreditService {

    public CreditDecisionDto makeDecision(CreditDecisionDto creditDecisionDto) {
        String personalCode = creditDecisionDto.getPersonalCode();
        double loanAmount = creditDecisionDto.getLoanAmount();
        int loanPeriod = creditDecisionDto.getLoanPeriod();

        int creditModifier = calculateCreditModifier(personalCode);
        double creditScore = (creditModifier / loanAmount) * loanPeriod;

        if (creditScore >= 1) {
            creditDecisionDto.setDecision(DecisionStatus.POSITIVE);
            creditDecisionDto.setApprovedAmount(loanAmount);
        } else {
            double maxApprovedAmount = creditModifier * loanPeriod;
            creditDecisionDto.setDecision(DecisionStatus.NEGATIVE);
            creditDecisionDto.setApprovedAmount(maxApprovedAmount);
        }
        return creditDecisionDto;
    }

    private int calculateCreditModifier(String personalCode) {
        return switch (personalCode) {
            case "49002010976" -> 100;
            case "49002010987" -> 300;
            case "49002010998" -> 1000;
            default -> 0;
        };
    }
}
