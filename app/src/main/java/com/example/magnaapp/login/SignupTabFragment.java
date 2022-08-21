package com.example.magnaapp.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.magnaapp.R;
import com.example.magnaapp.home.MenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.concurrent.Executor;

public class SignupTabFragment extends Fragment  implements Executor, View.OnClickListener {




     private FirebaseAuth mAuth;
     private EditText conf_psw,password,email,username,cof_psw;

     private  AppCompatButton signup;


    private static final String TAG = "EmailPassword";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState) {
        Toast.makeText(getActivity(),"Registrati!", Toast.LENGTH_SHORT).show();

        View view = (View) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();


        signup = view.findViewById(R.id.signup);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        conf_psw=view.findViewById(R.id.conf_psw);
        signup = view.findViewById(R.id.signup);

        signup.setOnClickListener(this);

        float v = 0;

        return view;

    }


    private void createAccount(String username,String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getActivity(), "Account creato con successo!",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getActivity(), "Registrazione fallita!",
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
        String email=this.email.getText().toString().trim();
        String password=this.password.getText().toString().trim();
        String username=this.username.getText().toString().trim();
        String cnf_psw=this.conf_psw.getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(getActivity(),"Inserisci un email valido!", Toast.LENGTH_SHORT).show();

        }
        else {
            if(username.isEmpty()){
                Toast.makeText(getActivity(),"Inserisci username!", Toast.LENGTH_SHORT).show();

            }else {
                if (password.isEmpty()) {
                    Toast.makeText(getActivity(), "Inserisci password!", Toast.LENGTH_SHORT).show();

                }
                else{
                    if (cnf_psw.isEmpty()){
                        Toast.makeText(getActivity(),"Ripeti la password!", Toast.LENGTH_SHORT).show();

                    }else {

                        if (!cnf_psw.contentEquals(password)){
                            Toast.makeText(getActivity(), "La password non combacia!", Toast.LENGTH_SHORT).show();
                        }else{
                            createAccount(username,email,password);
                        }
                    }
                }
            }




        }
    }

    @Override
    public void execute(Runnable runnable) {

    }

}
/*

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState) {
        View view = (View) inflater.inflate(R.layout.signup_tab_fragment, container, false);


        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        conf_psw = view.findViewById(R.id.conf_psw);
        signup = view.findViewById(R.id.signup);
        signup.setOnClickListener(this);
        logo = view.findViewById(R.id.Logo);
        Activity activity = getActivity();


        Toast.makeText(activity, "Registrati!", Toast.LENGTH_LONG).show();

        //TODO: comunicare con il DB per aggiungere il nuovo utente
        //TODO: aprire la nuova activity in cui poter ordinare


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup:
                registration();
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
                break;

        }

    }


    private void registration() {

        String email = this.email.getText().toString().trim();
        String username = this.username.getText().toString().trim();
        String password = this.password.getText().toString().trim();
        Activity activity = getActivity();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT);
                        }
                    }
                });
        private void reload() { }

        private void updateUI(FirebaseUser user) {

        }

    }


}
*/
