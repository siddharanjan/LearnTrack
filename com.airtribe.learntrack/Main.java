import exception.EntityNotFoundException;
import ui.ConsoleUi;

import java.util.Scanner;

/**
 * Main class for the LearnTrack project. Serves as the application's entry point.
 */
public class Main {
    public static void main(String[] args) throws EntityNotFoundException {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            ConsoleUi.printMenuItem();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    ConsoleUi.studentManagementMenu();
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
            }
        }
    }
}