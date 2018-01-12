package com.rasl.dao;

import com.rasl.DBWorker;
import com.rasl.pojo.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAO {

    private static DBWorker DBWORKER = new DBWorker();
    private static final String FIND_BY_ID = "SELECT * FROM student WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM student";
    private static final String DELETE = "DELETE FROM student WHERE id=?";
    private static final String INSERT = "INSERT INTO student (id, name, age, groupId) VALUES (NULL, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE users SET name=?, age=?, groupId=? WHERE id=?";


    public Student getOne(int id) {
        Student student = new Student() ;
        ResultSet resultSet;
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall(FIND_BY_ID);){
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGroupId(resultSet.getInt("groupId"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return student;
    }

    public List<Object> getAll() {
        List<Object> getAllStudent = new ArrayList<>();
        Student student = new Student() ;
        ResultSet resultSet;
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall(FIND_ALL);){
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGroupId(resultSet.getInt("groupId"));
                getAllStudent.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return getAllStudent;
    }

    public void update(int id, Object obj) {

    }

    public void create(Object obj) {

    }

    public void delete(int id) {
        Student student = new Student() ;
        ResultSet resultSet;
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall(DELETE);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}