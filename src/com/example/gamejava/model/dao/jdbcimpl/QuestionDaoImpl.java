/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gamejava.model.dao.jdbcimpl;

import com.example.gamejava.model.dao.QuestionDAO;
import com.example.gamejava.model.dao.UserDAO;
import com.example.gamejava.model.entities.Account;
import com.example.gamejava.model.entities.Question;
import com.example.gamejava.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author thuongptitdev
 */
public class QuestionDaoImpl implements QuestionDAO {

    private Connection conn;

    public QuestionDaoImpl(Connection conn) {
        this.conn = conn;
    }



    @Override
    public List<Question> list(Boolean isAnwser) throws Exception {
        List<Question> list = new LinkedList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM question ORDER BY RAND() LIMIT 3");

        rs = st.executeQuery();
        while (rs.next()) {
            Question obj = new Question();
            obj.setId(rs.getInt("id"));
            obj.setTitle(rs.getString("title"));
            obj.setAreply(rs.getString("a"));
            obj.setBreply(rs.getString("b"));
            obj.setCreply(rs.getString("c"));
            if(isAnwser){
                obj.setAnwser(rs.getString("anwser"));
            }

            list.add(obj);
        }
        return list;
    }

    @Override
    public List<Question> listAll() throws Exception {
        List<Question> list = new LinkedList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement(
                "SELECT * FROM question");

        rs = st.executeQuery();
        while (rs.next()) {
            Question obj = new Question();
            obj.setId(rs.getInt("id"));
            obj.setTitle(rs.getString("title"));
            obj.setAreply(rs.getString("a"));
            obj.setBreply(rs.getString("b"));
            obj.setCreply(rs.getString("c"));
            obj.setAnwser(rs.getString("anwser"));
            list.add(obj);
        }
        return list;
    }
}
