package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

}