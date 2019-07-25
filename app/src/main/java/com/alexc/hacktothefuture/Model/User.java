package com.alexc.hacktothefuture.Model;

import java.util.List;

public class User {
    private String username;
    private String email;
    private List<String> favorites;
    private String admin;

    public User(String username,String email){
        this.username=username;
        this.email=email;
    }
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
}
