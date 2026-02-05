package com.emts.util;

public interface WriteRepository<K, V> {
    V create(V v);
    V update(V v);
    V delete(K k);
}
