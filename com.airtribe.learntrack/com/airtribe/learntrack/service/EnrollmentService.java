package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;

import com.airtribe.learntrack.exception.EntityNotActiveException;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private final StudentService studentService;
    private final CourseService courseService;
    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    List<Enrollment> enrollments = new ArrayList<>();

    public Enrollment enrollStudentInCourse(int studentId, int courseId, String enrollmentDate) throws EntityNotFoundException,
            EntityNotActiveException {
        Enrollment enrollment;
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
           throw new EntityNotFoundException("Student not found");
        }
        if (!student.isActive()) {
            throw new EntityNotActiveException("Student not active");
        }
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            throw new EntityNotFoundException("Course not found");
        }
        if (!course.isActive()) {
            throw new EntityNotActiveException("Course not active");
        }
        enrollment = new Enrollment(studentId, courseId, enrollmentDate, Enrollment.Status.ACTIVE);
        enrollment.setId(IdGenerator.enrollmentIdGenerator());
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment getEnrollment(int studentId) throws EntityNotFoundException {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment not found for the student Id");
    }

    public Enrollment markEnrollment(int enrollmentId, Enrollment.Status status) throws EntityNotFoundException {
        for (Enrollment enrollment : enrollments) {
            if (enrollmentId == enrollment.getId()) {
                enrollment.setStatus(status);
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment not found");
    }
}
