package com.example.magnaapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.database.FirebaseDatabase;

public class SignupTabFragment extends Fragment implements View.OnClickListener {

    EditText email;
    EditText username;
    EditText password;
    TextView conf_psw;
    ImageView logo;
    AppCompatButton signup;

    float v = 0;


    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState){
        View view = (View) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        conf_psw = view.findViewById(R.id.conf_psw);
        signup = view.findViewById(R.id.signup);
        signup.setOnClickListener(this);
        logo=view.findViewById(R.id.Logo);

        mAuth=FirebaseAuth.getInstance();

        //TODO: comunicare con il DB per aggiungere il nuovo utente
        //TODO: aprire la nuova activity in cui poter ordinare




        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup:
                registration();
                break;

        }

    }

    private void registration(){

        String email = this.email.getText().toString().trim();
        String username = this.username.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user =new User(username,password,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){

                                            }
                                        }
                                    });
                        }
                    }
                });
    }
}