package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Student;

public interface StudentDao extends JpaRepository<Student, Integer> {
    
}