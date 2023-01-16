package com.example.tcc_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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

import java.util.HashMap;
import java.util.Map;


public class AddPeixes extends AppCompatActivity {

    private ImageButton btnAddBetta;
    private View btnTelaBetta, btnTelaNeon, btnTelaKing, btnTelaLebiste, btnTelaCarpa, btnTelaAcara, btnVoltar;
    private ImageButton btnAddNeon;
    private ImageButton btnAddKing;
    private ImageButton btnAddLebiste;
    private ImageButton btnAddCarpa;
    private ImageButton btnAddAcara;
    private TextView txtNomebetta;
    private TextView txtNome2betta;
    private TextView txtNeon;
    private TextView txtNeon2;
    private TextView txtKing;
    private TextView txtKing2;
    private TextView txtLebiste;
    private TextView txtLebiste2;
    private TextView txtCarpa;
    private TextView txtCarpa2;
    private TextView txtAcara;
    private TextView txtAcara2;
    private TextView peixebanco, txtBetta2, txtBettapH, pHbanco, txtNeon3, txtNeonpH, txtKing3, txtKingpH, txtLebiste3, txtLebistepH, txtCarpa3, txtCarpapH, txtAcara3, txtAcarapH;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;
    String[] mensagens = {"Esse peixe já foi adicionado(a)", "Peixe adicionado com sucesso."};
    double contBetta, contNeon, contKing, contLebiste, contCarpa, contAcara;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_peixes);

        IniciarComponentes();

        btnTelaBetta.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaBetta.class));
        });

        btnTelaNeon.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaNeon.class));
        });

        btnTelaKing.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaKing.class));
        });

        btnTelaLebiste.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaLebiste.class));
        });

        btnTelaCarpa.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaCarpa.class));
        });

        btnTelaAcara.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaAcara.class));
        });

        btnVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaPeixes.class));
        });



        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String nomepeixe = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    peixebanco.setText(documentSnapshot.getString( "nomePeixes"));
                    pHbanco.setText(documentSnapshot.getString("pHPeixes"));
                    // PEGA O VALOR DO NOME E PH NO FIREBASE
                    String nomeBanco = peixebanco.getText().toString();
                    String pHBanco = pHbanco.getText().toString();
                    // SOMA NOME DO BETTA
                    String SomaBetta2 = txtNomebetta.getText().toString();
                    String SomaBetta3 = nomeBanco+SomaBetta2;
                    txtBetta2.setText(SomaBetta3);
                    // SOMA PH DO BETTA
                    String SomapHB2 = "6,8 à 7,4."+" ";
                    String SomapHB3 = pHBanco+SomapHB2;
                    txtBettapH.setText(SomapHB3);
                    // SOMA NOME DO NEON
                    String SomaNeon2 = txtNeon.getText().toString();
                    String SomaNeon3 = nomeBanco+SomaNeon2;
                    txtNeon3.setText(SomaNeon3);
                    // SOMA PH DO NEON
                    String SomapHN2 = "6,0 à 7,0."+" ";
                    String SomapHN3 = pHBanco+SomapHN2;
                    txtNeonpH.setText(SomapHN3);
                    // SOMA NOME DO KINGUIO
                    String SomaKing = txtKing.getText().toString();
                    String SomaKing2 = nomeBanco+SomaKing;
                    txtKing3.setText(SomaKing2);
                    // SOMA PH DO KINGUIO
                    String SomapHK = "6,8 à 7,5."+" ";
                    String SomapHK2 = pHBanco+SomapHK;
                    txtKingpH.setText(SomapHK2);
                    // SOMA NOME DO LEBISTE
                    String SomaLebiste = txtLebiste.getText().toString();
                    String SomaLebiste2 = nomeBanco+SomaLebiste;
                    txtLebiste3.setText(SomaLebiste2);
                    // SOMA PH DO LEBISTE
                    String SomapHL = "7,0 à 7,8."+" ";
                    String SomapHL2 = pHBanco+SomapHL;
                    txtLebistepH.setText(SomapHL2);
                    // SOMA NOME DO CARPA
                    String SomaCarpa = txtCarpa.getText().toString();
                    String SomaCarpa2 = nomeBanco+SomaCarpa;
                    txtCarpa3.setText(SomaCarpa2);
                    // SOMA PH DO CARPA
                    String SomapHC = "7,0 à 7,4."+" ";
                    String SomapHC2 = pHBanco+SomapHC;
                    txtCarpapH.setText(SomapHC2);
                    // SOMA NOME DO ACARA
                    String SomaAcara = txtAcara.getText().toString();
                    String SomaAcara2 = nomeBanco+SomaAcara;
                    txtAcara3.setText(SomaAcara2);
                    // SOMA PH DO ACARA
                    String SomapHA = "6,8 à 7,2."+" ";
                    String SomapHA2 = pHBanco+SomapHA;
                    txtAcarapH.setText(SomapHA2);
                }
            }
        });



        btnAddBetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contBetta++;
                if(contBetta > 1){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                else{
                        SalvarBetta();
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
        }
        });

        btnAddNeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contNeon++;
                if(contNeon > 1){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                else {
                    SalvarNeon();
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });

        btnAddKing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contKing++;
                if(contKing > 1){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                else {
                    SalvarKing();
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });

        btnAddLebiste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contLebiste++;
                if(contLebiste > 1){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                else {
                    SalvarLebiste();
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });

        btnAddCarpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contCarpa++;
                if(contCarpa > 1){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                else {
                    SalvarCarpa();
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });

        btnAddAcara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contAcara++;
                if(contAcara > 1){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                else {
                    SalvarAcara();
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void IniciarComponentes(){
        btnAddBetta = findViewById(R.id.btnAdicionarBetta);
        btnAddNeon = findViewById(R.id.btnAdicionarNeon);
        btnAddKing = findViewById(R.id.btnAdicionarKinguio);
        btnAddLebiste = findViewById(R.id.btnAdicionarLebiste);
        btnAddCarpa = findViewById(R.id.btnAdicionarCarpa);
        btnAddAcara = findViewById(R.id.btnAdicionarAcara);
        txtNomebetta = findViewById(R.id.myText1);
        txtNome2betta = findViewById(R.id.myText2);
        txtNeon = findViewById(R.id.txtNeon);
        txtNeon2 = findViewById(R.id.txtNeon2);
        txtKing = findViewById(R.id.txtKinguio);
        txtKing2 = findViewById(R.id.txtKinguio2);
        txtLebiste = findViewById(R.id.txtLebiste);
        txtLebiste2 = findViewById(R.id.txtLebiste2);
        txtCarpa = findViewById(R.id.txtCarpa);
        txtCarpa2 = findViewById(R.id.txtCarpa2);
        txtAcara = findViewById(R.id.txtAcara);
        txtAcara2 = findViewById(R.id.txtAcara2);
        peixebanco = findViewById(R.id.Nomebanco);
        txtBetta2 = findViewById(R.id.SomaBetta2);
        txtBettapH = findViewById(R.id.SomaphB);
        pHbanco = findViewById(R.id.pHbanco);
        btnTelaBetta = findViewById(R.id.TelaBetta);
        btnTelaNeon = findViewById(R.id.imgNeon);
        btnTelaKing = findViewById(R.id.imgKinguio);
        btnTelaLebiste = findViewById(R.id.imgLebiste);
        btnTelaCarpa = findViewById(R.id.imgCarpa);
        btnTelaAcara = findViewById(R.id.imgAcara);
        txtNeon3 = findViewById(R.id.Neonbanco);
        txtNeonpH = findViewById(R.id.NeonpH);
        txtKing3 = findViewById(R.id.Kingbanco);
        txtKingpH = findViewById(R.id.KingpH);
        txtLebiste3 = findViewById(R.id.Lebistebanco);
        txtLebistepH = findViewById(R.id.LebistepH);
        txtCarpa3 = findViewById(R.id.Carpabanco);
        txtCarpapH = findViewById(R.id.CarpapH);
        txtAcara3 = findViewById(R.id.Acarabanco);
        txtAcarapH = findViewById(R.id.AcarapH);
        btnVoltar = findViewById(R.id.btnVoltarHome);
    }


// PROCESSO PARA ADICIONAR PEIXE BETTA
    private void SalvarBetta(){
        String Verbranco = peixebanco.getText().toString();
        if(Verbranco == ""){
            String nomeBetta = txtNomebetta.getText().toString();
            String pHBetta = txtNome2betta.getText().toString();

            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);

            db.collection("Usuarios").document("nomePeixes");

            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", nomeBetta);
            map.put("pHPeixes", "6,8 à 7,4."+"  ");

            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }
        else {

            String sBetta = txtBetta2.getText().toString();
            String pHBetta = txtBettapH.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);
            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", sBetta);
            map.put("pHPeixes", pHBetta);

            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }
    }


