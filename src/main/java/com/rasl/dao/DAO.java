package com.rasl.dao;

import com.rasl.pojo.Student;

import java.util.List;

public interface DAO {
    Object getOne(int id);
    List<Object> getAll();
    void update(int id, Object obj);
    void create(Object obj);
    void delete(int id);
}
