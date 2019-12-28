package com.example.gamejava.model.entities;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String passwrod;

    public Account(String username, String passwrod) {
        this.username = username;
        this.passwrod = passwrod;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }
}
