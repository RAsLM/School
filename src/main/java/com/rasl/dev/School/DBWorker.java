package com.rasl.dev.School;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker implements AutoCloseable {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private static DBWorker instance;

    public static synchronized DBWorker getInstance(){
        if(instance == null) {
            try {
                instance = new DBWorker();
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Соединение с БД Установлено!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public void close() throws Exception {
        System.out.println("I'm close");
    }
}