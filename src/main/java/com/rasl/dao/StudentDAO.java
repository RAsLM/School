package com.rasl.dao;

import com.rasl.DBWorker;
import com.rasl.pojo.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;

public class StudentDAO implements DAO {

    private static DBWorker DBWORKER = new DBWorker();


    public Object getOne(int id) {
        Student student;
        ResultSet resultSet;
        String query = "SELECT * FROM student WHERE student.id = id";
        try(Statement statement = DBWORKER.getConnection().createStatement()){
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                student = new Student();
                student.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Object> getAll() {
        return null;
    }

    public void update(int id, Object obj) {

    }

    public void create(Object obj) {

    }

    public void delete(int id) {

    }
}