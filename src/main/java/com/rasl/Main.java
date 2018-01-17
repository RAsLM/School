package com.rasl;

import com.rasl.dao.StudentDAO;
import com.rasl.pojo.Student;

public class Main {
    public static StudentDAO studentDAO = new StudentDAO();
    public static void main(String[] args) {

        //test methods
       /* for (int i = 6; i < 9; i++) {
            studentDAO.delete(i);
        }
        studentDAO.delete(2);*/

        Student student = new Student();
        student.setName("Bes");
        student.setAge(30);
        student.setGroupId(1);

        Student updateTest = new Student();
        updateTest.setId(3);
        updateTest.setName("ddd");
        updateTest.setAge(50);
        updateTest.setGroupId(1);

        //studentDAO.create(student);
        studentDAO.update(updateTest);
        System.out.println(studentDAO.getOne(3));
        System.out.println(studentDAO.getAll());


    }
}