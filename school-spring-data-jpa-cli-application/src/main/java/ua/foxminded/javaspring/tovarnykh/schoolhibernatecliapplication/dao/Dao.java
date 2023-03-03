package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao;

import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;

import jakarta.persistence.EntityExistsException;

public interface Dao<T> {

    void add(T entity) throws EntityExistsException, IllegalArgumentException;
    
    void addAll(List <T> entity) throws EntityExistsException;

    T read(int id) throws IllegalArgumentException;

    List<T> readAll() throws EmptyResultDataAccessException;

    void update(T entity) throws IllegalArgumentException;

    void delete(int id) throws IllegalArgumentException;

}