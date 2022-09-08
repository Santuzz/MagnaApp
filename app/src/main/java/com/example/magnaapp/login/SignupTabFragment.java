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
import androidx.viewpager2.widget.ViewPager2;

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

public class SignupTabFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText conf_psw, password, email, username, cof_psw;

    private AppCompatButton signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState) {
        Toast.makeText(getActivity(), "Registrati!", Toast.LENGTH_SHORT).show();

        View view = (View) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();

        signup = view.findViewById(R.id.signup);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        conf_psw = view.findViewById(R.id.conf_psw);
        signup = view.findViewById(R.id.signup);

        signup.setOnClickListener(this);

        return view;

    }

    private void createAccount(String username, String email, String password) {
        // [START create_user_with_email]

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> taskSignIn) {
                        if (taskSignIn.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information


                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            CreateAccount userAc = new CreateAccount(email, password, username);

                            FirebaseDatabase.getInstance("https://magnalbase-default-rtdb.europe-west1.firebasedatabase.app").getReference("Users/"+FirebaseAuth.getInstance().getUid())
                                    .setValue(userAc).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getActivity(), "Account creato con successo!",
                                                        Toast.LENGTH_SHORT).show();
                                                //TODO ho utilizzato un nuovo intent per richiamare il login ma credo che ci sia un modo per richiamare il fragment login senza crearne un nuovo intent ma sono stanco pensaci tu
                                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(getActivity(), "Registrazione fallita!",
                                                        Toast.LENGTH_SHORT).show();
                                                updateUI(null);
                                            }
                                        }
                                    });

                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(getActivity(), "Registrazione fallita!",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]


    }


    private void reload() {
    }

    private void updateUI(FirebaseUser user) {

    }

    @Override
    public void onClick(View view) {
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();
        String username = this.username.getText().toString().trim();
        String cnf_psw = this.conf_psw.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(getActivity(), "Inserisci un email valido!", Toast.LENGTH_SHORT).show();

        } else {
            if (username.isEmpty()) {
                Toast.makeText(getActivity(), "Inserisci username!", Toast.LENGTH_SHORT).show();

            } else {
                if (password.isEmpty()) {
                    Toast.makeText(getActivity(), "Inserisci password!", Toast.LENGTH_SHORT).show();

                } else {
                    if (cnf_psw.isEmpty()) {
                        Toast.makeText(getActivity(), "Ripeti la password!", Toast.LENGTH_SHORT).show();

                    }
                    if (password.length() < 6) {
                        Toast.makeText(getActivity(), "Password troppo corta!", Toast.LENGTH_LONG).show();
                    } else {

                        if (!cnf_psw.contentEquals(password)) {
                            Toast.makeText(getActivity(), "La password non combacia!", Toast.LENGTH_SHORT).show();
                        } else {
                            createAccount(username, email, password);
                        }
                    }
                }
            }
        }
    }


}