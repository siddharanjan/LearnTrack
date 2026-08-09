package com.airtribe.learntrack.entity;

public class Trainer extends Person {
    private int id;
    private String batch;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }


    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Trainer() {
    }

    public Trainer(int id, String batch, boolean active) {
        this.id = id;
        this.batch = batch;
        this.active = active;
    }
}
