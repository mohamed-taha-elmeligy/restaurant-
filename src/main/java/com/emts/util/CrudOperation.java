package com.emts.util;

public interface CrudOperation<T> {

    T create(T t);
    T update(T t);
    T findById(int id);
    boolean delete(int id);

}
