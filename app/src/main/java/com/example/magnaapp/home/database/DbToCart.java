package com.example.magnaapp.home.database;

import static com.example.magnaapp.login.LoginActivity.connection;

import androidx.annotation.NonNull;

import com.example.magnaapp.home.ListCartAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * classe utilizzata per interagire con il DB
 * Il metodo readToDb legge i valori contenuti nel db che sono stati precedentemente scritti usando FabToDb
 * Il metodo confirmtoDb completa l'ordine passando il contenuto del carrello negli ordini passati e svuota il carrello
 * il metodo deleteToDb svuota il carrello corrente
 */
public class DbToCart {

    ArrayList<Data> finalReceived;
    private DatabaseReference mRef = FirebaseDatabase.getInstance(connection)
            .getReference().child("Users/" + FirebaseAuth.getInstance().getUid() + "/Ha nel carrello:");


    public DbToCart() {
        this.finalReceived = new ArrayList<>();
    }

    public void readToDb() {
        ArrayList<Data> dataReceived = new ArrayList<>();


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {

                    mRef.child(child.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            dataReceived.add(snapshot.getValue(Data.class));
                            finalReceived = new ArrayList<>();
                            finalReceived.addAll(dataReceived);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deleteToDb(){
        mRef.removeValue();
    }

    public void confirmToDb(){
        FirebaseDatabase.getInstance(connection)
                .getReference().child("Users/" + FirebaseAuth.getInstance().getUid() + "/Ordini confermati").push().setValue(finalReceived).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    deleteToDb();
                    System.out.println("Aggiunto nel db");
                } else {
                    System.out.println("Errore nel db");
                    updateUI(null);
                }
            }
        });
    }

    public ArrayList<Data> getFinalReceived() {
        return finalReceived;
    }
    private void updateUI(FirebaseUser user) {

    }
}
