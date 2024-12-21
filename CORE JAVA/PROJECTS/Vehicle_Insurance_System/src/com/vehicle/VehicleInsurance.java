package com.vehicle;

import java.sql.*;

public class VehicleInsurance {
	public static void addVehicle(String ownerName, String vehicleModel, String licensePlate, String policyNumber) throws SQLException {

		//if (!checkDuplicateData(ownerName, vehicleModel, licensePlate, policyNumber)) {

			// String query = "INSERT INTO vehicles (owner_name, vehicle_model,
			// license_plate, insurance_policy_number) VALUES (?, ?, ?, ?)";

			String query = "INSERT INTO vehicles (owner_name, vehicle_model, license_plate, insurance_policy_number) VALUES ('"
					+ ownerName + "','" + vehicleModel + "','" + licensePlate + "','" + policyNumber + "')";

			System.out.println(query);
			try (
					// Step 1
					Connection conn = DatabaseConnection.getInstance().getConnection();
					// Step 2
					Statement stmt = conn.createStatement()) {
				// PreparedStatement stmt = conn.prepareStatement(query)) {

				// stmt.setString(1, ownerName);
				// stmt.setString(2, vehicleModel);
				// stmt.setString(3, licensePlate);
				// stmt.setString(4, policyNumber);

				// Step 3 - execute the query
				int rowsAffected = stmt.executeUpdate(query);

				// Step 4 - processing the result
				System.out.println(" Rows Affected :" + rowsAffected);
				if (rowsAffected > 0) {
					System.out.println("Vehicle record added successfully.");
				}
				//DatabaseConnection.getInstance().closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					DatabaseConnection.getInstance().closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			/*
			 * } else { System.out.println(" Duplicate data !!!"); }
			 */
		
	}

	private static boolean checkDuplicateData(String ownerName, String vehicleModel, String licensePlate,
			String policyNumber) throws SQLException {
		// TODO Auto-generated method stub
		boolean duplicate = false;
		String query = "SELECT * FROM vehicles";

		try (Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			// stmt.setInt(1, claimId);
			ResultSet rs = stmt.executeQuery();
			System.out.println("ResultSet :: " + rs);

			// Processing
			while (rs.next()) {
				String owner_name = rs.getString("owner_name");
				String vehicle_model = rs.getString("vehicle_model");
				String license_plate = rs.getString("license_plate");
				String policy_number = rs.getString("insurance_policy_number");

				// String status = rs.getString("claim_status");
				System.out.println("Vehicle Details : " + owner_name + " " + vehicle_model + " " + policy_number);
				if (ownerName.equalsIgnoreCase(owner_name) && vehicleModel.equalsIgnoreCase(vehicle_model)
						&& licensePlate.equalsIgnoreCase(license_plate)
						&& policy_number.equalsIgnoreCase(policyNumber)) {
					duplicate = true;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DatabaseConnection.getInstance().closeConnection();
		}
		return duplicate;
	}
}
