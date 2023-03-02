package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.pojo.StudentCourse;

public interface CourseDao extends Dao<Course> {

    List<Course> readAllWithAssignedStudents() throws EmptyResultDataAccessException;
    
    void enrollStudent(Student student, Course course) throws IllegalArgumentException;

    void enrollAll(List<StudentCourse> students) throws IllegalArgumentException;
    
    void expelStudent(Student student, Course course) throws IllegalArgumentException;

}