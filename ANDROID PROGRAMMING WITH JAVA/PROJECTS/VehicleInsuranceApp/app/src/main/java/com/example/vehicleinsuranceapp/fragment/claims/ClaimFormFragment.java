package com.example.vehicleinsuranceapp.fragment.claims;



import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.vehicleinsuranceapp.MainActivity;
import com.example.vehicleinsuranceapp.R;
import com.example.vehicleinsuranceapp.database.ClaimDatabase;
import com.example.vehicleinsuranceapp.model.Claim;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClaimFormFragment extends Fragment {

    private EditText vehicleNumberEditText, accidentDetailsEditText, claimAmountEditText;
    private Button submitClaimButton,captureImageButton;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentPhotoPath;
    private ImageView imageView;
    private ClaimDatabase database;


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
        imageView = view.findViewById(R.id.imageView);
        database = ClaimDatabase.getInstance(getActivity().getApplicationContext());

        captureImageButton= view.findViewById(R.id.captureImageButton);
        submitClaimButton = view.findViewById(R.id.submitClaimButton);

        sharedPreferences = getContext().getSharedPreferences("ClaimsData", Context.MODE_PRIVATE);

        captureImageButton.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Log.d("takePictureIntent.resolveActivity(getActivity().getPackageManager()", takePictureIntent.resolveActivity(getActivity().getPackageManager()).toString());
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                dispatchTakePictureIntent();
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "No camera app found", Toast.LENGTH_SHORT).show();
            }

        });
        submitClaimButton.setOnClickListener(v -> insertClaim());

        return view;
    }

    private void insertClaim() {
        new Thread(() -> {
            Claim claim = new Claim();
            claim.setVehicleNumber(vehicleNumberEditText.getText().toString());
            claim.setAccidentDetails(accidentDetailsEditText.getText().toString());
            claim.setClaimAmount(claimAmountEditText.getText().toString());
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            claim.setDate(date);
            claim.setImagePath(currentPhotoPath);

            database.claimDao().insert(claim);
          //  finish();
        }).start();

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
    private void dispatchTakePictureIntent() {
        /*if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 100);
        }
*/


        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = createImageFile();
            if (photoFile != null) {
                currentPhotoPath = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(getActivity().getApplicationContext(), "com.example.vehicleclaimapp.Claims", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp;
        File storageDir = getActivity().getExternalFilesDir("Claims");
        try {
            return File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
