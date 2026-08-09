package com.airtribe.learntrack.entity;

public class Course {
    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    public Course(String courseName, String courseDescription, int durationInWeeks, boolean active) {
        this.courseName = courseName;
        this.description = courseDescription;
        this.durationInWeeks = durationInWeeks;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Course Id =" + id +
                ", Course Name ='" + courseName + '\'' +
                ", Course Description='" + description + '\'' +
                ", Course Duration in weeks =" + durationInWeeks +
                ", Course Status =" + active +
                '}';
    }
}
