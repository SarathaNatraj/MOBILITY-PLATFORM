package com.example.vehicleclaimapp.ui.claim;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vehicleclaimapp.R;
import com.example.vehicleclaimapp.database.AppDatabase;
import com.example.vehicleclaimapp.model.Claim;
import com.example.vehicleclaimapp.notification.NotificationHelper;
import com.example.vehicleclaimapp.service.claim.ClaimManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

public class UpdateClaimStatusActivity extends AppCompatActivity {
    private ClaimManager claimManager;
    private EditText etClaimId, etNewStatus;
    private Button btnUpdateStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_claim_status);

        claimManager = new ClaimManager();
        etClaimId = findViewById(R.id.etClaimId);
        etNewStatus = findViewById(R.id.etNewStatus);
        btnUpdateStatus = findViewById(R.id.btnUpdateStatus);
        //Fetch the database object
        AppDatabase db = AppDatabase.getInstance(this);
        NotificationHelper.createNotificationChannel(getApplicationContext());


        btnUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int claimId = Integer.parseInt(etClaimId.getText().toString());
                String newStatus = etNewStatus.getText().toString();
                String updatedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                // Update the claim status
    //            claimManager.updateClaimStatus(claimId, newStatus, updatedDate);
                Executors.newSingleThreadExecutor().execute(()-> {
                    Claim existingClaim = db.claimDao().getClaimById(claimId);
                    if (existingClaim != null) {
                        existingClaim.setStatus(newStatus);
                        existingClaim.setDateUpdated(updatedDate);
                        db.claimDao().updateClaim(existingClaim);
                        runOnUiThread(() -> {
                            // Send a notification when the claim is updated

                            NotificationHelper.sendNotification(getApplicationContext(), existingClaim.getStatus());

                            Toast.makeText(UpdateClaimStatusActivity.this, "Claim Status Updated: " + existingClaim.getStatus(), Toast.LENGTH_SHORT).show();

                           // Toast.makeText(UpdateClaimStatusActivity.this, "Claim status updated", Toast.LENGTH_SHORT).show();
                        });

                    } else {
                        runOnUiThread(() -> {
                            Toast.makeText(UpdateClaimStatusActivity.this, "Claim " + claimId + " not available !", Toast.LENGTH_SHORT).show();
                        });
                    }

                });
            }
        });
    }
}
