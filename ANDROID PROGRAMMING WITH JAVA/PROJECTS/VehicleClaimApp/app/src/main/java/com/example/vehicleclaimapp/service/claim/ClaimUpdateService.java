package com.example.vehicleclaimapp.service.claim;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.example.vehicleclaimapp.database.AppDatabase;
import com.example.vehicleclaimapp.model.Claim;
import com.example.vehicleclaimapp.notification.NotificationHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ClaimUpdateService extends Service {

    private final Handler handler = new Handler();
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = AppDatabase.getInstance(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        simulateRealTimeUpdates();
        return START_STICKY;
    }

    private void simulateRealTimeUpdates() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Simulate receiving data
                String[] statuses = {"Pending", "Approved", "Rejected"};
                String claimId = "CL-" + new Random().nextInt(10000);
                String status = statuses[new Random().nextInt(statuses.length)];
                String details = "Simulated update for claim: " + claimId;
                String dateSubmitted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
             //   String status = "Pending"; // Initial status
                String dateUpdated = dateSubmitted;

              //  Claim newClaim = new Claim( details, status, dateSubmitted, dateUpdated);
                //API call claims

                // Insert simulated data into Room DB
                new Thread(() -> {
                    Claim newClaim = new Claim( details, status, dateSubmitted, dateUpdated);

                    database.claimDao().insertClaim(newClaim);

                }).start();

                // Schedule the next update
                handler.postDelayed(this, 5000); // Repeat every 5 seconds
            }
        }, 5000); // Initial delay of 5 seconds
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null); // Stop updates
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // Not bound
    }
}
