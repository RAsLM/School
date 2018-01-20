package com.rasl.dev.School.dao;

import com.rasl.dev.School.DBWorker;
import com.rasl.dev.School.pojo.Student;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class StudentDAOTest {
    StudentDAO studentDAO = new StudentDAO();

    @Test
    public void createTest() {
        Student student = new Student("Name", 21, 1);
        int oldId = getMaxId();
        studentDAO.create(student);

        int newId = getMaxId();
        assertNotNull(studentDAO.getOne(getMaxId()));
        assertNotEquals(oldId, newId);
    }

    @Test
    public void update() {
    }

    @Test
    public void getOne() {
        Student student = studentDAO.getOne(getMaxId());
        assertNotNull(student);
    }

    @Test
    public void getAll() {
    }

    @Test
    public void delete() {
        int oldId = getMaxLines();

        studentDAO.delete(8);

        int maxId = getMaxLines();

        assertNotEquals(oldId,maxId);

    }

    private Integer getMaxId(){
        ResultSet resultSet;
        String sql = "SELECT max(id) from student";
        try (PreparedStatement preparedStatement =
                     DBWorker.getInstance().getConnection().prepareCall(sql)) {
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                int id =  resultSet.getInt(1);
                System.out.println("Max id - " + id);
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer getMaxLines(){
        ResultSet resultSet;
        String sql = "SELECT count(*) from student";
        try (PreparedStatement preparedStatement =
                     DBWorker.getInstance().getConnection().prepareCall(sql)) {
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                int lines =  resultSet.getInt(1);
                System.out.println("Max lines - " + lines);
                return lines;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}