package com.dist.smartbike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.UnsupportedEncodingException;

public class activity_logueoqr extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private TextView cod;
    private ImageView imgqr;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueoqr);
        sharedPreferences = getSharedPreferences("Credentials", MODE_PRIVATE);
        imgqr=findViewById(R.id.imgqr);
        cod=findViewById(R.id.probando);
        try {
            generateQr();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
    public void generateQr () throws UnsupportedEncodingException {
        String code_session = sharedPreferences.getString("code_session", "");



        cod.setText(code_session);

        MainActivity activity= new MainActivity();

       // String texto = "meraleonardo99@gmail.com:12345";
      //  String Codbase64QR = activity.Base64Encoder(texto);

        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(code_session, BarcodeFormat.QR_CODE, 900, 800);
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