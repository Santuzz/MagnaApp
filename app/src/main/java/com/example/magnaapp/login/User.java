package com.example.magnaapp.login;

public class User {

    public String username,password,email;

    public User(){

    }

    public User(String username,String password,String email){
        this.email=email;
        this.password=password;
        this.username=username ;

    }
}
