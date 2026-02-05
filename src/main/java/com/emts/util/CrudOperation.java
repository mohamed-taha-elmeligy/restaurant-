package com.emts.util;

import java.util.ArrayList;

public interface CrudOperation<K,T> {
    T create(T t);
    T update(T t);
    T findById(K id);
    T delete(K id);
    ArrayList<T> findAll();
    boolean exists (K id);
}
