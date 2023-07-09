package com.example.touragency.mappers;

import java.sql.ResultSet;
import java.util.Set;

public interface SetMapper<T> {
    Set<T> mapSet(ResultSet resultSet);
}
