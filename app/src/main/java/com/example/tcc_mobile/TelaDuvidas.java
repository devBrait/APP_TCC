package com.example.tcc_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Properties;

public class TelaDuvidas extends AppCompatActivity {

    private View btnVolta;

    public EditText txtDuvida;

    private Button btnEnviar;

    public EditText txtTitulo;

    public TextView txtDestino;

    String destino = "lorimiervb@gmail.com";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;

    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_duvidas);

        IniciarComponentes();

        btnVolta.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = txtDuvida.getText().toString();
                String titulo = txtTitulo.getText().toString().trim();
                if(Email.isEmpty() || titulo.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    showDialog(v);
                    sendMail();
                }
            }
        });

    }
    private void showDialog(View v){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_popup);
        dialog.show();

    }

    private void sendMail(){

        String email = txtDuvida.getText().toString();
        String titulo = txtTitulo.getText().toString().trim();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, destino, titulo, email);

        javaMailAPI.execute();
    }

    private void IniciarComponentes(){
        btnVolta = findViewById(R.id.btnVoltarHome);
        btnEnviar = findViewById(R.id.btnEnviar);
        txtDuvida = findViewById(R.id.txtDuvida);
        txtTitulo = findViewById(R.id.Titulomail);
    }
}