package com.example.vehicleclaimapp.service.claim;

import com.example.vehicleclaimapp.model.Claim;

import java.util.ArrayList;
import java.util.Optional;

public class ClaimManager {
    private ArrayList<Claim> claims;

    // Constructor
    public ClaimManager() {
        this.claims = new ArrayList<>();
    }

    // Add a new claim
    public void addClaim(Claim claim) {
        claims.add(claim);
    }

    // Get all claims (claim history)
    public ArrayList<Claim> getAllClaims() {
        return claims;
    }

    // Find a claim by ID
    public Claim findClaimById(String claimId) {
        for (Claim claim : claims) {
            if (claim.getClaimId().equals(claimId)) {
                return claim;
            }
        }
        return null;
    }

    // Update a claim status
    public void updateClaimStatus(String claimId, String newStatus, String updatedDate) {
        Claim claim = findClaimById(claimId);

        if (claim != null) {
            claim.setStatus(newStatus);
            claim.setDateUpdated(updatedDate);
            claims.add(claim);
            // Use streams to fetch and update the object
            Optional<Claim> claimToUpdate = claims.stream()
                    .filter(claim1 -> claim1.getClaimId().equals(claimId))
                    .findFirst();

            // Update the object if present
            claimToUpdate.ifPresent(claimLatest -> {
                claimLatest.setStatus(newStatus);
                claimLatest.setDateUpdated(updatedDate);
            });

        }
    }
}

