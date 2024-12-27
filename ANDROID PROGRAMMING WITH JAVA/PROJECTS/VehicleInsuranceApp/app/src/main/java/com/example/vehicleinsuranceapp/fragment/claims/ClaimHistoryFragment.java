package com.example.vehicleinsuranceapp.fragment.claims;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.vehicleinsuranceapp.MainActivity;
import com.example.vehicleinsuranceapp.R;

public class ClaimHistoryFragment extends Fragment {

    private TextView claimHistoryTextView;
    private Button addClaimButton;

    private SharedPreferences sharedPreferences;

    public ClaimHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_claim_history, container, false);

        claimHistoryTextView = view.findViewById(R.id.claimHistoryTextView);
        addClaimButton = view.findViewById(R.id.addClaimButton);

        sharedPreferences = getContext().getSharedPreferences("ClaimsData", Context.MODE_PRIVATE);

        displayClaimHistory();

        addClaimButton.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).loadFragment(new ClaimFormFragment());
            }
        });

        return view;
    }

    private void displayClaimHistory() {
        // For simplicity, showing only one claim.
        // In a real application, you would retrieve all claims from a database or SharedPreferences
        String vehicleNumber = sharedPreferences.getString("vehicleNumber", "No claim yet");
        String accidentDetails = sharedPreferences.getString("accidentDetails", "No claim yet");
        String claimAmount = sharedPreferences.getString("claimAmount", "No claim yet");
        String date = sharedPreferences.getString("date", "No claim yet");

        String claimHistory = "Vehicle Number: " + vehicleNumber + "\n" +
                "Accident Details: " + accidentDetails + "\n" +
                "Claim Amount: " + claimAmount + "\n" +
                "Date: " + date;

        claimHistoryTextView.setText(claimHistory);
    }
}
