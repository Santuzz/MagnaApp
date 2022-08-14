package com.example.magnaapp.login;

public class CreateAccount {

    public String username,password,email;

    public CreateAccount(){

    }

    public CreateAccount(String username, String email, String password){
        this.email=email;
        this.password=password;
        this.username=username ;

    }
}
