package com.emts.util.cli;


public interface WriteCli<T> {
    void displayAll();
    void exists();
    void findById();
    T searchById();
}
