/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gamejava.model.dao;


import com.example.gamejava.model.entities.Question;


import java.util.List;

/**
 *
 * @author thuongptitdev
 */
public interface QuestionDAO {
    List<Question> list(Boolean isAnwser) throws Exception;
    List<Question> listAll() throws Exception;
}
