package com.example.tcc_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaLogin extends AppCompatActivity {

    private Button btnHome;

    private Button btnTelaCadastro;

    EditText edit_email, edit_senha;
    boolean senhaAberta;
    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        setTheme(R.style.Theme_TCC_mobile);
        IniciarComponentes();

        btnTelaCadastro.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaCadastro.class));
        });

        edit_senha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=edit_senha.getRight()-edit_senha.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=edit_senha.getSelectionEnd();
                        if(senhaAberta){
                            edit_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye_another, 0);

                            edit_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            senhaAberta=false;
                        }
                        else {
                            edit_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0);

                            edit_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            senhaAberta=true;
                        }
                        edit_senha.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edit_email.getText().toString();
                String Senha = edit_senha.getText().toString();
                if(Email.isEmpty() || Senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    AutenticarUsuario(v);
                }
            }
        });
    }


    
    private void AutenticarUsuario(View v){
        String Email = edit_email.getText().toString();
        String Senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(Email, Senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                             TelaPrincipal();
                        }
                    }, 1500);
                }else{
                    String erro;

                    try {
                        throw task.getException();

                    }catch (Exception e){
                         erro = "Erro ao logar usuário";
                    }
                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
        if(usuarioAtual != null){
            TelaPrincipal();
        }
    }

    private void TelaPrincipal(){
        Intent intent = new Intent(TelaLogin.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes(){
        edit_senha = findViewById(R.id.textSenha);
        edit_email = findViewById(R.id.Email);
        btnTelaCadastro = findViewById(R.id.btnNãoconta);
        btnHome = findViewById(R.id.btnHome);
    }
}