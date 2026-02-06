package com.emts.domain.models.common;

public class Person extends Model {

    private String name ;

    public Person(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }
}
