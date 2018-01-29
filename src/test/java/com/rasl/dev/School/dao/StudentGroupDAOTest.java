package com.rasl.dev.School.dao;

import com.rasl.dev.School.DBWorker;
import com.rasl.dev.School.pojo.StudentGroup;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class StudentGroupDAOTest {

    StudentGroupDAO studentGroupDAO = new StudentGroupDAO();

    @Test
    public void createTest() {
        StudentGroup studentGroup = new StudentGroup("S1");
        int oldId = getMaxId();
        studentGroupDAO.create(studentGroup);
        int newId = getMaxId();
        assertNotNull(studentGroupDAO.getOne(getMaxId()));
        assertNotEquals(oldId, newId);
    }

    @Test
    public void updateTest() {
    }

    @Test
    public void getOneTest() {
    }

    @Test
    public void getAllTest() {
    }

    @Test
    public void deleteTest() {
    }

    private int getMaxId(){
        ResultSet resultSet;
        String sql = "SELECT max(id) from groups";
        try (Connection connection = DBWorker.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql)) {
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                int id =  resultSet.getInt(1);
                System.out.println("Max id - " + id);
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}