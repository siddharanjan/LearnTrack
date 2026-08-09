package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public Course addCourse(Course course) {
        course.setId(IdGenerator.courseIdGenerator());
        courses.add(course);
        return course;
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public Course courseActivation(int courseIdActivation) {
        for(Course course : courses) {
            if(course.getId() == courseIdActivation) {
                course.setActive(true);
                return course;
            }
        }
        return null;
    }

    public Course courseDeActivation(int courseIdDeActivation) {
        for(Course course : courses) {
            if(course.getId() == courseIdDeActivation) {
                course.setActive(false);
                return course;
            }
        }
        return null;
    }

    public Course getCourseById(int courseId) {
        for(Course course : courses) {
            if(course.getId() == courseId) {
                return course;
            }
        }
        return null;
    }
}
