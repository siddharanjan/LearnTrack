package com.airtribe.learntrack.entity;

public class Trainer extends Person {
    private int id;
    private String batch;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id != 0) {
            throw new IllegalStateException("ID is already set and cannot be changed.");
        }
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

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    private void setActive(boolean active) {
        this.active = active;
    }

    public Trainer() {
    }

    public Trainer(String firstName, String lastName, String email, String batch, boolean active) {
        super(firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Trainer)";
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "Trainer Id: " + id +
                ", First Name: " + getFirstName() +
                ", Last Name: " + getLastName() +
                ", Email: " + getEmail() +
                ", Batch: " + batch +
                ", Active: " + (active ? "ACTIVE" : "INACTIVE") +
                '}';
    }
}
