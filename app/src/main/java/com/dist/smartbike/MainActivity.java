package com.dist.smartbike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView IrAFormularioRegistro = findViewById(R.id.txtNuevoUsuario);
        IrAFormularioRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para llamar al formulario deseado
                Intent intent = new Intent(MainActivity.this, register_user.class);
                startActivity(intent);
            }
        });

        Button irMain= findViewById(R.id.btnIniciarSesion);
        irMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, home.class);
                startActivity((intent));
            }
        });
    }
}