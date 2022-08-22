package com.example.magnaapp.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.magnaapp.home.MenuActivity;
import com.example.magnaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class LoginTabFragment extends Fragment  implements Executor, View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText password,username,email;

    private  AppCompatButton login;

    float v = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState) {
        Toast.makeText(getActivity(),"Accedi!", Toast.LENGTH_SHORT).show();

        View view = (View) inflater.inflate(R.layout.login_tab_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();

        login = view.findViewById(R.id.login);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);


        login.setOnClickListener(this);

        float v = 0;

        return view;

    }
    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
    // [END on_start_check_user]

    private void accedi(String email, String password) {
        // [START create_user_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> taskLogIn) {
                        if (taskLogIn.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getActivity(), "Bentornato!",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user= mAuth.getCurrentUser();
                            updateUI(user);

                            Intent intent = new Intent(getActivity(), MenuActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getActivity(), "Autenticazione fallita!",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);

                        }
                    }
                });
        // [END create_user_with_email]

    }




    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }

    @Override
    public void onClick(View view) {
        String email=this.username.getText().toString().trim();
        String password=this.password.getText().toString().trim();
        String username=this.username.getText().toString().trim();


            if(email.isEmpty()){
                Toast.makeText(getActivity(),"Inserisci username!", Toast.LENGTH_SHORT).show();

            }else {
                if (password.isEmpty()) {
                    Toast.makeText(getActivity(), "Inserisci password!", Toast.LENGTH_SHORT).show();

                }
                else{
                            accedi(email,password);
                        }
                    }
                }








    @Override
    public void execute(Runnable runnable) {

    }

}
