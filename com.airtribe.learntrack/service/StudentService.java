package service;

import entity.Student;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public Student addStudent(String firstName, String lastName, String email, String batch, boolean status) {
        Student student = new Student();
        student.setId(IdGenerator.studentIdGenerator());
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        student.setActive(status);
        students.add(student);
        return student;
    }

    public List<Student> getStudents() {
        Iterator<Student> iterator = students.iterator();
        while(iterator.hasNext()) {
            Student student = iterator.next();
            if(!student.isActive()) {
                iterator.remove();
            }
        }
        return students;
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
