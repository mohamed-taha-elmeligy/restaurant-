package com.emts.util;

import java.util.ArrayList;

public interface ReadRepository<K, V> {
    V findById(K k);
    ArrayList<V> findAll();
    boolean exists(K k);

}
