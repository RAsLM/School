package com.rasl.dev.School;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker{
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static DBWorker instance;
    private Connection connection;


    private DBWorker() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        System.out.println("Соединение с БД Установлено!");
    }

    public Connection getConnection() {
        return connection;
    }

    public static synchronized DBWorker getInstance(){
        if(instance == null) {
            try {
                instance = new DBWorker();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}