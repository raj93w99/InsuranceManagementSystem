package com.hexaware.dao;

import com.hexaware.entity.Policy;
import com.hexaware.exception.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InsuranceServiceImpl implements IPolicyService {
    private Connection connection;

    public InsuranceServiceImpl(Connection connection) {
        this.connection = connection;
    }

    public boolean createPolicy(Policy policy) {
        String query = "INSERT INTO policies (policyId, policyName, premiumAmount) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, policy.getPolicyId());
            stmt.setString(2, policy.getPolicyName());
            stmt.setDouble(3, policy.getPremiumAmount());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
            return false;
        }
    }

   
    @Override
    public Policy getPolicy(int policyId) {
        String query = "SELECT * FROM policies WHERE policyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, policyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getDouble("premiumAmount")
                );
            } else {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
            return null; // Return null or handle as needed
        } catch (PolicyNotFoundException e) {
            // Handle the custom exception if needed
            e.printStackTrace();
            return null; // Return null or handle as needed
        }
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Collection getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        String query = "SELECT * FROM policies";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                policies.add(new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getDouble("premiumAmount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return policies;
    }


    public boolean updatePolicy(Policy policy) {
        String query = "UPDATE policies SET policyName = ?, premiumAmount = ? WHERE policyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, policy.getPolicyName());
            stmt.setDouble(2, policy.getPremiumAmount());
            stmt.setInt(3, policy.getPolicyId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) {
        String query = "DELETE FROM policies WHERE policyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, policyId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
            return false;
        }
    }
}
