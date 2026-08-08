package util;

public class IdGenerator {
    public static int studentId = 0;
    public static int courseId = 0;

    public static int studentIdGenerator(){
        return studentId++;
    }

    public static int courseIdGenerator() {
        return courseId++;
    }
}
