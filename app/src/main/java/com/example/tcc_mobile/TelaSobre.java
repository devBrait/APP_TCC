package com.example.tcc_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TelaSobre extends AppCompatActivity {

    private View btnVoltarHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sobre);

        btnVoltarHome = findViewById(R.id.btnVoltarHome);
        btnVoltarHome.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}