package com.vehicle;

import java.sql.SQLException;

public class Vehicle_Insurance_Management {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		VehicleInsurance.addVehicle(" sXYZ", "Toyota xyv", "ABC923", "POLICX003");

	//	InsuranceClaim.fileClaim(1, 1500.00); // 
		
		//ClaimStatus.getClaimStatus(35);  
		
		ClaimStatus.getAllClaims();
		
		//ClaimApproval.updateClaimStatus(1, "Approved");
		
		//ClaimApproval.updateClaimStatus(2, "Denied");
		
		
		
		//InsuranceClaim.removeClaim(4);
       // StoredProcedureEx.fileClaim(4, 1000.00);
		
		//ClaimStatus.getAllClaims();
	}

}
