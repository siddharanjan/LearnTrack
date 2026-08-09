package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotActiveException;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUi {
    private static final StudentService studentService =  new StudentService();
    private static final CourseService courseService =  new CourseService();
    private static final EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);

    static Scanner sc = new Scanner(System.in);

    public static void printMenuItem() {
        System.out.println("============================");
        System.out.println("Learn Track Managemet System");
        System.out.println("============================");
        System.out.println("======Student Management====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student By Id");
        System.out.println("4. Deactivate Student");
        System.out.println("=====Course Management======");
        System.out.println("5. Add new course");
        System.out.println("6. View All Courses");
        System.out.println("7. Activate/Deactivate Course");
        System.out.println("=====Enrollment Management=====");
        System.out.println("8. Enroll a Student in a course");
        System.out.println("9. View enrollment for a student");
        System.out.println("10. Mark enrollment as completed/cancelled");
        System.out.println("11. Exit");
    }

    public static void studentManagementMenu() {
        System.out.println("============================");
        System.out.println("======Student details=======");
        System.out.println("Student First Name:");
        String firstName = sc.nextLine().trim();
        System.out.println("Student Last Name:");
        String lastName = sc.nextLine().trim();
        System.out.println("Student Email Address:");
        String email = sc.nextLine().trim();
        System.out.println("Student batch:");
        String batch = sc.nextLine().trim();
        System.out.println("Student status:");
        boolean status = Boolean.parseBoolean(sc.nextLine().trim());
        Student student = new Student(firstName, lastName, email, batch, status);
        Student addedStudent = studentService.addStudent(student);
        System.out.println("Student added successfully: " +addedStudent);
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }

    public static void viewAllStudents() {
        List<Student> students = studentService.getStudents();
        System.out.println("============================");
        System.out.println("Students Details: ");
        for (Student student : students) {
            System.out.println(student);
        }
        if (students.isEmpty()) {
            System.out.println("No students found");
        }
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }

    public static void searchStudentById() {
        System.out.println("===========================");
        System.out.println("Enter Student Id:");
        int studentId;
        try {
            studentId = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input: Student Id must be a number.");
            System.out.println("Return to previous menu press enter");
            sc.nextLine();
            return;
        }
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            System.out.println("No students found");
        } else if (!student.isActive()) {
            System.out.println("Student not active");
        } else {
            System.out.println("Student Details: " + student);
        }
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }

    public static void deactivateStudent() {
        System.out.println("===========================");
        System.out.println("Enter Student Id:");
        int studentId;
        try {
            studentId = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input: Student Id must be a number.");
            System.out.println("Return to previous menu press enter");
            sc.nextLine();
            return;
        }
        Student student = studentService.deactivateStudent(studentId);
        if (student == null) {
            System.out.println("No students found");
        } else {
            System.out.println("Deactivated student details: " +student);
        }
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }

    public static void addCourse() {
        System.out.println("============================");
        System.out.println("=======Course details=======");
        System.out.println("Course Name: ");
        String courseName = sc.nextLine().trim();
        System.out.println("Course Description: ");
        String courseDescription = sc.nextLine().trim();
        System.out.println("Course Duration in weeks: ");
        int durationInWeeks;
        try {
            durationInWeeks = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Duration in weeks must be a number.");
            System.out.println("Return to previous menu press enter");
            sc.nextLine();
            return;
        }
        System.out.println("Course status: ");
        boolean active = Boolean.parseBoolean(sc.nextLine().trim());
        Course course = new Course(courseName, courseDescription, durationInWeeks, active);
        Course courseAdded = courseService.addCourse(course);
        System.out.println("Course added successfully: " +courseAdded);
        System.out.println("Return to previous menu press enter");
        sc.nextLine();

    }

    public static void viewAllCourses() {
        System.out.println("============================");
        System.out.println("=======Course details=======");
        List<Course> courses = courseService.getCourses();
        for (Course course : courses) {
            System.out.println(course);
        }
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }

    public static void courseActivation() {
        System.out.println("===========================");
        System.out.println("Activate/Deactivate Course");
        System.out.println("1. Activate Course");
        System.out.println("2. Deactivate Course");
        String n = sc.nextLine().trim();
        switch(n) {
            case "1":
                activateCourse();
                break;
            case "2":
                deactivateCourse();
                break;
        }
    }

    private static void activateCourse() {
        System.out.println("Please enter course Id:");
        int courseIdActivation;
        try {
            courseIdActivation = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input: Course Id must be a number.");
            System.out.println("Return to previous menu press enter");
            sc.nextLine();
            return;
        }
        Course courseActivation =  courseService.courseActivation(courseIdActivation);
        if (courseActivation == null) {
            System.out.println("No course found");
        } else {
            System.out.println("Activated course details: " +courseActivation);
        }
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }

    private static void deactivateCourse() {
        System.out.println("Please enter course Id:");
        int courseIdDeActivation;
        try {
            courseIdDeActivation = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input: Course Id must be a number.");
            System.out.println("Return to previous menu press enter");
            sc.nextLine();
            return;
        }
        Course courseActivation =  courseService.courseDeActivation(courseIdDeActivation);
        if (courseActivation == null) {
            System.out.println("No course found");
        } else {
            System.out.println("Deactivated course details: " +courseActivation);
        }
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }

    public static void enrollAStudent() {
        System.out.println("============================");
        System.out.println("=======Enrollment=======");
        System.out.println("Enter student Id: ");
        int studentId;
        int courseId;
        try {
            studentId = sc.nextInt();
            System.out.println("Enter Course Id: ");
            courseId = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input: Student Id and Course Id must be numbers.");
            System.out.println("Return to previous menu press enter");
            sc.nextLine();
            return;
        }
        System.out.println("Enter Enrollment Date: ");
        String enrollmentDate = sc.nextLine().trim();
        try {
            Enrollment enrollment = enrollmentService.enrollStudentInCourse(studentId, courseId, enrollmentDate);
            System.out.println("Enrollment added sucessfully: " + enrollment);
        } catch (EntityNotFoundException | EntityNotActiveException e) {
            System.out.println("Error: "  + e.getMessage());
            System.out.println("Return to previous menu press enter");
            sc.nextLine();
            return;
        }
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }

    public static void viewEnrollmentForStudent() {
        System.out.println("============================");
        System.out.println("=======Enrollment Details=======");
        System.out.println("Student Id: ");
        int studentId;
        try {
            studentId = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input: Student Id must be a number.");
            System.out.println("Return to previous menu press enter");
            sc.nextLine();
            return;
        }
        try {
            Enrollment enrollment = enrollmentService.getEnrollment(studentId);
            System.out.println(enrollment);
            Student student = studentService.getStudentById(studentId);
            System.out.println(student);
            Course course = courseService.getCourseById(enrollment.getCourseId());
            System.out.println(course);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }

    public static void markEnrollment() {
        System.out.println("============================");
        System.out.println("=======Enrollment Status change=======");
        System.out.println("Enter Enrollment Id: ");
        int enrollmentId;
        Enrollment.Status status;
        try {
            enrollmentId = Integer.parseInt(sc.nextLine().trim());
            System.out.println("Enter status (COMPLETED, CANCELLED):");
            String statusInput = sc.nextLine().trim().toUpperCase();
            status = Enrollment.Status.valueOf(statusInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: Enrollment Id must be a number and status must be COMPLETED or CANCELLED.");
            System.out.println("Return to previous menu press enter");
            sc.nextLine();
            return;
        }
        try {
            Enrollment enrollment = enrollmentService.markEnrollment(enrollmentId, status);
            System.out.println("Enrollment Updated successfully: " + enrollment);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }
}
