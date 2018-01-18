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

    @Override
    public void create(Student student) {
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall("INSERT INTO student (name, age, groupId) VALUES (?, ?, ?)");){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(3, student.getGroupId());
            preparedStatement.executeUpdate();
            System.out.println("Ученик с именем: " + student.getName() + " добавлен!");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(int id, Student student) {
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall("UPDATE student SET name=?, age=?, groupId=? WHERE id=?");){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(3, student.getGroupId());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

            System.out.println("Данные ученика с id " + student.getId() + " были успешно обновлены: " + student.toString());

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Student getOne(int id) {
        Student student = new Student() ;
        ResultSet resultSet;
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall("SELECT * FROM student WHERE id = ?");){
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.first()){
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

    @Override
    public List<Student> getAll() {
        List<Student> getAllStudent = new ArrayList<>();
        Student student;
        ResultSet resultSet;
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall("SELECT * FROM student");){
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

    @Override
    public void delete(int id) {
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall("DELETE FROM student WHERE id=?");) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Ученик с id " + id + " успешно удален!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}