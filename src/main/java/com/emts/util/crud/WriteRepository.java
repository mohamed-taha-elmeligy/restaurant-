package com.emts.util.crud;

public interface WriteRepository<K, V> {
    V create(K k, V v);
    V update(K k, V v);
    V delete(K k);
}
