package com.rasl.dao;

import java.util.List;

public interface DAO<T> {
    T getOne(int id);
    List<T> getAll();
    void update(int id, T obj);
    void create(T obj);
    void delete(int id);
}
