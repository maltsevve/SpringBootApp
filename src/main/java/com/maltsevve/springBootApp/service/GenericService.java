package com.maltsevve.springBootApp.service;

import java.util.List;

public interface GenericService<T, ID>{
    void save(T t);

    T getById(ID id);

    List<T> getAll();

    void deleteById(ID id);
}
