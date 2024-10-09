package com.hexaware.dao;


import com.hexaware.entity.Policy;
import com.hexaware.exception.PolicyNotFoundException; 
import java.util.Collection;

public interface IPolicyService {
    boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId) throws PolicyNotFoundException; // Updated to include exception
    Collection<Policy> getAllPolicies();
    boolean updatePolicy(Policy policy);
    boolean deletePolicy(int policyId);
}
