package com.example.generateqr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class ScanQr extends AppCompatActivity {

    //private CompoundBarcodeView barcodeScannerView;
    TextView textView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textView = findViewById(R.id.qr_text);

        //barcodeScannerView = findViewById(R.id.cameraPreview);

        // Initialize the ZXing IntentIntegrator
        IntentIntegrator integrator = new IntentIntegrator(this);
        //integrator.setOrientationLocked(false);  // Allow both portrait and landscape scanning
        integrator.initiateScan();  // Start QR code scanning
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Set screen orientation to landscape each time the activity resumes
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Get the scan results
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        // Check if the result is valid
        if (result != null) {
            if (result.getContents() == null) {
                // Handle canceled scan
                Toast.makeText(this, "Scan canceled", Toast.LENGTH_SHORT).show();
            } else {
                // Handle successful scan
                String scannedData = result.getContents();
                textView.setVisibility(View.VISIBLE);
                textView.setText(scannedData);

                Log.d("QRScannerActivity", "Scanned data: " + scannedData);

                // You can now use the scannedData as needed
                // For example, you might want to send it to another activity or perform some action
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}