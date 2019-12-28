/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gamejava.model.entities;

import java.io.Serializable;

/**
 *
 * @author thuongptitdev
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String name;
    private int point;
    private Status status;

    public User() {
    }

    public User(String username, String password, String name, int point) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.point = point;
        this.status = Status.getStatus(0);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static enum Status {
        ONLINE(0),
        OFFLINE(1),
        BUSY(2);

        private final Integer code;

        Status(Integer value) {
            this.code = value;
        }
        public Integer getCode() {
            return code;
        }

        public static Status getStatus(Integer numeral){
            for(Status ds : values()){
                if(ds.code == numeral){
                    return ds;
                }
            }
            return null;
        }

        public static Integer getStatusInt(Status status){
            if(status != null)
                return status.code;
            return  null;
        }
    }
}
