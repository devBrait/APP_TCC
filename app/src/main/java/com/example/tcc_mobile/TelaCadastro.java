package com.example.tcc_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TelaCadastro extends AppCompatActivity {

    private Button btnHome;

    private Button btnTelaLogin;

    private EditText editNome;
    EditText editSenha;
    private EditText editEmail;
    private EditText editTelefone;
    private Button btnFoto;
    private Uri mSelectUri;
    private ImageView imageFoto;
    boolean senhaAberta;
    String usuarioID;
    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        IniciarComponentes();

        editSenha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=editSenha.getRight()-editSenha.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=editSenha.getSelectionEnd();
                        if(senhaAberta){
                            editSenha.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye_another, 0);

                            editSenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            senhaAberta=false;
                        }
                        else {
                            editSenha.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0);

                            editSenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            senhaAberta=true;
                        }
                        editSenha.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


        btnTelaLogin.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaLogin.class));
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nome = editNome.getText().toString();
                String Email = editEmail.getText().toString();
                String Senha = editSenha.getText().toString();

                if(Nome.isEmpty() || Email.isEmpty() || Senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    CadastrarUsuario(v);
                }
            }
        });
    }


    private void CadastrarUsuario(View v){
        String Nome = editNome.getText().toString();
        String Email = editEmail.getText().toString();
        String Senha = editSenha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email, Senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    IniciarComponentes();

                    SalvarDadosUsuario();

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

                }else {
                     String erro;

                     try {
                         throw task.getException();
                     }
                     catch (FirebaseAuthWeakPasswordException e){
                          erro = "Digite uma senha com no mínimo 6 caracteres";
                     }
                     catch (FirebaseAuthUserCollisionException e){
                         erro = "Esta conta já existe";

                     }catch (FirebaseAuthInvalidCredentialsException e){
                         erro = "E-mail inválido";
                     }
                     catch (Exception e){
                          erro = "Erro ao cadastrar usuário";
                     }
                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void IniciarComponentes(){

        editNome = findViewById(R.id.textNome);
        editSenha = findViewById(R.id.textSenha);
        editEmail = findViewById(R.id.Email);
        btnHome = findViewById(R.id.btnHome);
        btnTelaLogin = findViewById(R.id.btnTelaLogin);
        editTelefone = findViewById(R.id.textTelefone);

    }

    private void SalvarDadosUsuario(){

        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String telefone = editTelefone.getText().toString();
        String senha = editSenha.getText().toString();
      FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);
        usuarios.put("email", email);
        usuarios.put("telefone", telefone);
        usuarios.put("senha", senha);
        usuarios.put("nomePeixes", "");
        usuarios.put("pHPeixes", "");

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("db", "Sucesso ao salvar os dados");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                    }
                });

    }

    private void TelaPrincipal(){
        Intent intent = new Intent(TelaCadastro.this, TelaLogin.class);
        startActivity(intent);
        finish();
    }
}