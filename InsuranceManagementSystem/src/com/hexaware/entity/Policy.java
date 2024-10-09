package com.hexaware.entity;


public class Policy {
    private int policyId;
    private String policyName;
    private double premiumAmount;

    // Default constructor
    public Policy() {}

    // Parameterized constructor
    public Policy(int policyId, String policyName, double premiumAmount) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.premiumAmount = premiumAmount;
    }

    // Getters and Setters
    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    // toString() method
    @Override
    public String toString() {
        return "Policy{" +
                "policyId=" + policyId +
                ", policyName='" + policyName + '\'' +
                ", premiumAmount=" + premiumAmount +
                '}';
    }
}
