package com.emts.domain.models.common;

public class Model {
    private final int id ;

    protected Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
