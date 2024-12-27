package com.example.vehicleinsuranceapp.fragment.claims;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vehicleinsuranceapp.MainActivity;
import com.example.vehicleinsuranceapp.R;
import com.example.vehicleinsuranceapp.model.Claim;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClaimFormFragment extends Fragment {

    private EditText vehicleNumberEditText, accidentDetailsEditText, claimAmountEditText;
    private Button submitClaimButton;

    private SharedPreferences sharedPreferences;

    public ClaimFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_claim_form, container, false);

        vehicleNumberEditText = view.findViewById(R.id.vehicleNumberEditText);
        accidentDetailsEditText = view.findViewById(R.id.accidentDetailsEditText);
        claimAmountEditText = view.findViewById(R.id.claimAmountEditText);
        submitClaimButton = view.findViewById(R.id.submitClaimButton);

        sharedPreferences = getContext().getSharedPreferences("ClaimsData", Context.MODE_PRIVATE);

        submitClaimButton.setOnClickListener(v -> submitClaim());

        return view;
    }

    private void submitClaim() {
        String vehicleNumber = vehicleNumberEditText.getText().toString().trim();
        String accidentDetails = accidentDetailsEditText.getText().toString().trim();
        String claimAmount = claimAmountEditText.getText().toString().trim();

        if (TextUtils.isEmpty(vehicleNumber) || TextUtils.isEmpty(accidentDetails) || TextUtils.isEmpty(claimAmount)) {
            Toast.makeText(getContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
        } else {
            // Save claim data in SharedPreferences
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Claim claim = new Claim(vehicleNumber, accidentDetails, claimAmount, date);
            saveClaim(claim);

            // Show success message
            Toast.makeText(getContext(), "Claim Submitted!", Toast.LENGTH_SHORT).show();

            // Navigate back to Claim History
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).loadFragment(new ClaimHistoryFragment());
            }
        }
    }

    private void saveClaim(Claim claim) {
        //create a sharedpefreference obj
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // For simplicity, we are saving one claim at a time, you can extend this to save multiple claims
        editor.putString("vehicleNumber", claim.getVehicleNumber());
        editor.putString("accidentDetails", claim.getAccidentDetails());
        editor.putString("claimAmount", claim.getClaimAmount());
        editor.putString("date", claim.getDate());
        //save the shared pref
        editor.apply();
    }
}
