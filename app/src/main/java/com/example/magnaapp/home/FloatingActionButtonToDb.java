package com.example.magnaapp.home;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.magnaapp.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

//TODO verrà sostituito dalla classe fabToDb
public class FloatingActionButtonToDb extends Fragment {

    FirebaseAuth mAuth;

    FloatingActionButtonToDb() {

    }

     FloatingActionButtonToDb(String one,String two,String three,String four,String five,String tipo){
       FoodCreation food=new FoodCreation(one ,two,three,four,five,tipo);

        FirebaseDatabase
                .getInstance("https://magnalbase-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("antipasti")
                .child(mAuth.getInstance().getUid())
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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            String aggiunta="Aggiunto al carrello ";
            Toast.makeText(getActivity(),aggiunta ,Toast.LENGTH_LONG).show();

        }
    }
}
