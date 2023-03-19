package com.dist.smartbike;

import static com.dist.smartbike.R.id.cerrarSesion;
import static com.dist.smartbike.R.id.textView4;
import static com.dist.smartbike.R.id.txtOpcionDay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView IrAFormularioDay = findViewById(txtOpcionDay);

        SharedPreferences sharedPreferences = getSharedPreferences("Credentials", MODE_PRIVATE);




        IrAFormularioDay.setOnClickListener(view -> {

             String code_session = sharedPreferences.getString("code_session", "");
             //Toast.makeText(this, code_session, Toast.LENGTH_SHORT).show();

            boolean islogueado= sharedPreferences.getBoolean("esta_logueado",false);

            //Validar si esta logueado el usuario
            if(islogueado){
                Intent intent= new Intent(home.this, activity_logueoqr.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(home.this, activity_logueoqr.class);
                startActivity(intent);
            }
        });





        Button irAIncio= findViewById(cerrarSesion);
        irAIncio.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("code_session");
            editor.apply();
            Intent intent = new Intent(home.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}