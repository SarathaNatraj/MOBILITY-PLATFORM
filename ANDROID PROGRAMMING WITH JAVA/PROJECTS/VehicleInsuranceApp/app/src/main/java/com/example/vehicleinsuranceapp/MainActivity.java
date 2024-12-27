package com.example.vehicleinsuranceapp;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.vehicleinsuranceapp.fragment.claims.ClaimFormFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load Claim Form Fragment by default
        if (savedInstanceState == null) {
            loadFragment(new ClaimFormFragment());
        }
    }

    // Method to switch fragments (ClaimFormFragment or ClaimHistoryFragment)
    public void loadFragment(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
