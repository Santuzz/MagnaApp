package com.example.magnaapp.home.database;

import static com.example.magnaapp.login.LoginActivity.connection;

import androidx.annotation.NonNull;

import com.example.magnaapp.login.CreateAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FabToDb {

    ArrayList<Data> dataSend;
    CreateAccount user = new CreateAccount();
    private DatabaseReference mRef = FirebaseDatabase.getInstance(connection)
            .getReference("Users/" + FirebaseAuth.getInstance().getUid() + "/Ha nel carrello:");

    public FabToDb(ArrayList<Data> dataToSend) {
        this.dataSend = dataToSend;

        //invio al db i prodotti selezionati (ovvero quelli con quantità diversa da zero) uno alla volta con la push
        for (int i = 0; i < dataSend.size(); i++) {

                    mRef.push().setValue(dataSend.get(i))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                System.out.println("Aggiunto nel db");
                            } else {
                                System.out.println("Errore nel db");
                                updateUI(null);
                            }
                        }
                    });
        }
    }

    private void updateUI(FirebaseUser user) {

    }


}
