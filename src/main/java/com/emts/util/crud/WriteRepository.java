package com.emts.util.crud;

public interface WriteRepository<K, V> {
    V create(V v);
    V update(V v);
    V delete(K k);
}
