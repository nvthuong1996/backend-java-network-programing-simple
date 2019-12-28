package com.example.gamejava.model.entities;

import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String areply;
    private String breply;
    private String creply;
    private String anwser;
    private int id;

    public Question(String title, String areply, String breply, String creply, String anwser) {
        this.title = title;
        this.areply = areply;
        this.breply = breply;
        this.creply = creply;
        this.anwser = anwser;
    }

    public Question() { }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public String getAreply() {
        return areply;
    }

    public String getBreply() {
        return breply;
    }

    public String getCreply() {
        return creply;
    }

    public String getAnwser() {
        return anwser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAreply(String areply) {
        this.areply = areply;
    }

    public void setBreply(String breply) {
        this.breply = breply;
    }

    public void setCreply(String creply) {
        this.creply = creply;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
