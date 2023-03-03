package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Group;

public interface GroupDao extends Dao<Group> {

    List<Group> readAllWithAssignedStudents() throws EmptyResultDataAccessException;

}