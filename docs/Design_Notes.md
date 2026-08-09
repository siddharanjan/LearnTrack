# Design Notes

## Why ArrayList Instead of a Plain Array

`StudentService`, `CourseService`, and `EnrollmentService` all store their data in an `ArrayList` (`ArrayList<Student>`, `ArrayList<Course>`, `ArrayList<Enrollment>`) instead of a plain array.

I don't know ahead of time how many students, courses, or enrollments will be added. They get added one at a time while the program is running. A plain array has a fixed size once you create it, so I would have to track the count myself and make a new bigger array every time it fills up. `ArrayList` already does this resizing for me. It also gives me a few things I use directly in this project:

- `add(...)` to add a new item without worrying about array size.
- A normal for-each loop to go through the list.
- A copy constructor, `new ArrayList<>(students)`, to return a safe copy of the list. `getStudents()` and `getCourses()` return a copy so the caller cannot change the service's real list by mistake.
- `isEmpty()` to check if the list is empty and show a message like "No students found".

None of the services need to access items by index or need a fixed size, so `ArrayList` is the simpler choice here.

## Where I Used Static Members, and Why

`util/IdGenerator` is the one place I use static state on purpose:

```java
public class IdGenerator {
    private static int studentId = 1;
    private static int courseId = 1;
    private static int enrollmentId = 1;

    public static int studentIdGenerator() { return studentId++; }
    // ...similarly for courseIdGenerator() and enrollmentIdGenerator()
}
```

Every student, course, and enrollment needs a unique id, and the id has to keep going up no matter which object is being created or which service is calling it. A static counter fits this well, because the "next id" belongs to the whole application, not to one object. If I made these counters normal instance fields on `StudentService`, every new `StudentService` would start counting from 1 again, which is not what I want.

The counter fields are `private static`. Nothing outside this class can change `studentId` directly. The only way to move it forward is by calling `studentIdGenerator()`, which keeps this shared state safe to use everywhere.

## Where I Used Inheritance, and What It Gave Me

`Person` is the base class for `Student` and `Trainer`. It holds the fields that any person in the system has: `firstName`, `lastName`, `email`.

```java
public class Student extends Person {
    private int id;
    private String batch;
    private boolean active;

    public Student(String firstName, String lastName, String email, String batch, boolean active) {
        super(firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }
}
```

Without inheritance, I would have to repeat `firstName`, `lastName`, `email` (and their getters) in both `Student` and `Trainer`, and again in any future class like `Admin`. Putting these shared fields in `Person` means:

- `Student`'s constructor only has to deal with the fields that make a student a student (`batch`, `active`). The name and email fields are passed up to `Person` with `super(...)`.
- Anything I add to `Person` later (like a full name method) is available to every class that extends it, without copying code.

`Trainer`'s constructor also calls `super(firstName, lastName, email)`, the same way `Student` does, so its inherited fields are actually set.

`Person` has a `getDisplayName()` method that returns the first and last name. `Student` overrides it to also show the batch, and `Trainer` overrides it to add "(Trainer)". This is the polymorphism part of inheritance: if you hold a value as a `Person` but it is really a `Student` or a `Trainer`, calling `getDisplayName()` on it runs the subclass version, not the one in `Person`.
