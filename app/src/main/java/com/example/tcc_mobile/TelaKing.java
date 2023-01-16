package com.example.tcc_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TelaKing extends AppCompatActivity {

    private View btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_king);

        btnVoltar = findViewById(R.id.btnVoltarHome);
        btnVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, AddPeixes.class));
        });
    }
}