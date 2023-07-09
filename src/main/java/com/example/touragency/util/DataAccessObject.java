package com.example.touragency.util;

import java.util.Set;

public abstract class DataAccessObject <T> {
    public abstract T findById(int id);
    public abstract Set<T> getAll();
    public abstract void save(T entity);
    public abstract void update(T entity);
    public abstract void delete(int id);
}
