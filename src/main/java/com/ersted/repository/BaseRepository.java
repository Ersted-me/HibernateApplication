package com.ersted.repository;

import java.util.List;

public interface BaseRepository<T, R> {
    T create(T obj);

    T getById(R id);

    T update(T obj);

    void deleteById(R id);

    List<T> getAll();
}
