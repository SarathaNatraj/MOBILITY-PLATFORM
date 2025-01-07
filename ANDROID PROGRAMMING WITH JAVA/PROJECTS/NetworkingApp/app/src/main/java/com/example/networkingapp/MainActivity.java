package com.example.networkingapp;

import android.Manifest;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private WifiManager wifiManager;
    private NfcAdapter nfcAdapter;
    private TextView resultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        Button bluetoothButton = findViewById(R.id.btnBluetooth);
        Button wifiButton = findViewById(R.id.btnWifi);
        Button nfcButton = findViewById(R.id.btnNfc);
        resultsTextView = findViewById(R.id.txtResults);

        // Initialize Bluetooth adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Initialize Wi-Fi manager
        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        // Initialize NFC adapter
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // Bluetooth scanning functionality
        bluetoothButton.setOnClickListener(v -> scanBluetoothDevices());

        // Wi-Fi connection functionality
        wifiButton.setOnClickListener(v -> connectToWifi());

        // NFC reading functionality
        nfcButton.setOnClickListener(v -> readNfcTag());
    }

    private void scanBluetoothDevices() {
        if (bluetoothAdapter == null) {
            resultsTextView.setText("Bluetooth is not supported on this device.");
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            resultsTextView.setText("Bluetooth is off. Please turn it on.");
            return;
        }
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, 101);

        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 100);


            // return;
        }

            Log.d("After Bluetooth Check ", " afterblue");
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            StringBuilder deviceList = new StringBuilder("Paired Bluetooth devices:\n");
            for (BluetoothDevice device : pairedDevices) {
                deviceList.append(device.getName()).append("\n");
            }
            resultsTextView.setText(deviceList.toString());

            // Discover new devices
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(bluetoothReceiver, filter);
            bluetoothAdapter.startDiscovery();

    }

    private void connectToWifi() {
        WifiInfo currentWifi = wifiManager.getConnectionInfo();
        String currentNetwork = currentWifi.getMacAddress();
        resultsTextView.setText("Connected to Wi-Fi: " + currentNetwork);
    }

    private void readNfcTag() {
        if (nfcAdapter == null || !nfcAdapter.isEnabled()) {
            resultsTextView.setText("NFC is not available or turned off.");
            return;
        }
        resultsTextView.setText("Please scan an NFC tag.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, PendingIntent.getActivity(this, 0, new Intent(this, getClass()), PendingIntent.FLAG_IMMUTABLE),
                    null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    // Bluetooth receiver to handle found devices
    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                String deviceName = device.getName();
                String deviceAddress = device.getAddress(); // MAC address
                resultsTextView.append("Found device: " + deviceName + " (" + deviceAddress + ")\n");
            }
        }
    };

    // Handle NFC tags when scanned
    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String tagId = bytesToHex(tag.getId());
            resultsTextView.setText("NFC Tag Scanned: " + tagId);
        }
    }

    // Convert NFC tag byte array to Hex
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }
}
