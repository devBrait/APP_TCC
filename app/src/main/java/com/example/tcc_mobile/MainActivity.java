package com.example.tcc_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

     private View btnUser;

    private View btnHelp;

    private View btnSobre;

    private Button btnPeixe;

    private Button btnPH;

    private Button btnTDS;

    private Button btnTemp;

    private TextView txtPh, txtTDS, txtTemp;

   // String pH, TDS, Temp;
    double TDSZero, phZero;
    private TextView nomeUsuario;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenceEntrada1 = database.getReference("SENSORES");
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IniciarComponentes();

        referenceEntrada1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pH = snapshot.child("pH").getValue().toString();
                txtPh.setText(pH);
               String TDS = snapshot.child("Tds").getValue().toString();
                txtTDS.setText(TDS);

                TDSZero = Double.parseDouble(TDS);
                phZero = Double.parseDouble(pH);

                if(TDSZero == 0){
                    txtTDS.setTextColor(getResources().getColor(R.color.black));
                }else{
                    if(TDSZero <= 900 ){
                        txtTDS.setTextColor(getResources().getColor(R.color.green));
                    }
                    else{
                        txtTDS.setTextColor(getResources().getColor(R.color.red));
                    }
                }

                if(phZero == 0){
                    txtPh.setTextColor(getResources().getColor(R.color.black));
                }else{
                    if(phZero <= 3.9 ){
                        txtPh.setTextColor(getResources().getColor(R.color.red));
                    }
                    else{
                        txtPh.setTextColor(getResources().getColor(R.color.green));
                    }
                }
                String Temp = snapshot.child("Temperatura").getValue().toString();
                txtTemp.setText(Temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnUser = findViewById(R.id.btnUser);
        btnUser.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaUser.class));
        });

        btnSobre = findViewById(R.id.btnSobre);
        btnSobre.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaSobre.class));
        });

        btnTemp = findViewById(R.id.btnTemp);
        btnTemp.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaTemp.class));
        });

        btnTDS = findViewById(R.id.btnTDS);
        btnTDS.setOnClickListener(view ->{
            startActivity(new Intent(this, TelaTDS.class));
                });

        btnPH = findViewById(R.id.btnPH);
        btnPH.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaPH.class));
        });

        btnPeixe = findViewById(R.id.btnPeixe);
        btnPeixe.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaPeixes.class));
        });

        btnHelp = findViewById(R.id.ic_help);
        btnHelp.setOnClickListener(view -> {
            startActivity(new Intent(this, TelaDuvidas.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    nomeUsuario.setText(documentSnapshot.getString( "nome"));
                }
            }
        });
    }

    private void IniciarComponentes(){
        nomeUsuario = findViewById(R.id.textNomeuser);
        txtPh = findViewById(R.id.txtValores);
        txtTDS = findViewById(R.id.txtValor);
        txtTemp = findViewById(R.id.txtValorTemp);
    }
}