package com.example.magnaapp.home.section;

import static com.example.magnaapp.login.LoginActivity.connection;

import android.content.Intent;
import android.content.res.TypedArray;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magnaapp.login.CreateAccount;
import com.example.magnaapp.login.LoginActivity;
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
import java.util.Date;
import java.util.Map;

public class FabToDb {

    Map<String, ArrayList<Integer>> selectedFood, oldSelectedFood;
    CreateAccount user = new CreateAccount();

    public FabToDb(Map<String, ArrayList<Integer>> selectedFood) {
        this.selectedFood = selectedFood;
        Date now = new Date();

        FirebaseDatabase.getInstance(connection)
                .getReference("Users/" + FirebaseAuth.getInstance().getUid() + "/Ha nel carrello:")
                .child("Ordine:").setValue(selectedFood)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {


                            DatabaseReference root = FirebaseDatabase.getInstance(connection)
                                    .getReference()
                                    .child("Users/" + FirebaseAuth.getInstance().getUid() + "/Ha nel carrello:");
                            root.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapShot) {

                                    ArrayList<String> food = new ArrayList<>();

                                    for (DataSnapshot datasnapShot : snapShot.getChildren()) {
                                        food.add(datasnapShot.getValue().toString());
                                    }

                                    System.out.println("Ha nel carrello " + food);
                                    food.clear();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            System.out.println("Aggiunto al Db");
                        } else {

                            System.out.println("Errore nel db");

                            updateUI(null);
                        }
                    }
                });
    }


    public FabToDb(Map<String, ArrayList<Integer>> selectedFood, String OldCarrello) {

        this.selectedFood = selectedFood;
        Date now = new Date();

        FirebaseDatabase.getInstance(connection)
                .getReference("Users/" + FirebaseAuth.getInstance().getUid() + "/Ordini passati:")
                .child(now.toString())
                .setValue(selectedFood)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Aggiunto al Db");
                        } else {

                            System.out.println("Errore nel db");

                            updateUI(null);
                        }
                    }
                });


        DatabaseReference root = FirebaseDatabase.getInstance(connection).getReference().child("Users/" + FirebaseAuth.getInstance().getUid() + "/Ordini passati:");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapShot) {

                ArrayList<String> food = new ArrayList<>();

                for (DataSnapshot datasnapShot : snapShot.getChildren()) {
                    food.add(datasnapShot.getValue().toString());
                }

                System.out.println("Ha ordinato in passato:" + food);
                food.clear();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void updateUI(FirebaseUser user) {

    }


}
