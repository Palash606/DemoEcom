package com.example.insurance_app.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PolicyResponseDto {
    private int policyHolderId;
    private String policyHolderName;
    private String policyHolderPan;
    private LocalDate dateOfPurchase;
    private LocalDate dateOfRenewal;

    private double premium_paid;
    private String policyCategory;
    private String policyType;

    public int getPolicyHolderId() {
        return policyHolderId;
    }

    public void setPolicyHolderId(int policyHolderId) {
        this.policyHolderId = policyHolderId;
    }

    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }

    public String getPolicyHolderPan() {
        return policyHolderPan;
    }

    public void setPolicyHolderPan(String policyHolderPan) {
        this.policyHolderPan = policyHolderPan;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public LocalDate getDateOfRenewal() {
        return dateOfRenewal;
    }

    public void setDateOfRenewal(LocalDate dateOfRenewal) {
        this.dateOfRenewal = dateOfRenewal;
    }

    public double getPremium_paid() {
        return premium_paid;
    }

    public void setPremium_paid(double premium_paid) {
        this.premium_paid = premium_paid;
    }

    public String getPolicyCategory() {
        return policyCategory;
    }

    public void setPolicyCategory(String policyCategory) {
        this.policyCategory = policyCategory;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }
}
