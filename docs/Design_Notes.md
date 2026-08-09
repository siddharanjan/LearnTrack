# Design Notes

## Why `ArrayList` Instead of a Plain Array

`StudentService`, `CourseService`, and `EnrollmentService` all store their in-memory data in `ArrayList<Student>`, `ArrayList<Course>`, and `ArrayList<Enrollment>` respectively, instead of `Student[]`, `Course[]`, `Enrollment[]`.

The number of students, courses, and enrollments isn't known up front — students get added one at a time from the console menu for the lifetime of the running program. A plain array has a fixed size chosen at creation time, so supporting an unbounded, growing collection with an array would mean manually tracking a "current count" separately from the array's length, and reallocating a bigger array (and copying every element over) whenever it fills up. `ArrayList` does exactly that internal resizing for us, and additionally gives useful behavior out of the box that this project relies on directly:

- `add(...)` to append a new entity without any capacity bookkeeping.
- Iteration via `for (Student s : students)` or an explicit `Iterator` (used in `getStudents()`/`getCourses()` to remove entries while iterating, which a plain array can't safely support without shifting elements manually).
- `isEmpty()` to detect and message an empty list in the UI (e.g. "No students found").

Since none of the services need index-based random access or a fixed-size guarantee, `ArrayList` is the simpler and safer choice here.

## Where Static Members Are Used, and Why

`util/IdGenerator` is the one place static state is used deliberately:

```java
public class IdGenerator {
    public static int studentId = 1;
    public static int courseId = 1;
    public static int enrollmentId = 1;

    public static int studentIdGenerator() { return studentId++; }
    ...
}
```

IDs need to be unique across the whole application and to keep incrementing regardless of which `Student`/`Course`/`Enrollment` object is being created or which service instance is calling it. A static counter is the natural fit: it's shared state that belongs to the *concept* of "the next student ID," not to any single object. If these counters were instance fields on `StudentService` instead, every new `StudentService` would restart numbering from 1, which isn't what we want.

**Known gap:** the counter fields themselves are currently `public static`, which means any code can reach in and do `IdGenerator.studentId = 999` directly, bypassing the generator methods entirely. They should be `private static`, exposing only the `...Generator()` methods — that's the safer version of this same pattern and is called out in the improvement backlog for this project.

## Where Inheritance Is Used, and What It Bought Us

`Person` is the base class for `Student` and `Trainer`, holding the fields common to any person in the system: `firstName`, `lastName`, `email`.

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
    ...
}
```

Without inheritance, `firstName`/`lastName`/`email` (and their getters) would need to be duplicated in both `Student` and `Trainer`, and any future person-like entity (e.g. an `Admin`) would repeat them again. Pulling the shared identity fields into `Person` means:

- `Student`'s constructor only has to worry about the fields that make a student a student (`batch`, `active`); the identity fields are handed off to `Person` via `super(...)`.
- Any behavior added to `Person` later (e.g. full-name formatting) is automatically available to every subtype without copy-pasting it.

**Known gap:** the current hierarchy only reuses fields — it doesn't yet demonstrate polymorphism through an overridden method (e.g. a `getDisplayName()` on `Person` with specialized behavior in `Student`/`Trainer`). `toString()` is overridden on the entities, but that's an `Object` override for debugging output, not the inheritance-driven polymorphism the design is meant to showcase. Also, `Trainer`'s parameterized constructor doesn't currently call `super(...)`, so a `Trainer`'s inherited name/email fields are never actually set — `Student` is the class that correctly demonstrates the pattern.

## Known Limitations (Honest Self-Assessment)

A few issues were found while reviewing this implementation against the brief, noted here deliberately rather than hidden:

1. **`StudentService.getStudents()` / `CourseService.getCourses()` mutate their backing list.** Both methods use an `Iterator` to strip out inactive entries *and return the same list reference*, which means calling "View All Students" after deactivating someone permanently removes that student from memory — not just from that one view. The brief asks for deactivation to replace deletion ("set `active = false}` instead of deleting"), so this needs to change to a non-destructive filter (e.g. build and return a new filtered list, or add a separate `getActiveStudents()` method) rather than removing from the live list.
2. **`EnrollmentService.getEnrollment()` / `markEnrollment()` can throw on the first non-matching record** instead of scanning the full list, because the `throw` sits in the `else` branch of the per-element loop rather than after the loop completes with no match found.
3. **Input parsing isn't wrapped in try/catch** in most of `ConsoleUi` (e.g. `sc.nextInt()`, `Integer.parseInt(...)`, `Enrollment.Status.valueOf(...)`), so non-numeric or invalid input can crash the program with an uncaught exception rather than showing a clean error message.

These are tracked as the next things to fix, in priority order, ahead of any further feature work.