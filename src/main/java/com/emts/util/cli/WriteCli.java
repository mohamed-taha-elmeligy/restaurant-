package com.emts.util.cli;


public interface WriteCli<K,T> {
    void displayAll();
    void exists();
    void findById();
    T searchById(K k);
}
