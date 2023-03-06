package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JdbcCourseDaoIntegrationTest {

    @Autowired
    private CourseDao courseDao;

    @Test
    @Order(1)
    void add_CheckIsCourseSaved_True() {
        Course testCourse = new Course("Test");
        courseDao.save(testCourse);

        Optional<Course> courseDB = courseDao.findById(1);

        assertTrue(courseDB.isPresent());
        assertEquals(testCourse.getName(), courseDB.get().getName());
    }

    @Test
    @Order(2)
    void addAll_CheckIsManyCoursesSaves_True() {
        List<Course> courses = List.of(new Course("Test2"), new Course("Test3"));
        courseDao.saveAll(courses);

        List<Course> coursesDB = courseDao.findAll();

        assertNotNull(coursesDB);
        assertEquals(3, coursesDB.size());
    }

    @Test
    @Order(3)
    void read_CheckIsSuchCourseExist_True() {
        Optional<Course> courseDb = courseDao.findById(2);

        assertTrue(courseDb.isPresent());
        assertEquals("Test2", courseDb.get().getName());
    }

    @Test
    @Order(4)
    void readAll_TryToResolveAllRows_True() {
        List<Course> coursesDb = courseDao.findAll();

        assertNotNull(coursesDb);
        assertEquals(3, coursesDb.size());
    }

    @Test
    @Order(5)
    void delete_IsRowDeleted_True() {
        Optional<Course> courseDb = courseDao.findById(3);

        courseDao.delete(courseDb.get());

        assertTrue(courseDao.findById(3).isEmpty());
    }

}