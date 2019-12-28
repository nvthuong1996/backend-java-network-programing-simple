/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gamejava.model.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.example.gamejava.model.dao.UserDAO;
import com.example.gamejava.model.entities.Account;
import com.example.gamejava.model.entities.User;

/**
 *
 * @author thuongptitdev
 */
public class UserDaoImpl implements UserDAO {

    private Connection conn;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(User user) throws Exception {
        PreparedStatement st = null;
        // conn.setAutoCommit(false);
        st = conn.prepareStatement(
                "INSERT INTO user "
                + "(username,password,name,point,status) "
                + "VALUES "
                + "(?,?)",
                Statement.RETURN_GENERATED_KEYS);

        st.setString(1, user.getUsername());
        st.setString(2, user.getPassword());
        st.setString(3, user.getName());
        st.setInt(4, user.getPoint());
        st.setInt(5, user.getStatus().getCode());

        int rowsAffected = st.executeUpdate();
        if (!(rowsAffected > 0)) {
            throw new Exception("Unexpected error! No rows affected!");
        }
    }

    @Override
    public User findByAccount(Account account) throws Exception{
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM user WHERE username = ? AND password = ?");
        st.setString(1, account.getUsername());
        st.setString(2, account.getPasswrod());
        rs = st.executeQuery();
        if (rs.next()) {
            User obj = new User();
            obj.setName(rs.getString("name"));
            obj.setPassword(rs.getString("password"));
            obj.setPoint(rs.getInt("point"));
            obj.setUsername(rs.getString("username"));
            obj.setStatus(User.Status.getStatus(rs.getInt("status")));
            return obj;
        }
        return null;
    }

    @Override
    public User findByUserName(String username) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM user WHERE username = ?");
        st.setString(1, username);

        rs = st.executeQuery();
        if (rs.next()) {
            User obj = new User();
            obj.setName(rs.getString("name"));
            obj.setPassword(rs.getString("password"));
            obj.setPoint(rs.getInt("point"));
            obj.setUsername(rs.getString("username"));
            obj.setStatus(User.Status.getStatus(rs.getInt("status")));
            return obj;
        }
        return null;
    }

    @Override
    public List<User> list(User user) throws Exception {
        List<User> list = new LinkedList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM user WHERE username <> ?");
        st.setString(1, user.getUsername());

        rs = st.executeQuery();
        while (rs.next()) {
            User obj = new User();
            obj.setName(rs.getString("name"));
            obj.setPoint(rs.getInt("point"));
            obj.setUsername(rs.getString("username"));
            obj.setStatus(User.Status.getStatus(rs.getInt("status")));
            list.add(obj);
        }
        return list;
    }
}
