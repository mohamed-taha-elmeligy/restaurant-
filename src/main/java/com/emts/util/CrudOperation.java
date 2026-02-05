package com.emts.util;

public interface CrudOperation<K,T> extends
        ReadRepository<K,T> ,WriteRepository<K,T> {

}
