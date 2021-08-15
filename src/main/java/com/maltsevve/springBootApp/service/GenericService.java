package com.maltsevve.springBootApp.service;

import java.util.List;

public interface GenericService<T, ID>{
    T save(T t);

    T getById(ID id);

    List<T> getAll();

    T deleteById(ID id);
}
