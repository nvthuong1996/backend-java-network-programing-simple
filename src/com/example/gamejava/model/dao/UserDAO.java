/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gamejava.model.dao;

import com.example.gamejava.model.entities.Account;
import com.example.gamejava.model.entities.User;

import java.util.List;

/**
 *
 * @author thuongptitdev
 */
public interface UserDAO {
    void create(User user) throws Exception;
    User findByAccount(Account account) throws Exception;
    User findByUserName(String username) throws Exception;
    List<User> list(User user) throws Exception;
}
