package com.rasl.dao;

import com.rasl.DBWorker;
import com.rasl.pojo.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;

public class StudentDAO implements DAO {

    private static DBWorker DBWORKER = new DBWorker();


    public Object getOne(int id) {
        Student student = new Student() ;
        ResultSet resultSet;
        try(Statement statement = DBWORKER.getConnection().createStatement()){
            PreparedStatement preparedStatement = DBWORKER.getConnection().prepareCall("SELECT * FROM student WHERE student.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGroupId(resultSet.getInt("groupId"));
                return student;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return student;
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