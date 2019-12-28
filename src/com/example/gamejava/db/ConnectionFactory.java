/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gamejava.db;

/**
 *
 * @author thuongptitdev
 */
import java.sql.Connection;

public class ConnectionFactory {
	private static ConnectionFactory connectionFactory = null;

	private ConnectionFactory() {}

            public Connection getConnection() {
            return MysqlConnection.getInstance().getConnection();
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
}