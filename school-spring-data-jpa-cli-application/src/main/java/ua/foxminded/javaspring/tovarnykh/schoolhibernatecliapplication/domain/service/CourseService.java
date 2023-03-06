package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.generator.Generator;

@Service
public class CourseService {

    private static final String MESSAGE_UPDATE_EXCEPTION = "Error: No such course to update";
    private static final String MESSAGE_DELETE_EXCEPTION = "Error: No such course to delete";

    private CourseDao courseDao;
    private Generator<Course> generator;

    public CourseService(CourseDao courseDao, @Qualifier("courseGenerator") Generator<Course> generator) {
        this.courseDao = courseDao;
        this.generator = generator;
    }

    public void generateData() {
        List<Course> courses = generator.generate();
        courseDao.saveAll(courses);
    }

    public void add(String name, String description) {
        courseDao.save(new Course(name, description));
    }

    public Course get(int courseId) {
        Optional<Course> courseDb = courseDao.findById(courseId);

        if (courseDb.isEmpty()) {
            String missedCourseName = courseId + "- not found";

            return new Course(missedCourseName, "No Data");
        }

        return courseDb.get();
    }

    public List<Course> getAll() {
        return courseDao.findAll();
    }

    public void update(int courseId, String name, String description) {
        try {
            Optional<Course> courseDb = courseDao.findById(courseId);

            if (courseDb.isEmpty()) {
                throw new IllegalArgumentException();
            }

            Course course = courseDb.get();

            course.setName(name);
            course.setDescription(description);
            courseDao.save(course);
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_UPDATE_EXCEPTION);
        }
    }

    public void delete(int courseId) {
        try {
            Optional<Course> courseDb = courseDao.findById(courseId);

            if (courseDb.isEmpty()) {
                throw new IllegalArgumentException();
            }

            courseDao.delete(courseDb.get());
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_DELETE_EXCEPTION);
        }
    }

}