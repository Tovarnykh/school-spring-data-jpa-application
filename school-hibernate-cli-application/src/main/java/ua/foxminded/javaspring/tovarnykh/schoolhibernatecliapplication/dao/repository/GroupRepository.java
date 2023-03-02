package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.GroupDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Group;

@Repository
@Transactional
public class GroupRepository implements GroupDao {

    private static final String PROPERTY_GROUP_GET_ALL = """
            SELECT g
            FROM Group g
            """;
    private static final String PROPERTY_GROUP_GET_ALL_EAGER = """
            SELECT g
            FROM Group g
            JOIN FETCH g.students
            """;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Group group) throws EntityExistsException, IllegalArgumentException {
        entityManager.persist(group);
    }

    @Override
    public void addAll(List<Group> groups) throws EntityExistsException {
        for (Group group : groups) {
            entityManager.persist(group);
        }
    }

    @Override
    public Group read(int id) throws IllegalArgumentException {
        Group groupDb = entityManager.find(Group.class, id);

        if (groupDb == null) {
            throw new IllegalArgumentException();
        }
        return groupDb;
    }

    @Override
    public List<Group> readAll() throws EmptyResultDataAccessException {
        return entityManager.createQuery(PROPERTY_GROUP_GET_ALL, Group.class).getResultList();
    }

    @Override
    public List<Group> readAllWithAssignedStudents() throws EmptyResultDataAccessException {
        return entityManager.createQuery(PROPERTY_GROUP_GET_ALL_EAGER, Group.class).getResultList();
    }

    @Override
    public void update(Group group) throws IllegalArgumentException {
        Group groupDb = entityManager.find(Group.class, group.getId());

        if (groupDb == null) {
            throw new IllegalArgumentException();
        }
        groupDb.setName(group.getName());
        groupDb.setStudents(group.getStudents());
        entityManager.merge(groupDb);
    }

    @Override
    public void delete(int id) throws IllegalArgumentException {
        Group groupDb = entityManager.find(Group.class, id);

        if (groupDb == null) {
            throw new IllegalArgumentException();
        }
        entityManager.remove(groupDb);
    }

}