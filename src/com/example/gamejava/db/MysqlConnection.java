/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gamejava.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thuongptitdev
 */
public class MysqlConnection {
    static String driverClassName = "com.mysql.jdbc.Driver";
	static String connectionUrl = "jdbc:mysql://localhost:3308/game";
	static String dbUser = "root";
	static String dbPwd = "root";
        static Connection conn = null;
        
        public static MysqlConnection mysqlConnection = null;

        public MysqlConnection() {}
       
        public Connection getConnection() {
            if(conn != null){
                return conn;
            }
            try {
                conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return conn;
        }
        
        
        public static MysqlConnection getInstance() {
            if(mysqlConnection != null){
                return mysqlConnection;
            }else{
                return new MysqlConnection();
            }
	}
}
