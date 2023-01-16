package com.example.tcc_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AltFoto extends AppCompatActivity {

    private View btnVolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_foto);

        btnVolta = findViewById(R.id.btnVoltarHome);
        btnVolta.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaUser.class));
        });
    }
}