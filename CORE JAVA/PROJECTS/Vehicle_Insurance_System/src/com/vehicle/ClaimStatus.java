package com.vehicle;

import java.sql.*;

public class ClaimStatus {
    public static void getClaimStatus(int claimId) {
        String query = "SELECT claim_status FROM claims WHERE claim_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, claimId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String status = rs.getString("claim_status");
                System.out.println("Claim Status: " + status);
            } else {
                System.out.println("Claim not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

