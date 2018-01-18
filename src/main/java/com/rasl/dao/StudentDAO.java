package com.rasl.dao;

import com.rasl.DBWorker;
import com.rasl.pojo.Student;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAO<Student> {

    private static DBWorker DBWORKER = new DBWorker();
    private static final String FIND_BY_ID = "SELECT * FROM student WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM student";
    private static final String DELETE = "DELETE FROM student WHERE id=?";
    private static final String INSERT = "INSERT INTO student (name, age, groupId) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE student SET name=?, age=?, groupId=? WHERE id=?";

    @Override
    public void create(Student student) {
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall(INSERT);){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(3, student.getGroupId());
            preparedStatement.executeUpdate();
            System.out.println("Ученик с именем: " + student.getName() + " добавлен!");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void update(Student student) {
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall(UPDATE);){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(3, student.getGroupId());
            preparedStatement.setInt(4, student.getId());

            preparedStatement.executeUpdate();

            System.out.println("Данные ученика с id " + student.getId() + " были успешно обновлены: " + student.toString());

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

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

    public List<Student> getAll() {
        List<Student> getAllStudent = new ArrayList<>();
        Student student;
        ResultSet resultSet;
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall(FIND_ALL);){
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                student = new Student();
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

    public void delete(int id) {
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall(DELETE);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Ученик с id " + id + " успешно удален!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}