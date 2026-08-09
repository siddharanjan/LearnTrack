package com.airtribe.learntrack.entity;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private String enrollmentDate;
    private Status status;

    @Override
    public String toString() {
        return "Enrollment{" +
                "Enrollment Id =" + id +
                ", Student Id =" + studentId +
                ", Course Id =" + courseId +
                ", Enrollment Date ='" + enrollmentDate + '\'' +
                ", Enrollment Status =" + status +
                '}';
    }

    public Enrollment(int studentId, int courseId, String enrollmentDate, Status status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }


    public enum Status {
        ACTIVE,
        COMPLETED,
        CANCELLED
    }

    public Enrollment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id != 0) {
            throw new IllegalStateException("ID is already set and cannot be changed.");
        }
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
