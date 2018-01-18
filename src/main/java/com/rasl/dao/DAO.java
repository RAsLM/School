package com.rasl.dao;

import java.util.List;

public interface DAO {
    Object getOne(int id);
    List<Object> getAll();
    void update(Object obj);
    void create(Object obj);
    void delete(int id);
}
