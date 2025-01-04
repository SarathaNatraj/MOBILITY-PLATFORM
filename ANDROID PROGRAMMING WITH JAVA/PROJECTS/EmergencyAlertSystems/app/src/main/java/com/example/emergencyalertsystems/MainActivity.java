package com.example.emergencyalertsystems;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 101;

    private String emergencyMessage = "Emergency! Please help. Location: Office.";
    private String emergencyContact = "9865702357";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSendSms = findViewById(R.id.btnSendSms);
        Button btnMakeCall = findViewById(R.id.btnMakeCall);

        btnSendSms.setOnClickListener(view -> sendEmergencySms());
        btnMakeCall.setOnClickListener(view -> makeEmergencyCall());
    }

    private void sendEmergencySms() {
        if (checkPermissions(Manifest.permission.SEND_SMS)) {
            if (isNetworkAvailable()) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(emergencyContact, null, emergencyMessage, null, null);
                Toast.makeText(this, "Emergency SMS sent!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No network available!", Toast.LENGTH_SHORT).show();
            }
        } else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS});
        }
    }

    private void makeEmergencyCall() {
        if (checkPermissions(Manifest.permission.CALL_PHONE)) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            //call -tel:, mail - mailto: XXX@gmail.com
            callIntent.setData(android.net.Uri.parse("tel:" + emergencyContact));
            startActivity(callIntent);
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE});
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private boolean checkPermissions(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions(String[] permissions) {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
