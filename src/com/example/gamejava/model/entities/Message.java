package com.example.gamejava.model.entities;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private TYPE type;
    private Account account;
    private User user;
    private User inviteUser;
    private List<User> list;
    private List<Question> questions;
    private  String error;
    private int countPass;
    private User me;


    private List<Question> meAnwser;
    private List<Question> friendAnwser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message(TYPE type) {
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<User> getList() {
        return list;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public User getInviteUser() {
        return inviteUser;
    }

    public void setInviteUser(User inviteUser) {
        this.inviteUser = inviteUser;
    }

    public List<Question> getMeAnwser() {
        return meAnwser;
    }

    public List<Question> getFriendAnwser() {
        return friendAnwser;
    }

    public void setMeAnwser(List<Question> meAnwser) {
        this.meAnwser = meAnwser;
    }

    public void setFriendAnwser(List<Question> friendAnwser) {
        this.friendAnwser = friendAnwser;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public int getCountPass() {
        return countPass;
    }

    public void setCountPass(int countPass) {
        this.countPass = countPass;
    }

    public User getMe() {
        return me;
    }

    public void setMe(User me) {
        this.me = me;
    }

    public static enum TYPE {
        LOGIN,
        LOGIN_SUCCESS,
        LIST_USER,
        INVITE,
        INVITE_ERROR,
        INVITE_SUCCESS,
        ACCEPT_INVITE,
        NOPBAI
    }
}

