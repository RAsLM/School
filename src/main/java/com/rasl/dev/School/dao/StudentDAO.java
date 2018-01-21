package com.rasl.dev.School.dao;

import com.rasl.dev.School.DBWorker;
import com.rasl.dev.School.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAO<Student> {

    @Override
    public void create(Student student) {
        String sql = "INSERT INTO student (name, age, groupId) VALUES (?, ?, ?)";
        try (Connection connection = DBWorker.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareCall(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(3, student.getGroupId());
            preparedStatement.executeUpdate();
            System.out.println("Ученик с именем: " + student.getName() + " добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(int id, Student student) {
        String sql = "UPDATE student SET name=?, age=?, groupId=? WHERE id=?";
        try (Connection connection = DBWorker.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(3, student.getGroupId());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

            System.out.println("Данные ученика с id " + student.getId() + " были успешно обновлены: " + student.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getOne(int id) {
        ResultSet resultSet;
        String sql = "SELECT * FROM student WHERE id = ?";
        try(Connection connection = DBWorker.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareCall(sql))  {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGroupId(resultSet.getInt("groupId"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        Student student;
        ResultSet resultSet;
        String sql = "SELECT * FROM student";
        try (Connection connection = DBWorker.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql)) {
            resultSet = preparedStatement.executeQuery();
            List<Student> getAllStudent = new ArrayList<>();
            while (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGroupId(resultSet.getInt("groupId"));
                getAllStudent.add(student);
            }
            return getAllStudent;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM student WHERE id=?";
        try (Connection connection = DBWorker.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Ученик с id " + id + " успешно удален!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}