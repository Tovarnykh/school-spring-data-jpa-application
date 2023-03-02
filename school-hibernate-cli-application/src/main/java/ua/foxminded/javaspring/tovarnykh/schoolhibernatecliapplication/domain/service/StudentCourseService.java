package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.pojo.StudentCourse;

@Service
public class StudentCourseService {

    private static final String MESSAGE_POPULATE_EXCEPTION = "Error: Problem with populating student-courses";
    private static final String MESSAGE_GET_EXCEPTION = "Error: Probably this course or student dont exist student-course";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding student-course";

    private CourseDao courseDao;
    private Generator<StudentCourse> generator;

    public StudentCourseService(CourseDao courseDao,
            @Qualifier("studentCourseGenerator") Generator<StudentCourse> generator) {
        this.courseDao = courseDao;
        this.generator = generator;
    }

    public void generateData() {
        try {
            List<StudentCourse> studentCourse = generator.generate();
            courseDao.enrollAll(studentCourse);
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_POPULATE_EXCEPTION);
        }
    }

    public void enrollStudent(int studentId, int courseId) {
        try {
            courseDao.enrollStudent(new Student(studentId), new Course(courseId));
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_ADD_EXCEPTION);
        }
    }

    public List<StudentCourse> getAllEnrolledStudents() {
        try {
            List<StudentCourse> courses = new ArrayList<>();

            courseDao.readAllWithAssignedStudents().forEach(course -> {
                List<StudentCourse> studentCourse = course.getStudents().stream()
                        .map(student -> new StudentCourse(student.getId(), course.getId(),
                                student.getFirstName() + " " + student.getLastName(), course.getName()))
                        .toList();

                courses.addAll(studentCourse);
            });
            return courses;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public List<Student> getStudentsByCourseName(String courseName) {
        try {
            return courseDao.readAllWithAssignedStudents().stream()
                    .filter(course -> course.getName().equals(courseName)).map(Course::getStudents)
                    .flatMap(Collection::stream)
                    .map(student -> new Student(student.getFirstName(), student.getLastName())).toList();
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public void expelStudent(int studentId, int courseId) {
        try {
            courseDao.expelStudent(new Student(studentId), new Course(courseId));
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

}