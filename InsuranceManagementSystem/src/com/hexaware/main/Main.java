package com.hexaware.main;


import com.hexaware.dao.IPolicyService;
import com.hexaware.dao.InsuranceServiceImpl;
import com.hexaware.entity.Policy;
import com.hexaware.exception.PolicyNotFoundException;
import com.hexaware.util.DBConnection;

import java.sql.Connection;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get the database connection
        Connection connection = DBConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to establish database connection. Exiting...");
            return;
        }

        IPolicyService insuranceService = new InsuranceServiceImpl(connection); // Create service with the connection
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nInsurance Management System");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy");
            System.out.println("3. Get All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Create Policy
                    System.out.println("Enter policy ID:");
                    int policyId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter policy name:");
                    String policyName = scanner.nextLine();
                    System.out.println("Enter premium amount:");
                    double premiumAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    Policy newPolicy = new Policy(policyId, policyName, premiumAmount);
                    if (insuranceService.createPolicy(newPolicy)) {
                        System.out.println("Policy created successfully.");
                    } else {
                        System.out.println("Failed to create policy.");
                    }
                    break;

                case 2:
                    // Get Policy
                    System.out.print("Enter Policy ID: ");
                    int getPolicyId = scanner.nextInt();
                    try {
                        Policy policy = insuranceService.getPolicy(getPolicyId);
                        System.out.println("Policy: " + policy);
                    } catch (PolicyNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    // Get All Policies
                    Collection<Policy> policies = insuranceService.getAllPolicies();
                    if (policies.isEmpty()) {
                        System.out.println("No policies found.");
                    } else {
                        policies.forEach(System.out::println);
                    }
                    break;

                case 4:
                    // Update Policy
                    System.out.print("Enter Policy ID to update: ");
                    int updatePolicyId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Policy updatePolicy = null;
				try {
					updatePolicy = insuranceService.getPolicy(updatePolicyId);
				} catch (PolicyNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    // Modify the policy object as needed (for demonstration, we are not doing actual modifications)
                    System.out.println("Enter new policy name:");
                    String newPolicyName = scanner.nextLine();
                    System.out.println("Enter new premium amount:");
                    double newPremiumAmount = scanner.nextDouble();
                    updatePolicy.setPolicyName(newPolicyName);
                    updatePolicy.setPremiumAmount(newPremiumAmount);
                    if (insuranceService.updatePolicy(updatePolicy)) {
                        System.out.println("Policy updated successfully.");
                    } else {
                        System.out.println("Failed to update policy.");
                    }
                    break;

                case 5:
                    // Delete Policy
                    System.out.print("Enter Policy ID to delete: ");
                    int deletePolicyId = scanner.nextInt();
                    if (insuranceService.deletePolicy(deletePolicyId)) {
                        System.out.println("Policy deleted successfully.");
                    } else {
                        System.out.println("Failed to delete policy.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
