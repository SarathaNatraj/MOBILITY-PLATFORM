package com.example.vehicleclaimapp.ui.claim;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vehicleclaimapp.R;
import com.example.vehicleclaimapp.database.AppDatabase;
import com.example.vehicleclaimapp.model.Claim;

import java.util.concurrent.Executors;

public class DeleteClaimActivity extends AppCompatActivity {
    private EditText etClaimId;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_claim);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etClaimId = findViewById(R.id.etClaimId_Del);
        btnDelete = findViewById(R.id.btnDelete);
        //Fetch the database object
        AppDatabase db = AppDatabase.getInstance(this);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showDeleteConfirmationDialog(db, etClaimId);

            }
        });
    }

    private void showDeleteConfirmationDialog(AppDatabase db, EditText etClaimId) {
        //build dialog object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Confirmation");
        builder.setMessage("Are you sure to delete the claim ?");
        int claimId = Integer.parseInt(etClaimId.getText().toString());

        builder.setPositiveButton("Delete", (dialog, which)->{
            Executors.newSingleThreadExecutor().execute(()-> {
                Claim claim = db.claimDao().getClaimById(claimId);
                if(claim != null) {
                    db.claimDao().deleteClaim(claim);
                    runOnUiThread(() -> {
                        Toast.makeText(DeleteClaimActivity.this, "Claim  de successfully!", Toast.LENGTH_SHORT).show();
                    });
                }else {
                    runOnUiThread(() -> {
                        Toast.makeText(DeleteClaimActivity.this, "Claim " + claimId + " not available !", Toast.LENGTH_SHORT).show();
                    });
                }
            });

        });

        builder.setNegativeButton("Cancel", (dialog,which)-> dialog.dismiss());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

}
