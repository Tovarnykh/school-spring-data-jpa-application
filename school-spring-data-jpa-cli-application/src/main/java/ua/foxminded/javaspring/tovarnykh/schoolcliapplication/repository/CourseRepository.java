package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    
    Optional<Course> findByName(String name);
    
}