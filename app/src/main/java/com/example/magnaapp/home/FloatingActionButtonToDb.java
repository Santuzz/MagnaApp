package com.example.magnaapp.home;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.magnaapp.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class FloatingActionButtonToDb {


    FloatingActionButtonToDb() {

    }




     FloatingActionButtonToDb(String one,String two,String three,String four,String five,String tipo){
       FoodCreation food=new FoodCreation(one ,two,three,four,five,tipo);
        FirebaseDatabase.getInstance("https://magnalbase-default-rtdb.europe-west1.firebasedatabase.app").getReference(tipo)
                .child(FirebaseAuth.getInstance().getUid())
                .setValue(food).addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            System.out.println("INVIO CIBO FIREBASE OK");

                        }else{
                            System.out.println("INVIO CIBO FIREBASE FAIL");


                        }
                    }
                });



    }
}
