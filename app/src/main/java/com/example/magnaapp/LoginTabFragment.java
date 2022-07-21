package com.example.magnaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {

    EditText username;
    EditText password;
    TextView forget_psw;
    AppCompatButton login;

    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState) {
        View view = (View) inflater.inflate(R.layout.login_tab_fragment, container, false);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        forget_psw = view.findViewById(R.id.forget_psw);
        login = view.findViewById(R.id.login);

        //TODO: comunicare con il DB per verificare l'esistenza dell'utente
        //TODO: aprire la nuova activity in cui poter ordinare

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();

                if(strUsername.equals("") && strPassword.equals("")){
                    //TODO: apri l'activity del menu
                    Intent intent = new Intent(getActivity(), MenuActivity.class);
                    startActivity(intent);
                }
                else{
                    //TODO: apri un popup che ti avvisa che username e password sono sbagliati
                    username.setText("");
                    password.setText("");
                }



            }
        });

        return view;
    }


}
