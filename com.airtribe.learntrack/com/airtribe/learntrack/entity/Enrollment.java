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

    public Enrollment(int id, int studentId, int courseId, String enrollmentDate, Status status) {
        this.id = id;
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


    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }


    public void setStatus(Status status) {
        this.status = status;
    }
}
