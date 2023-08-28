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
        double approvedAmount = creditModifier * loanPeriod;

        if (creditScore >= 1) {
            creditDecisionDto.setDecision(DecisionStatus.POSITIVE);
        } else {
            for (int i = 1; loanPeriod <= 60 && i <= 60; i++) {
                double maxApprovedAmount = creditModifier * loanPeriod;
                if (maxApprovedAmount < loanAmount) {
                    loanPeriod++;
                } else {
                    creditDecisionDto.setLoanPeriod(loanPeriod);
                    break;
                }
            }
            creditDecisionDto.setDecision(DecisionStatus.NEGATIVE);
        }

        creditDecisionDto.setApprovedAmount(approvedAmount);
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
