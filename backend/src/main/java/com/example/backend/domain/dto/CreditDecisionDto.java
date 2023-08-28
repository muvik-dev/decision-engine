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

    public DecisionStatus getDecision() {
        return decision;
    }

    public double getApprovedAmount() {
        return approvedAmount;
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

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
    }
}
