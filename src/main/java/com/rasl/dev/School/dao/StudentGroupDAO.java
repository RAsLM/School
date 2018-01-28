package com.rasl.dev.School.dao;

import com.rasl.dev.School.pojo.StudentGroup;
import com.rasl.dev.School.DBWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentGroupDAO implements DAO<StudentGroup> {

    @Override
    public void create(StudentGroup studentGroup) {
        String sql = "INSERT INTO groups (name) VALUES (?)";
        try(Connection connection = DBWorker.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareCall(sql)){
            preparedStatement.setString(1, studentGroup.getName());
            System.out.println("Группа с именем: " + studentGroup.getName() + " добавлена!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, StudentGroup studentGroup) {
        try(PreparedStatement preparedStatement =
                    DBWorker.getInstance().getConnection().prepareCall("UPDATE groups SET name=? WHERE id=?")){
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
        String sql = "SELECT * FROM groups WHERE id = ?";
        ResultSet resultSet;
        try(Connection connection = DBWorker.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareCall(sql)){
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.first()){
                StudentGroup studentGroup = new StudentGroup();
                studentGroup.setId(resultSet.getInt("id"));
                studentGroup.setName(resultSet.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentGroup> getAll() {
        List<StudentGroup> getAllStudentGroups = new ArrayList<>();
        StudentGroup studentGroup;
        ResultSet resultSet;
        try(PreparedStatement preparedStatement =
                    DBWorker.getInstance().getConnection().prepareCall("SELECT * FROM student")){
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
                    DBWorker.getInstance().getConnection().prepareCall("DELETE FROM groups WHERE id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Группа с id " + id + " успешно удалена!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}