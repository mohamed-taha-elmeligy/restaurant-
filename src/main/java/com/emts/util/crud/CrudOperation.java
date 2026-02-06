package com.emts.util.crud;

public interface CrudOperation<K,T> extends ReadRepository<K,T>, WriteRepository<K,T> {

}
