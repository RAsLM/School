package com.rasl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Infinity1917";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DBWorker(){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД Установлено!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}