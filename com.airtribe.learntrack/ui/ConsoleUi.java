package ui;

import entity.Course;
import entity.Student;
import service.CourseService;
import service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUi {
    static StudentService studentService =  new StudentService();
    static CourseService courseService =  new CourseService();

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
        Student student = studentService.addStudent(firstName, lastName, email, batch, status);
        System.out.println("Student added successfully: " +student);
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
        int studentId = sc.nextInt();
        sc.nextLine();
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
        int studentId = sc.nextInt();
        sc.nextLine();
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
        int durationInWeeks = Integer.parseInt(sc.nextLine().trim());
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
        int courseIdActivation = sc.nextInt();
        sc.nextLine();
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
        int courseIdDeActivation = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter course Id:");
        Course courseActivation =  courseService.courseDeActivation(courseIdDeActivation);
        if (courseActivation == null) {
            System.out.println("No course found");
        } else {
            System.out.println("Deactivated course details: " +courseActivation);
        }
        System.out.println("Return to previous menu press enter");
        sc.nextLine();
    }
}
