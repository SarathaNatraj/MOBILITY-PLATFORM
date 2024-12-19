package com.vehicle;

import java.sql.*;

public class ClaimStatus {
    public static void getClaimStatus(int claimId) {
    	//R - Select Query str
        String query = "SELECT claim_status FROM claims WHERE claim_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, claimId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("ResultSet :: "+rs);
            
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
    public static void getAllClaims() {
    	//R - Select Query str
        String query = "SELECT * FROM claims";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
          //  stmt.setInt(1, claimId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("ResultSet :: "+rs);
            
            //Processing 
            while (rs.next()) {
            	int claim_id = rs.getInt("claim_id");
            	int vehicle_id= rs.getInt("vehicle_id");
            	Date claim_date = rs.getDate("claim_date");
            	double claim_amount = rs.getDouble("claim_amount");
                String status = rs.getString("claim_status");
                System.out.println("Claim Details : " + claim_id +" "+ status+" "+claim_date);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

