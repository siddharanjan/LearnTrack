package util;

public class IdGenerator {
    public static int studentId = 1;
    public static int courseId = 1;
    public static int enrollmentId = 1;

    public static int studentIdGenerator(){
        return studentId++;
    }

    public static int courseIdGenerator() {
        return courseId++;
    }

    public static int enrollmentIdGenerator() {
        return enrollmentId++;
    }
}
