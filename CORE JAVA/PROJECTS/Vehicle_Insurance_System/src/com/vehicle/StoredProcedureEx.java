package com.vehicle;

import java.sql.*;

public class StoredProcedureEx {
    public static void fileClaim(int vehicleId, double claimAmount) {
        // SQL call for the stored procedure
        String sql = "{CALL fileClaimProcedure_1(?, ?)}";
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
             
            // Set input parameters for the stored procedure
            stmt.setInt(1, vehicleId); // vehicle_id
            stmt.setDouble(2, claimAmount); // claim_amount
            
            // Execute the stored procedure
            stmt.execute();
            System.out.println("Claim filed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}