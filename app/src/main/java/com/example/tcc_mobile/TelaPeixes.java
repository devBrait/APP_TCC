package com.example.tcc_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TelaPeixes extends AppCompatActivity {

    private View btnVolta;

    private Button btnAdicionar;

    private View btnDelete;

    private TextView ValorBanco;
    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    Myadapter myadapter;
    FirebaseFirestore db;
    String[] mensagens = {"Peixes excluídos com sucesso!", "Seus peixes já foram exlcuídos!"};
    String usuarioID;
    double cont;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_peixes);

       ValorBanco = findViewById(R.id.Valor);

         Refs();


        btnVolta = findViewById(R.id.btnVoltarHome);
        btnVolta.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        btnAdicionar = findViewById(R.id.btnEnviar);
        btnAdicionar.setOnClickListener(view -> {
            startActivity(new Intent(this, AddPeixes.class));
        });

        btnDelete = findViewById(R.id.ic_delete);
        btnDelete.setOnClickListener(view -> {
            cont++;
            String nomeBanco = ValorBanco.getText().toString();
            if(nomeBanco == ""){
                Snackbar snackbar = Snackbar.make(view, mensagens[1], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            }else {
                Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();

                ExcluirPeixe();

            }
        });


        recyclerView = findViewById(R.id.recyclerPeixe);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();
        myadapter = new Myadapter(TelaPeixes.this, userArrayList);
        recyclerView.setAdapter(myadapter);

        EventChangeListener();
    }

    private void EventChangeListener() {


        db.collection("Usuarios").orderBy("nomePeixes", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {


                if (error != null){
                    Log.e("Erro", error.getMessage());
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()){

                    if(dc.getType() == DocumentChange.Type.ADDED){
                        userArrayList.add(dc.getDocument().toObject(User.class));
                    }

                    myadapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void ExcluirPeixe(){
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                .document(usuarioID);

        db.collection("Usuarios").document("nomePeixes");

        Map<String, Object> map = new HashMap<>();
        map.put("nomePeixes", "");
        map.put("pHPeixes", "");

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

    private void Refs(){
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Usuarios")
                .document(usuarioID);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    ValorBanco.setText(documentSnapshot.getString( "nomePeixes"));
                }
            }
        });
    }

}