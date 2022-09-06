package com.example.magnaapp.home.section;

import android.content.Intent;
import android.content.res.TypedArray;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magnaapp.login.CreateAccount;
import com.example.magnaapp.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class FabToDb {

   Map<String, Integer> selectedFood;
    public FabToDb(Map<String, Integer> selectedFood) {
        this.selectedFood = selectedFood;


        FirebaseDatabase.getInstance("https://magnalbase-default-rtdb.europe-west1.firebasedatabase.app").getReference("Users/"+FirebaseAuth.getInstance().getUid()+"/Ha nel carello:")
                .child(FirebaseAuth.getInstance().getUid())
                .setValue(selectedFood).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    }

    private void updateUI(FirebaseUser user) {

    }


}
