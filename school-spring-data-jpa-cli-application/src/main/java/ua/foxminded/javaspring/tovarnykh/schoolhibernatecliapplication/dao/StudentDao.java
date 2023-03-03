package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Student;

public interface StudentDao extends Dao<Student> {
    
    List<Student> readAllWithAssignedCourses() throws EmptyResultDataAccessException;

    void enrollToCourse(Student student, Course course) throws IllegalArgumentException;

    void enrollAllToCourse(List<Student> students, Course course) throws IllegalArgumentException;
    
    void removeFromCourse(Student student, Course course) throws IllegalArgumentException;
    
}