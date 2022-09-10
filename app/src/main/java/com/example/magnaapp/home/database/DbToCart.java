package com.example.magnaapp.home.database;

import static com.example.magnaapp.login.LoginActivity.connection;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DbToCart {

    ArrayList<Data> finalReceived;

    public DbToCart() {
        this.finalReceived = new ArrayList<>();
    }

    public void readToDb() {
        ArrayList<Data> dataReceived = new ArrayList<>();

        DatabaseReference mRef = FirebaseDatabase.getInstance(connection)
                .getReference().child("Users/" + FirebaseAuth.getInstance().getUid() + "/Ha nel carrello:");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {

                    mRef.child(child.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            dataReceived.add(snapshot.getValue(Data.class));
                            finalReceived.addAll(dataReceived);
                            //TODO riuscire a passare dataReceived al ListCartAdapter che costruisce la RecyclerView
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

    public ArrayList<Data> getFinalReceived() {
        return finalReceived;
    }
}
