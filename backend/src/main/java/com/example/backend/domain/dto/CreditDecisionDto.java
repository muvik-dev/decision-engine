package com.example.backend.domain.dto;

import com.example.backend.domain.enums.DecisionStatus;

public class CreditDecisionDto {
    String personalCode;
    double loanAmount;
    int loanPeriod;

    DecisionStatus decision;

    double approvedAmount;


    public String getPersonalCode() {
        return personalCode;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public void setDecision(DecisionStatus decision) {
        this.decision = decision;
    }

    public void setApprovedAmount(double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

}
