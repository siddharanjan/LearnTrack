package com.airtribe.learntrack;

import com.airtribe.learntrack.ui.ConsoleUi;

import java.util.NoSuchElementException;

/**
 * Main class for the LearnTrack project. Serves as the application's entry point.
 */
public class Main {
    public static void main(String[] args) {
        try {
            boolean running = true;
            while (running) {
                ConsoleUi.printMenuItem();
                try {
                    String choice = ConsoleUi.readMenuChoice();
                    switch (choice) {
                        case "1":
                            ConsoleUi.addStudent();
                            break;
                        case "2":
                            ConsoleUi.viewAllStudents();
                            break;
                        case "3":
                            ConsoleUi.searchStudentById();
                            break;
                        case "4":
                            ConsoleUi.deactivateStudent();
                            break;
                        case "5":
                            ConsoleUi.addCourse();
                            break;
                        case "6":
                            ConsoleUi.viewAllCourses();
                            break;
                        case "7":
                            ConsoleUi.courseActivation();
                            break;
                        case "8":
                            ConsoleUi.enrollAStudent();
                            break;
                        case "9":
                            ConsoleUi.viewEnrollmentForStudent();
                            break;
                        case "10":
                            ConsoleUi.markEnrollment();
                            break;
                        case "11":
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid option, please choose a number from the menu.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("No more input available. Exiting.");
                    running = false;
                } catch (Exception e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                    System.out.println("Returning to the main menu.");
                }
            }
        } finally {
            ConsoleUi.closeScanner();
        }
    }
}
