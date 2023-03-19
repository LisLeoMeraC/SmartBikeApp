package com.dist.smartbike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class activity_logueoqr extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private TextView codigo;
    private ImageView imgqr;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueoqr);
        sharedPreferences = getSharedPreferences("Credentials", MODE_PRIVATE);
        imgqr=findViewById(R.id.imgqr);
        generateQr();

    }
    public void generateQr (){
        String code_session = sharedPreferences.getString("code_session", "");
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(code_session, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x,y) ? Color.BLACK : Color.WHITE);
                }
            }
            imgqr.setImageBitmap(bitmap);
        }
        catch (WriterException e) {
            Log.e("Error al generar el código QR", e.getMessage(), e);
        }
    }

}