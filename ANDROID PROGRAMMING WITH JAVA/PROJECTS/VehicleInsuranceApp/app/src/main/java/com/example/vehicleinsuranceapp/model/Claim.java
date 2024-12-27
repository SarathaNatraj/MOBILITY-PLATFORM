package com.example.vehicleinsuranceapp.model;



import java.io.Serializable;

public class Claim implements Serializable {
    private String vehicleNumber;
    private String accidentDetails;
    private String claimAmount;
    private String date;

    public Claim(String vehicleNumber, String accidentDetails, String claimAmount, String date) {
        this.vehicleNumber = vehicleNumber;
        this.accidentDetails = accidentDetails;
        this.claimAmount = claimAmount;
        this.date = date;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getAccidentDetails() {
        return accidentDetails;
    }

    public String getClaimAmount() {
        return claimAmount;
    }

    public String getDate() {
        return date;
    }
}
