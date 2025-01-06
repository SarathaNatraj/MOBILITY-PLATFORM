package com.example.vehicleclaimapp_userstory15;



import android.Manifest;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.video.FallbackStrategy;
import androidx.camera.video.MediaStoreOutputOptions;
import androidx.camera.video.PendingRecording;
import androidx.camera.video.Recorder;
import androidx.camera.video.Recording;
import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoOutput;
import androidx.camera.video.VideoRecordEvent;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CameraXVideo";
    private ExecutorService cameraExecutor;
    private VideoCapture<Recorder> videoCapture;
    private Recording currentRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreviewView previewView = findViewById(R.id.previewView);
        Button btnRecord = findViewById(R.id.btnRecord);

        // Request permissions
        ActivityResultLauncher<String[]> permissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                    Boolean cameraGranted = result.get(Manifest.permission.CAMERA);
                    Boolean audioGranted = result.get(Manifest.permission.RECORD_AUDIO);

                    if (cameraGranted != null && cameraGranted && audioGranted != null && audioGranted) {
                        startCamera(previewView, btnRecord);
                    } else {
                        Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

        permissionLauncher.launch(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO});
        cameraExecutor = Executors.newSingleThreadExecutor();
    }

    private void startCamera(PreviewView previewView, Button btnRecord) {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                Recorder recorder = new Recorder.Builder()
                        .setExecutor(cameraExecutor)
                  //      .setQualitySelector(VideoOutput.)
                        .build();

                videoCapture = VideoCapture.withOutput(recorder);
                Preview preview = new Preview.Builder().build();

                preview.setSurfaceProvider(previewView.getSurfaceProvider());


                Camera camera = cameraProvider.bindToLifecycle(this, androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA,
                        videoCapture,
                        preview
                );
                btnRecord.setOnClickListener(view -> toggleRecording(btnRecord));

            } catch (Exception e) {
                Log.e(TAG, "Use case binding failed", e);
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void toggleRecording(Button btnRecord) {
        if (currentRecording != null) {
            // Stop recording
            currentRecording.stop();
            currentRecording = null;
            btnRecord.setText("Record");
        } else {
            //name of the video
            File videoFile = new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES),
                    System.currentTimeMillis() + "_vehicle_damage.mp4");
            ContentValues contentValues = new  ContentValues();
            contentValues.  put(MediaStore.Video.Media.DISPLAY_NAME, videoFile.getName());

            // Start recording -> outputOptions-> output stream in java
            MediaStoreOutputOptions outputOptions;
            outputOptions = new MediaStoreOutputOptions.Builder(getContentResolver(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                    .setContentValues(contentValues) // Set the file name here
                    .build();
            PendingRecording pendingRecording = videoCapture.getOutput().prepareRecording(this, outputOptions);
            currentRecording = pendingRecording.start(ContextCompat.getMainExecutor(this), event -> {
                if (event instanceof VideoRecordEvent.Start) {
                    btnRecord.setText("Stop");
                    //saveVideoToDB()
                    Log.d(TAG, "Recording started");
                } else if (event instanceof VideoRecordEvent.Finalize) {
                    btnRecord.setText("Record");
                    Log.d(TAG, "Recording finalized");
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }
}
