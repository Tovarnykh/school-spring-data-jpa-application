package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.pojo.StudentCourse;

@Repository
@Transactional
public class CourseRepository implements CourseDao {

    private static final String PROPERTY_COURSE_GET_ALL = """
            SELECT c
            FROM Course c
            """;
    private static final String PROPERTY_COURSE_GET_ALL_EAGER = """
            SELECT c
            FROM Course c
            JOIN FETCH c.students
            """;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Course course) throws EntityExistsException, IllegalArgumentException {
        entityManager.persist(course);
    }

    @Override
    public void addAll(List<Course> courses) throws EntityExistsException {
        for (Course crouse : courses) {
            entityManager.persist(crouse);
        }
    }

    @Override
    public Course read(int id) throws IllegalArgumentException {
        Course courseDb = entityManager.find(Course.class, id);

        if (courseDb == null) {
            throw new IllegalArgumentException();
        }
        return courseDb;
    }

    @Override
    public List<Course> readAll() throws EmptyResultDataAccessException {
        return entityManager.createQuery(PROPERTY_COURSE_GET_ALL, Course.class).getResultList();
    }

    @Override
    public List<Course> readAllWithAssignedStudents() throws EmptyResultDataAccessException {
        return entityManager.createQuery(PROPERTY_COURSE_GET_ALL_EAGER, Course.class).getResultList();
    }

    @Override
    public void update(Course course) throws IllegalArgumentException {
        Course courseDb = entityManager.find(Course.class, course.getId());

        if (courseDb == null) {
            throw new IllegalArgumentException();
        }
        courseDb.setName(course.getName());
        courseDb.setDescription(course.getDescription());
        entityManager.merge(courseDb);
    }

    @Override
    public void delete(int id) throws IllegalArgumentException {
        Course courseDb = entityManager.find(Course.class, id);

        if (courseDb == null) {
            throw new IllegalArgumentException();
        }
        entityManager.remove(courseDb);
    }

    @Override
    public void enrollStudent(Student student, Course course) throws IllegalArgumentException {
        Student studentDb = entityManager.find(Student.class, student.getId());
        Course courseDb = entityManager.find(Course.class, course.getId());

        if ((studentDb == null) || (courseDb == null)) {
            throw new IllegalArgumentException();
        }
        courseDb.getStudents().add(studentDb);
        entityManager.merge(courseDb);
    }

    @Override
    public void enrollAll(List<StudentCourse> studentCourses) throws IllegalArgumentException {
        studentCourses.forEach(studentCourse -> {
            Student studentDb = entityManager.find(Student.class, studentCourse.getStudentId());
            Course courseDb = entityManager.find(Course.class, studentCourse.getCourseId());

            if ((studentDb == null) || (courseDb == null)) {
                throw new IllegalArgumentException();
            }
            studentDb.getCourses().add(courseDb);
            entityManager.merge(studentDb);
        });
    }

    @Override
    public void expelStudent(Student student, Course course) throws IllegalArgumentException {
        Student studentDb = entityManager.find(Student.class, student.getId());
        Course courseDb = entityManager.find(Course.class, course.getId());

        if ((studentDb == null) || (courseDb == null)) {
            throw new IllegalArgumentException();
        }
        courseDb.getStudents().remove(studentDb);
        entityManager.merge(courseDb);
    }

}