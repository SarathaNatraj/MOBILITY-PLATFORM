package com.vehicle;

import java.sql.*;

public class InsuranceClaim {
    public static void fileClaim(int vehicleId, double claimAmount) {
        String query = "INSERT INTO claims (vehicle_id, claim_date, claim_amount, claim_status) VALUES (?, CURDATE(), ?, 'Pending')";
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, vehicleId); // Vehicle ID from the vehicles table
            stmt.setDouble(2, claimAmount); // Claim amount
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Claim filed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void removeClaim(int claim_id) {
        String query = "DELETE from claims where claim_id = ?";
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, claim_id); // Vehicle ID from the vehicles table
           // stmt.setDouble(2, claimAmount); // Claim amount
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Claim deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
