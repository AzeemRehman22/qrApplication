package com.example.generateqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.main.qr.EmvQrModel;
import org.main.qr.GenerateEmvQr;

import lombok.ToString;

public class MainActivity extends AppCompatActivity {

    Button btn_generate_qr,btn_scan_qr;
    EditText amount;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_generate_qr = findViewById(R.id.btnGenerateQr);
        btn_scan_qr = findViewById(R.id.btnScanQr);
       // btn_scan_qr = findViewById(R.id.scan_qr);


        btn_generate_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "generat", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, GenerateQr.class);
                startActivity(intent);
            }
        });
        btn_scan_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Scan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ScanQr.class);
                startActivity(intent);
            }
        });
//        amount = findViewById(R.id.et_amount);
//        qrImage = findViewById(R.id.qr_image);
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("###",amount.getText().toString());
//                //Toast.makeText(MainActivity.this, "Please Enter the Amount", Toast.LENGTH_LONG).show();
//                if(!amount.getText().toString().equals("")){
//                        //ScreenName = Utilities.fetchMododuleByName(screen)!=null?Utilities.fetchMododuleByName(screen).getModuleRocDisplay():"";
//                        String amountText = amount.getText().toString();
//                        double Amount = Double.parseDouble(amountText);
//                        //TransactionDetail td = new TransactionDetail();
//                        EmvQrModel mdl = new EmvQrModel();
//                        mdl.setAmex("");
//                        mdl.setAmex2("");
//                        mdl.setUpi("");
//                        mdl.setUpi2("");
//                        mdl.setVisaCardPan("");
//                        mdl.setVisaCardPan2("");
//                        mdl.setDiscover("");
//                        mdl.setDiscover2("");
//                        mdl.setEmvCo("");
//                        mdl.setEmvCo2("");
//                        mdl.setJcb("");
//                        mdl.setJcb2("");
//                        mdl.setMasterCard("");
//                        mdl.setMasterCard2("");
//                        mdl.setCustomPan("98765432112345");
//                        mdl.setCountryCode("PK");
//                        mdl.setMerchantName("merchant Name");
//                        mdl.setMid("MERCHANT_ID");
//                        mdl.setTid("TERMINAL_ID");
//                        mdl.setSerialNumber("Serial");
//                        mdl.setPayloadFormatIndicator("01");
//                        mdl.setPointOfInitiationMethod("12");
//                        mdl.setMerchantCity("Karachi");
//                        //mdl.setMerchantCity(selectedProductID+"|"+ScreenName+"|"+QRExpiry+"|"+Constants.INVOICE_NO+"|"+TMSConstants.ISSUER+"|"+TMSConstants.BATCH_NO);
//                        mdl.setTransactionCurrency("586");
//                        mdl.setTransactionAmount(Double.toString(Amount));
//                        mdl.setMerchantCategoryCode("4111");
//
//                        //Log.e("$$$$",screen+"|"+QRExpiry+"|"+Constants.INVOICE_NO+"|"+TMSConstants.ISSUER);
//                        EmvQrModel responseEmv = new GenerateEmvQr().getGeneratedQr(mdl);
//                        if(responseEmv.getResponseCode().equals("0000")){
//                            String qrText = responseEmv.getResponseMessage();
//                            Toast.makeText(MainActivity.this, "Qr Generated.", Toast.LENGTH_SHORT).show();
//                            new Thread(new Runnable() {
//                                public void run() {
//                                    // Perform background work here
//                                    // Use a Handler to update the UI on the main thread
//                                    Handler handler = new Handler(Looper.getMainLooper());
//                                    handler.post(new Runnable() {
//                                        public void run() {
//                                            // Update UI elements here
//                                            submit.setVisibility(View.GONE);
//                                            amount.setVisibility(View.GONE);
//                                            qrImage.setVisibility(View.VISIBLE);
//
//                                            //amountfield.setVisibility(View.GONE);
//                                            //qr_layout.setVisibility(View.VISIBLE);
//                                            displayQr(qrText);
//                                        }
//                                    });
//                                }
//                            }).start();
//                            //schdulerStart();
//                        }
//                        else{
//                            Toast.makeText(MainActivity.this, "Qr Generation Failed.", Toast.LENGTH_SHORT).show();
//                        }
//
//                }else {
//                    Toast.makeText(MainActivity.this, "Please Enter the Amount", Toast.LENGTH_LONG).show();
//                }
//            }
//        });



    }
    /*private Bitmap generateQRCode(String text) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void displayQr(String qrString){
        // In your fragment:
        Bitmap qrCodeBitmap = generateQRCode(qrString);
        if (qrCodeBitmap != null) {
            // Use the generated bitmap (e.g., display it in an ImageView)
            qrImage.setImageBitmap(qrCodeBitmap);
        } else {
            Log.e("QRCodeGeneration", "Error generating QR code");
        }
    }*/
}