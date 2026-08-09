package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public Student addStudent(Student student) {
        student.setId(IdGenerator.studentIdGenerator());
        students.add(student);
        return student;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (id == student.getId()) {
                return student;
            }
        }
        return null;
    }

    public Student deactivateStudent(int studentId) {
        for (Student student: students) {
            if (studentId == student.getId()) {
                student.setActive(false);
                return student;
            }
        }
        return null;
    }
}
