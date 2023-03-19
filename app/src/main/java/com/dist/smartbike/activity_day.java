package com.dist.smartbike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.dist.smartbike.model.Monitoring;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class activity_day extends AppCompatActivity {

    private TextView lblSaludo;
    private TextView lblRecorrido;
    private TextView lblVelocidadMax;
    private TextView lblVelocidadMin;
    private TextView lblAltitud;
    private TextView lblTemperatura;
    private TextView lblTiempo;

    private TextView lblTiempo1;
    private OkHttpClient client;
    private Handler handler;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        lblSaludo = findViewById(R.id.lblSaludo);
        lblRecorrido = findViewById(R.id.lblRecorrido);
        lblVelocidadMax = findViewById(R.id.lblVelocidadMax);
        lblVelocidadMin = findViewById(R.id.lblVelocidadMin);
        lblAltitud = findViewById(R.id.lblAltitud);
        lblTemperatura = findViewById(R.id.lblTemperatura);
        lblTiempo = findViewById(R.id.lblTiempo);

        sharedPreferences = getSharedPreferences("Credentials", MODE_PRIVATE);
        client = new OkHttpClient();
        handler = new Handler();
        handler.postDelayed(runnable, 1000);
    }



    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                String url = "http://192.168.1.4:8080/monitoring/play";
                String code_session = sharedPreferences.getString("code_session", "");
                Request request = new Request.Builder().url(url).addHeader("Authorization", "Basic " + code_session).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        activity_day.this.runOnUiThread(() -> Toast.makeText(activity_day.this, "Error al entrar al servidor", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if(response.isSuccessful()){
                            Gson gson = new Gson();
                            Monitoring monitoring = gson.fromJson(response.body().string(), Monitoring.class);
                            activity_day.this.runOnUiThread(()->{
                                lblSaludo.setText(" Hi "  + monitoring.getUser().getNames()+"!!!");
                                lblRecorrido.setText(" " + monitoring.getTravel() + "Km");
                                lblVelocidadMax.setText(" Speed Max: " + monitoring.getSpeed_max() + "m/s");
                                lblVelocidadMin.setText(" Speed Min: " + monitoring.getSpeed_min() + "m/s");
                                lblAltitud.setText(" Altitude: " + monitoring.getAltitude()+"m");
                                lblTiempo.setText(" Temperature: " + monitoring.getTemperature() + "°C");
                                lblTemperatura.setText(" Time: " + monitoring.getTravel_time()+"min");

                            });
                        }
                    }
                });
            }catch (Exception e) {
                System.out.println(e);
            }
            handler.postDelayed(this, 1000);
        }
    };
}