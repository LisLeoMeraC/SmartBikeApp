package com.dist.smartbike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dist.smartbike.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView btnRegister;
    private TextInputEditText txtEmail;
    private TextInputEditText txtPass;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Credentials", MODE_PRIVATE);
        if (sharedPreferences.contains("code_session")){
            Intent intent = new Intent(MainActivity.this, home.class);
            startActivity(intent);
            finish();
        }

        btnLogin = findViewById(R.id.btnIniciarSesion);
        btnRegister = findViewById(R.id.txtNuevoUsuario);
        txtEmail = findViewById(R.id.edtMail);
        txtPass = findViewById(R.id.edtPassword);
        client = new OkHttpClient();

        String url = "http://192.168.0.105:8080/users";

        btnRegister.setOnClickListener(v -> {
            // Código para llamar al formulario deseado
            Intent intent = new Intent(MainActivity.this, register_user.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(view -> {
            // Codigo para iniciar sesión
            String credentials = null;
            try {
                credentials = Base64Encoder(txtEmail.getText() + ":" + txtPass.getText());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            Request request = new Request.Builder().url(url).addHeader("Authorization", "Basic " + credentials).build();
            String finalCredentials = credentials;
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    MainActivity.this.runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error al entrar al servidor", Toast.LENGTH_SHORT).show());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        User user = gson.fromJson(response.body().string(), User.class);
                        user.setCode_session(finalCredentials);
                        // Guardar el inicio de sesión en memoria
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("code_session", user.getCode_session());
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this, home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        MainActivity.this.runOnUiThread(() -> {
                            Toast.makeText(MainActivity.this, "Usuario y contraseña incorrecto", Toast.LENGTH_SHORT).show();
                        });
                    }
                }
            });
        });
    }

    public String Base64Encoder(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes();
        byte[] encodedBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedBytes = Base64.getEncoder().encode(bytes);
        }
        return new String(encodedBytes);
    }
}