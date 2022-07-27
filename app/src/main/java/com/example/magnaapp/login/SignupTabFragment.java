package com.example.magnaapp.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.magnaapp.R;

public class SignupTabFragment extends Fragment{

    EditText email;
    EditText username;
    EditText password;
    TextView conf_psw;
    AppCompatButton signup;

    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState){
        View view = (View) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        conf_psw = view.findViewById(R.id.conf_psw);
        signup = view.findViewById(R.id.signup);


        //TODO: comunicare con il DB per aggiungere il nuovo utente
        //TODO: aprire la nuova activity in cui poter ordinare

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }
}
