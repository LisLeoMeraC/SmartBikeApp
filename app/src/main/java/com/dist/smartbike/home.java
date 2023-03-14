package com.dist.smartbike;

import static com.dist.smartbike.R.id.textView4;
import static com.dist.smartbike.R.id.txtOpcionDay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView IrAFormularioDay = findViewById(txtOpcionDay);
        SharedPreferences sharedPreferences = getSharedPreferences("Credentials", MODE_PRIVATE);

        IrAFormularioDay.setOnClickListener(view -> {
            // Código para llamar al formulario deseado
//            Intent intent = new Intent(home.this, activity_day.class);
//            startActivity(intent);
            String code_session = sharedPreferences.getString("code_session", "");
            Toast.makeText(this, code_session, Toast.LENGTH_SHORT).show();
        });

        TextView IrAInicio = findViewById(textView4);

        IrAInicio.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("code_session");
            editor.apply();
            Intent intent = new Intent(home.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}