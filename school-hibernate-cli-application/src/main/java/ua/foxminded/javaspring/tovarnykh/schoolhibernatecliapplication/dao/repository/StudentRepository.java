package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.StudentDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Group;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Student;

@Repository
@Transactional
public class StudentRepository implements StudentDao {

    private static final String PROPERTY_STUDENT_GET_ALL = """
            SELECT s
            FROM Student s
            """;
    private static final String PROPERTY_STUDENT_GET_ALL_EAGER = """
            SELECT s
            FROM Student s
            LEFT OUTER JOIN FETCH s.courses
            """;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GroupRepository groupRepository;

    StudentRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void add(Student student) throws EntityExistsException, IllegalArgumentException {
        Group groupDb = entityManager.find(Group.class, student.getGroup().getId());
        student.setGroup(groupDb);
        entityManager.persist(student);
    }

    @Override
    public void addAll(List<Student> students) throws EntityExistsException {
        for (Student student : students) {
            Group groupDb = groupRepository.read(student.getGroup().getId());
            student.setGroup(groupDb);
            entityManager.persist(student);
        }
    }

    @Override
    public Student read(int id) throws IllegalArgumentException {
        Student studentDb = entityManager.find(Student.class, id);

        if (studentDb == null) {
            throw new IllegalArgumentException();
        }
        return studentDb;
    }

    @Override
    public List<Student> readAll() throws EmptyResultDataAccessException {
        return entityManager.createQuery(PROPERTY_STUDENT_GET_ALL, Student.class).getResultList();
    }

    @Override
    public List<Student> readAllWithAssignedCourses() throws EmptyResultDataAccessException {
        return entityManager.createQuery(PROPERTY_STUDENT_GET_ALL_EAGER, Student.class).getResultList();
    }

    @Override
    public void update(Student student) throws IllegalArgumentException {
        Student studentDb = entityManager.find(Student.class, student.getId());
        Group groupDb = entityManager.find(Group.class, student.getGroup().getId());

        if (studentDb == null) {
            throw new IllegalArgumentException();
        }
        if (groupDb == null) {
            groupRepository.add(student.getGroup());
        } else {
            studentDb.setGroup(groupDb);
            studentDb.setFirstName(student.getFirstName());
            studentDb.setLastName(student.getLastName());
            entityManager.merge(studentDb);
        }
    }

    @Override
    public void delete(int id) throws IllegalArgumentException {
        Student studentDb = entityManager.find(Student.class, id);

        if (studentDb == null) {
            throw new IllegalArgumentException();
        }
        entityManager.remove(studentDb);
    }

    @Override
    public void enrollToCourse(Student student, Course course) throws IllegalArgumentException {
        Student studentDb = entityManager.find(Student.class, student.getId());

        if (studentDb == null) {
            throw new IllegalArgumentException();
        }
        studentDb.getCourses().add(course);
        entityManager.merge(studentDb);
    }

    @Override
    public void enrollAllToCourse(List<Student> students, Course course) throws IllegalArgumentException {
        students.forEach(student -> {
            Student studentDb = entityManager.find(Student.class, student.getId());

            if (studentDb == null) {
                throw new IllegalArgumentException();
            }
            studentDb.getCourses().add(course);
            entityManager.merge(studentDb);
        });
    }

    @Override
    public void removeFromCourse(Student student, Course course) throws IllegalArgumentException {
        Student studentDb = entityManager.find(Student.class, student.getId());

        if (studentDb == null) {
            throw new IllegalArgumentException();
        }
        studentDb.getCourses().remove(course);
        entityManager.merge(studentDb);
    }

}