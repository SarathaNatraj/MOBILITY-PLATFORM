package com.example.vehicleclaimapp_userstory3.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraProvider;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;

import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;

import com.example.vehicleclaimapp_userstory3.R;
import com.example.vehicleclaimapp_userstory3.adapter.ClaimAdapter;
import com.example.vehicleclaimapp_userstory3.adapter.ClaimImageAdapter;
import com.example.vehicleclaimapp_userstory3.dao.ClaimImageDao;
import com.example.vehicleclaimapp_userstory3.database.ClaimDatabase;
import com.example.vehicleclaimapp_userstory3.model.Claim;
import com.example.vehicleclaimapp_userstory3.model.ClaimImage;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri imageUri;
    private ClaimDatabase database;
    private RecyclerView recyclerView, imagesRecyclerView;
    private ClaimAdapter adapter;
    private Button capButton;
    private PreviewView previewView;

    private static final int pic_id = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = ClaimDatabase.getInstance(this);
        //recyclerView = findViewById(R.id.recyclerView);
        imagesRecyclerView = findViewById(R.id.claimimagerecyclerView);
        imagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        capButton = findViewById(R.id.captureButton);
        previewView = findViewById(R.id.previewView);

        loadClaims();
        displayImages("CLAIM_ID_123");

        Button addClaimButton = findViewById(R.id.addClaimButton);
        try {
            checkAndRequestCameraPermission();
        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {


                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                Preview preview = new Preview.Builder().build();

                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                ImageCapture imageCapture = new ImageCapture.Builder().build();

                cameraProviderFuture.get().bindToLifecycle(this, cameraSelector, preview, imageCapture);
                capButton.setOnClickListener(v -> takePicture(imageCapture));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }


    private void takePicture(ImageCapture imageCapture) {
        if (imageCapture == null) {
            Toast.makeText(this, "Camera not ready", Toast.LENGTH_SHORT).show();
            return;
        }else{
            Toast.makeText(this, "Camera  ready", Toast.LENGTH_SHORT).show();
        }

        File photoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                System.currentTimeMillis() + "_vehicle_damage.jpg");

        ImageCapture.OutputFileOptions options = new ImageCapture.OutputFileOptions.Builder(photoFile).build();//building the image photo file

        ///storage/emulated/0/Android/data/com.example.vehicleclaimapp_userstory3/files/Pictures/
        imageCapture.takePicture(options, ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        String savedUri = photoFile.getAbsolutePath();
                        Log.d("savedURi path",savedUri);
                        saveImageToDb("CLAIM_ID_123", savedUri); // Replace with your claim ID
                        displayImages("CLAIM_ID_123");
                        Toast.makeText(MainActivity.this, "Image saved"+savedUri, Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        exception.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error saving image", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveImageToDb(String claimId, String imagePath) {
        ClaimImageDao dao = database.claimImageDao();

        new Thread(() -> {
            ClaimImage claimImage = new ClaimImage();
            claimImage.setClaimId(claimId);
            claimImage.setImagePath(imagePath);
            dao.insertImage(claimImage);//insert into claim_image(id,claim_id,imagePath)
        }).start();
    }


    private void loadClaims() {
        new Thread(() -> {
            List<Claim> claims = database.claimDao().getAllClaims();
            runOnUiThread(() -> {
                adapter = new ClaimAdapter(this, claims);
            //    recyclerView.setAdapter(adapter);
            });
        }).start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        startCamera(); // Ensure the camera is initialized
    }




    private static final int REQUEST_CAMERA_PERMISSION = 100;

    private void checkAndRequestCameraPermission() throws ExecutionException, InterruptedException {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            startCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    startCamera();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                Toast.makeText(this, "Camera permission is required to use this feature.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void displayImages(String claimId) {
      //  AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "claim_db").build();
        ClaimImageDao dao = database.claimImageDao();

        new Thread(() -> {
            List<ClaimImage> imagesList = new ArrayList<>();
            List<ClaimImage> images = dao.getImagesByClaimId(claimId);
            runOnUiThread(() -> {
                for (ClaimImage image : images) {
                    Log.d("Image", "Path: " + image.getImagePath());
                    // Use the paths to load images into an ImageView or RecyclerView
                    imagesList.add(image);
                }
                ClaimImageAdapter claimImageAdapter =new ClaimImageAdapter(this,images);
                imagesRecyclerView.setAdapter(claimImageAdapter);
                // Notify the adapter
                claimImageAdapter.notifyItemInserted(imagesList.size());


            });
        }).start();
    }

}
