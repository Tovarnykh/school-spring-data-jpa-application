package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.generator.Generator;

@Service
public class CourseService {

    private static final String MESSAGE_POPULATE_EXCEPTION = "Error: Such course already exist";
    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving course";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding course";
    private static final String MESSAGE_UPDATE_EXCEPTION = "Error: Problem with updating course";
    private static final String MESSAGE_DELETE_EXCEPTION = "Error: No such course to delete";

    private CourseDao courseDao;
    private Generator<Course> generator;

    public CourseService(CourseDao courseDao, @Qualifier("courseGenerator") Generator<Course> generator) {
        this.courseDao = courseDao;
        this.generator = generator;
    }

    public void generateData() {
        try {
            List<Course> courses = generator.generate();
            courseDao.addAll(courses);
        } catch (EntityExistsException e) {
            System.out.println(MESSAGE_POPULATE_EXCEPTION);
        }
    }

    public void add(String name, String description) {
        try {
            courseDao.add(new Course(name, description));
        } catch (EntityExistsException | IllegalArgumentException e) {
            System.out.println(MESSAGE_ADD_EXCEPTION);
        }
    }

    public Course get(int courseId) {
        try {
            return courseDao.read(courseId);
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return new Course();
        }
    }

    public List<Course> getAll() {
        try {
            return courseDao.readAll();
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public void update(int courseId, String name, String description) {
        try {
            courseDao.update(new Course(courseId, name, description));
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_UPDATE_EXCEPTION);
        }
    }

    public void delete(int courseId) {
        try {
            courseDao.delete(courseId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_DELETE_EXCEPTION);
        }
    }

}