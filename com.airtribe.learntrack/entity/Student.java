package entity;

public class Student extends Person {
    private int id;
    private String batch;
    private boolean active;

    public Student(int id, String batch, boolean active) {
        this.id = id;
        this.batch = batch;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Student Id: " + id +
                ", First Name: "+ getFirstName() +
                ", Last Name: "+ getLastName() +
                ", Email: "+ getEmail() +
                ", Batch: " + batch  +
                ", Active: " + active +
                '}';
    }

    public Student() {
    }

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
}