// PROCESSO PARA ADICIONAR PEIXE TETRA NEON
    private void SalvarNeon(){
        String Verbranco = peixebanco.getText().toString();
        if(Verbranco == "") {
            String nomeNeon = txtNeon.getText().toString();
            String pHNeon = txtNeon2.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);

            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", nomeNeon);
            map.put("pHPeixes", "6,0 à 7,0."+"  ");


            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }else{

            String sNeon = txtNeon3.getText().toString();
            String pHNeon = txtNeonpH.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);
            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", sNeon);
            map.put("pHPeixes", pHNeon);

            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }
    }


// PROCESSO PARA ADICIONAR PEIXE KINGUIO
    private void SalvarKing(){
        String Verbranco = peixebanco.getText().toString();
        if(Verbranco == "") {
            String nomeKing = txtKing.getText().toString();
            String pHKing = txtKing2.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);

            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", nomeKing);
            map.put("pHPeixes", "6,8 à 7,5."+"  ");


            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }else{

            String sKing = txtKing3.getText().toString();
            String pHKing = txtKingpH.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);
            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", sKing);
            map.put("pHPeixes", pHKing);

            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }
    }


// PROCESSO PARA ADICIONAR PEIXE LEBISTE
    private void SalvarLebiste(){
        String Verbranco = peixebanco.getText().toString();
        if(Verbranco == "") {
            String nomeLebiste = txtLebiste.getText().toString();
            String pHLebiste = txtLebiste2.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);

            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", nomeLebiste);
            map.put("pHPeixes", "7,0 à 7,8."+"  ");


            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }else{
            String sLebiste = txtLebiste3.getText().toString();
            String pHLebiste = txtLebistepH.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);
            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", sLebiste);
            map.put("pHPeixes", pHLebiste);

            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }
    }


