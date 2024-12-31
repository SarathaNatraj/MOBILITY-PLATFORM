
package com.example.vehicleclaimapp.ui.claim;
//User Story 6: As a user, I want to view my claim history and status updates for my ongoing claims.
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.example.vehicleclaimapp.R;
import com.example.vehicleclaimapp.database.AppDatabase;
import com.example.vehicleclaimapp.model.Claim;
import com.example.vehicleclaimapp.service.claim.ClaimManager;
import com.example.vehicleclaimapp.service.claim.ClaimUpdateService;
import com.example.vehicleclaimapp.ui.about.AboutAppActivity;
import com.example.vehicleclaimapp.ui.about.SettingsActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

public class ClaimSubmissionActivity extends AppCompatActivity {

    private ClaimManager claimManager;
    private EditText etClaimDescription;
    private Button btnSubmitClaim, btnViewClaimHistory,btnUpdateClaim,btnDelClaim;
    private ListView lvClaimHistory;
    private ArrayAdapter<String> claimHistoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  onOptionsItemSelected(true);
        setContentView(R.layout.activity_claim_submission);

        claimManager = new ClaimManager();
        etClaimDescription = findViewById(R.id.etClaimDescription);
        btnSubmitClaim = findViewById(R.id.btnSubmitClaim);
        btnViewClaimHistory = findViewById(R.id.btnViewClaimHistory);
        btnUpdateClaim = findViewById(R.id.btnUpdateClaim);
        btnDelClaim = findViewById(R.id.btnDeleteClaim);
        lvClaimHistory = findViewById(R.id.lvClaimHistory);

        //Fetch the database object
        AppDatabase db = AppDatabase.getInstance(this);

        // Start background service
        Intent serviceIntent = new Intent(this, ClaimUpdateService.class);
        startService(serviceIntent);


        // Set up adapter to display claim history
        claimHistoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        lvClaimHistory.setAdapter(claimHistoryAdapter);

        btnSubmitClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = etClaimDescription.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(ClaimSubmissionActivity.this, "Please enter claim description", Toast.LENGTH_SHORT).show();
                } else {
                    // Create a new claim
                    String claimId = "CLAIM" + (claimManager.getAllClaims().size() + 1); // Unique claim ID
                    String dateSubmitted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    String status = "Pending"; // Initial status
                    String dateUpdated = dateSubmitted;

                    Claim newClaim = new Claim( description, status, dateSubmitted, dateUpdated);


                    Executors.newSingleThreadExecutor().execute(()->{
                        db.claimDao().insertClaim(newClaim);
                    });

                 //   claimManager.addClaim(newClaim);

                    // Clear input and show confirmation
                    etClaimDescription.setText("");
                    Toast.makeText(ClaimSubmissionActivity.this, "Claim submitted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnViewClaimHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display claim history
                ArrayList<String> claimHistoryList = new ArrayList<>();
                /*for (Claim claim : claimManager.getAllClaims()) {
                    claimHistoryList.add(claim.toString());
                }*/
                Executors.newSingleThreadExecutor().execute(()-> {
                    List<Claim> claimList = db.claimDao().getAllClaims();
                    runOnUiThread(()->{
                        for (Claim claim : claimList) {
                            claimHistoryList.add(claim.toString());
                        }
                        claimHistoryAdapter.clear();
                        claimHistoryAdapter.addAll(claimHistoryList);
                        claimHistoryAdapter.notifyDataSetChanged();

                    });

                });
            }
        });
        btnUpdateClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClaimSubmissionActivity.this, UpdateClaimStatusActivity.class));

            }
        });
        btnDelClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClaimSubmissionActivity.this, DeleteClaimActivity.class));

            }
        });
        // Enable the ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Vehicle Claim App"); // Set title
            actionBar.setSubtitle("Track your claims");
            actionBar.setDisplayHomeAsUpEnabled(true); // Enable back button
            actionBar.setIcon(R.drawable.app);
        }
    }
    // Inflate the menu items for the ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;

    }

    // Handle ActionBar item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("ActionBar", "Clicked item: " + item);
        Log.d("ActionBar", "Clicked item - Title: " + item.getTitle()
        );

        if (item.getTitle().toString().equalsIgnoreCase("About App")) {
            Toast.makeText(this, "About App clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ClaimSubmissionActivity.this, AboutAppActivity.class));
            return true;
        }
        if (item.getTitle().toString().equalsIgnoreCase("Settings")){
            Toast.makeText(this, "About App clicked", Toast.LENGTH_SHORT).show();
          startActivity(new Intent(ClaimSubmissionActivity.this, SettingsActivity.class));
        return true;
    }



        return super.onOptionsItemSelected(item);
    }

}
