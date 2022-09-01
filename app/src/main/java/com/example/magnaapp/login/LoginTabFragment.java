package com.example.magnaapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.magnaapp.R;
import com.example.magnaapp.home.MenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment  implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText password,username,email;
    private ImageButton google;
    private  AppCompatButton login;
    private TextView forget_psw;


    float v = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState) {
        Toast.makeText(getActivity(),"Accedi!", Toast.LENGTH_SHORT).show();

        View view = (View) inflater.inflate(R.layout.login_tab_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();

        login = view.findViewById(R.id.login);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        google = view.findViewById(R.id.google);
        forget_psw=view.findViewById(R.id.forget_psw);

        forget_psw.setOnClickListener(this);
        google.setOnClickListener(this);
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

            String Bentornato="Bentornato "+currentUser.getEmail().toString()+"\n\t\t\t\t\t\taccedi di nuovo!";
            Toast.makeText(getActivity(),Bentornato ,Toast.LENGTH_LONG).show();
            updateUI(null);

        }
    }
    // [END on_start_check_user]

    private void accedi(String email, String password) {
        // [START create_user_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> taskLogIn) {
                        if (taskLogIn.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getActivity(), "Bentornato!",
                                    Toast.LENGTH_LONG).show();
                            FirebaseUser user= mAuth.getCurrentUser();
                            updateUI(user);

                            Intent intent = new Intent(getActivity(), MenuActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getActivity(), "email o password errati!",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);

                        }
                    }
                });
        // [END create_user_with_email]

    }

    //TODO accedere con google (optional)
    private void accedi() {

    }




    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }

    @Override
    public void onClick(View view) {
        String email=this.username.getText().toString().trim();
        String password=this.password.getText().toString().trim();
        String username=this.username.getText().toString().trim();


        switch (view.getId()){
            case R.id.login:
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
           /*case R.id.google:
                {accedi();}
            case R.id.forget_psw:
            {
                //TODO richiamare pagina resetpassword.xml

                resetPassword(email);
            }

            */
        }


    }
            //TODO per reset psw con codice tramite email
    private void resetPassword(String email) {
         // [START send_password_reset]
            FirebaseAuth auth = FirebaseAuth.getInstance();

            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("success", "Email inviato!");
                                Toast.makeText(getActivity(), "Email ripristino password inviato!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
            // [END send_password_reset]
        }
    }

