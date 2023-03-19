package com.dist.smartbike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class register_user extends AppCompatActivity {

    private TextInputEditText txtNames;
    private TextInputEditText txtLastNames;
    private TextInputEditText txtEmail;
    private TextInputEditText txtPass;
    private TextInputEditText txtCDI;

    private Button btnRegister;

    private OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        txtNames = findViewById(R.id.edtNombre);
        txtLastNames = findViewById(R.id.edtApellido);
        txtEmail = findViewById(R.id.edtEmail);
        txtPass = findViewById(R.id.edtPassword);
        txtCDI = findViewById(R.id.edtIdentidad);
        btnRegister = findViewById(R.id.btnRegister);

        client = new OkHttpClient();
        String url = "http://192.168.1.4:8080/users/register";

        btnRegister.setOnClickListener(view -> {
            /*
            "names": "Kelvin",
            "last_names": "Estrada",
            "email": "kel@gmail.com",
            "password": "123456",
            "cdi": "1235485236"
            */
            try {
                if(txtNames.getText().toString().isBlank() || txtEmail.getText().toString().isBlank() || txtPass.getText().toString().isBlank() || txtCDI.getText().toString().isBlank()){
                    Toast.makeText(this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
                    return;
                }
                JSONObject formData = new JSONObject();
                formData.put("names", txtNames.getText());
                formData.put("last_names", txtLastNames.getText());
                formData.put("email", txtEmail.getText());
                formData.put("password", txtPass.getText());
                formData.put("cdi", txtCDI.getText());
                RequestBody requestBody = RequestBody.create(formData.toString(), MediaType.parse("application/json; charset=utf-8"));

                Request request = new Request.Builder().url(url).post(requestBody).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        register_user.this.runOnUiThread(() -> Toast.makeText(register_user.this, "Error al entrar al servidor", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()){
                            register_user.this.runOnUiThread(() -> Toast.makeText(register_user.this, "Usuario registrado", Toast.LENGTH_SHORT).show());
                            Intent intent = new Intent(register_user.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            register_user.this.runOnUiThread(() -> Toast.makeText(register_user.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show());
                        }
                    }
                });
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }
}