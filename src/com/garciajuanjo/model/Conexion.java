package com.garciajuanjo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection connection = null;

    public Connection getConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "root");
        } catch (SQLException sql){
            sql.printStackTrace();
        }

        return connection;
    }
}
