package com.rasl.dao;

import com.rasl.DBWorker;
import com.rasl.pojo.Student;
import com.rasl.pojo.StudentGroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentGroupDAO implements DAO<StudentGroup> {

    private static DBWorker DBWORKER = new DBWorker();

    @Override
    public void create(StudentGroup studentGroup) {
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall("INSERT INTO groups (name) VALUES (?)");){
            preparedStatement.setString(1, studentGroup.getName());
            System.out.println("Группа с именем: " + studentGroup.getName() + " добавлена!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, StudentGroup studentGroup) {
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall("UPDATE groups SET name=? WHERE id=?");){
            preparedStatement.setString(1,studentGroup.getName());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

            System.out.println("Данные группы с id " + studentGroup.getId() + " были успешно обновлены: " + studentGroup.toString());

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public StudentGroup getOne(int id) {
        StudentGroup studentGroup = new StudentGroup() ;
        ResultSet resultSet;
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall("SELECT * FROM groups WHERE id = ?");){
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.first()){
                studentGroup.setId(resultSet.getInt("id"));
                studentGroup.setName(resultSet.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return studentGroup;
    }

    @Override
    public List<StudentGroup> getAll() {
        List<StudentGroup> getAllStudentGroups = new ArrayList<>();
        StudentGroup studentGroup;
        ResultSet resultSet;
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall("SELECT * FROM student");){
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                studentGroup = new StudentGroup();
                studentGroup.setId(resultSet.getInt("id"));
                studentGroup.setName(resultSet.getString("name"));
                getAllStudentGroups.add(studentGroup);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return getAllStudentGroups;
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement preparedStatement =
                    DBWORKER.getConnection().prepareCall("DELETE FROM groups WHERE id=?");) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Группа с id " + id + " успешно удалена!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}