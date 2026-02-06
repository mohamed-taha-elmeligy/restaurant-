package com.emts.util.crud;

import java.util.List;

public interface ReadRepository<K, V> {
    V findById(K k);
    List<V> findAll();
    boolean exists(K k);

}
