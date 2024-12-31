package com.example.vehicleinsuranceapp.model;



import java.io.Serializable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "claims")
public class Claim implements Serializable {
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setAccidentDetails(String accidentDetails) {
        this.accidentDetails = accidentDetails;
    }

    public void setClaimAmount(String claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    @PrimaryKey(autoGenerate = true)
    private int claimId;
    private String vehicleNumber;
    private String accidentDetails;
    private String claimAmount;
    private String date;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String imagePath;

    public Claim(String vehicleNumber, String accidentDetails, String claimAmount, String date) {
        this.vehicleNumber = vehicleNumber;
        this.accidentDetails = accidentDetails;
        this.claimAmount = claimAmount;
        this.date = date;
    }
    public Claim(){
        super();
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
