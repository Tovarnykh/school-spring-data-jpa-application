package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;

public interface CourseDao extends JpaRepository<Course, Integer> {
    
    Optional<Course> findByName(String name);
    
}