// PROCESSO PARA ADICIONAR PEIXE CARPA
    private void SalvarCarpa(){
        String Verbranco = peixebanco.getText().toString();
        if(Verbranco == "") {
            String nomeCarpa = txtCarpa.getText().toString();
            String pHCarpa = txtCarpa2.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);

            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", nomeCarpa);
            map.put("pHPeixes", "7,0 à 7,4."+"  ");


            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }else{
            String sCarpa = txtCarpa3.getText().toString();
            String pHCarpa = txtCarpapH.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);
            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", sCarpa);
            map.put("pHPeixes", pHCarpa);

            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }
    }


// PROCESSO PARA ADICIONAR PEIXE ACARA
    private void SalvarAcara() {
        String Verbranco = peixebanco.getText().toString();
        if (Verbranco == "") {
            String nomeAcara = txtAcara.getText().toString();
            String pHAcara = txtAcara2.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);

            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", nomeAcara);
            map.put("pHPeixes", "6,8 à 7,2."+"  ");


            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }
        else{
            String sAcara = txtAcara3.getText().toString();
            String pHAcara = txtAcarapH.getText().toString();
            usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                    .document(usuarioID);
            Map<String, Object> map = new HashMap<>();
            map.put("nomePeixes", sAcara);
            map.put("pHPeixes", pHAcara);

            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao salvar os dados");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                }
            });
        }
    }
}