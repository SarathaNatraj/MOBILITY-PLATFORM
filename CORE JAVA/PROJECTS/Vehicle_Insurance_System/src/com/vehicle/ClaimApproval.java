package com.vehicle;

import java.sql.*;

public class ClaimApproval {
    public static void updateClaimStatus(int claimId, String newStatus) {
        String query = "UPDATE claims SET claim_status = ? WHERE claim_id = ?";
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, newStatus);  // New status (Approved/Denied)
            stmt.setInt(2, claimId);       // Claim ID
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Claim status updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
