package com.pumpit.app.data.local.entity;

public class Exercise {
    private long id;
    private String name;

    public Exercise(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Exercise() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
