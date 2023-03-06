package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Group;

public interface GroupDao extends JpaRepository<Group, Integer> {

}