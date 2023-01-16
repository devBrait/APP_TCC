package com.example.tcc_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TelaUser extends AppCompatActivity {

    private View btnVoltar;
    private View btnUser;
    private View imageSair;
    private View btnNovasenha;
    private TextView nomeUsuario;
    private TextView emailUser;
    private FirebaseAuth auth;
    EditText senhaUser;
    private TextView telefoneUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;
    boolean senhaAberta;
    private Button btnSalvar;
    String[] mensagens = {"Dados salvos com sucesso", "Um link foi enviado para o seu e-mail.", "Erro ao enviar e-mail"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_user2);

        IniciarComponentes();

        auth = FirebaseAuth.getInstance();

        senhaUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=senhaUser.getRight()-senhaUser.getCompoundDrawables()[Right].getBounds().width()){
                      int selection=senhaUser.getSelectionEnd();
                      if(senhaAberta){
                          senhaUser.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye_close, 0);

                         senhaUser.setTransformationMethod(PasswordTransformationMethod.getInstance());
                          senhaAberta=false;
                      }
                      else {
                          senhaUser.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eyeblack, 0);

                          senhaUser.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                          senhaAberta=true;
                      }
                        senhaUser.setSelection(selection);
                      return true;
                    }
                }
                return false;
            }
        });

        imageSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaUser.this, TelaLogin.class);
                startActivity(intent);
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                       db.collection("Usuarios").document(usuarioID).update("nome", nomeUsuario.getText().toString(),
                               "telefone", telefoneUser.getText().toString());
                    }
                });

        btnUser = findViewById(R.id.btnUser);
        btnUser.setOnClickListener(view -> {
            startActivity(new Intent(this, AltFoto.class));
        });


        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        btnNovasenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recuperarSenha(v);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String senha = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String telefone = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    nomeUsuario.setText(documentSnapshot.getString( "nome"));
                    emailUser.setText(documentSnapshot.getString( "email"));
                    senhaUser.setText(documentSnapshot.getString( "senha"));
                    telefoneUser.setText(documentSnapshot.getString("telefone"));
                }
            }
        });
    }

    private void IniciarComponentes(){
        nomeUsuario = findViewById(R.id.textNomeuser);
        emailUser = findViewById(R.id.Email);
        senhaUser = findViewById(R.id.textSenha);
        imageSair = findViewById(R.id.ImageSair);
        btnSalvar = findViewById(R.id.btnAlterar);
        telefoneUser = findViewById(R.id.textTelefone);
        btnNovasenha = findViewById(R.id.btnRedefinir);
    }

    private void recuperarSenha(View v){

        String Emailsenha = emailUser.getText().toString();
        auth.sendPasswordResetEmail(Emailsenha).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar snackbar = Snackbar.make(v, mensagens[2], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            }
        });

    }
}