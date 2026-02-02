package com.emts.domain.common;

public class Model {
    private final int id ;

    protected Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
