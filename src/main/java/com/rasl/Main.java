package com.rasl;

import com.rasl.dao.StudentDAO;

public class Main {
    public static StudentDAO studentDAO = new StudentDAO();
    public static void main(String[] args) {
        System.out.println(studentDAO.getOne(2));
        System.out.println(studentDAO.getAll());

    }
}