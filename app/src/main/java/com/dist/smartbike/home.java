package com.dist.smartbike;

import static com.dist.smartbike.R.id.txtOpcionDay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView IrAFormularioDay = findViewById(txtOpcionDay);
        IrAFormularioDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para llamar al formulario deseado
                Intent intent = new Intent(home.this, activity_day.class);
                startActivity(intent);
            }
        });
    }
}