package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Group;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Student;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JdbcStudentDaoIntegrationTest {

    private StudentDao studentDao;
    private GroupDao groupDao;

    @Autowired
    JdbcStudentDaoIntegrationTest(StudentDao studentDao, GroupDao groupDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
    }

    @Test
    @Order(1)
    void add_CheckIsStudentSaved_True() {
        Group group = new Group("ew-21");
        groupDao.add(group);
        Student testStudent = new Student(group, "Adam", "Adamson");

        studentDao.add(testStudent);

        Student studentDb = studentDao.read(1);
        assertEquals(testStudent.getFirstName(), studentDb.getFirstName());
    }

    @Test
    @Order(2)
    void read_CheckIsSuchStudentExist_True() {
        Student studentsDb = studentDao.read(1);

        assertNotNull(studentsDb);
        assertEquals("Adam", studentsDb.getFirstName());
    }

    @Test
    @Order(3)
    void readAll_TryToResolveAllRows_True() {
        List<Student> studentsDb = studentDao.readAll();

        assertNotNull(studentsDb);
        assertTrue(studentsDb.size() > 0);
    }

    @Test
    @Order(4)
    void update_CheckIsRowUpdated_True() {
        Student testStudent = new Student(1, new Group(1), "Tester", "Testson");

        studentDao.update(testStudent);

        Student testStudentDb = studentDao.read(1);
        assertEquals(testStudent.getFirstName(), testStudentDb.getFirstName());
    }

    @Test
    @Order(5)
    void delete_IsRowDeleted_True() {
        Student testStudent = new Student(1, new Group(1), "Tester", "Testson");

        studentDao.delete(testStudent.getId());

        assertThrows(IllegalArgumentException.class, () -> studentDao.read(1));
    }

}