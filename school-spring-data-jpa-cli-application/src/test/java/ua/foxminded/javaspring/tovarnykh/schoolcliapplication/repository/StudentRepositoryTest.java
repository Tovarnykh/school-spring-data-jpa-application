package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository;

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

import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Student;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRepositoryTest {

    private StudentRepository studentDao;

    @Autowired
    StudentRepositoryTest(StudentRepository studentDao, GroupRepository groupDao) {
        this.studentDao = studentDao;
    }

    @Test
    @Order(1)
    void add_CheckIsStudentSaved_True() {
        Student testStudent = new Student(null, "Adam", "Adamson");

        studentDao.save(testStudent);

        Optional<Student> studentDb = studentDao.findById(1);
        assertTrue(studentDb.isPresent());
        assertEquals(testStudent.getFirstName(), studentDb.get().getFirstName());
    }

    @Test
    @Order(2)
    void addAll_CheckIsManyGroupsSaves_True() {
        List<Student> students = List.of(new Student(null, "Adam", "Adamson"), new Student(null, "Tom", "Tomson"));

        studentDao.saveAll(students);

        List<Student> studentsDb = studentDao.findAll();
        assertNotNull(studentsDb);
        assertTrue(studentsDb.size() > 1);
    }

    @Test
    @Order(3)
    void read_CheckIsSuchStudentExist_True() {
        Optional<Student> studentsDb = studentDao.findById(1);

        assertTrue(studentsDb.isPresent());
        assertEquals("Adam", studentsDb.get().getFirstName());
    }

    @Test
    @Order(4)
    void readAll_TryToResolveAllRows_True() {
        List<Student> studentsDb = studentDao.findAll();

        assertNotNull(studentsDb);
        assertTrue(studentsDb.size() > 0);
    }

    @Test
    @Order(5)
    void delete_IsRowDeleted_True() {
        Optional<Student> studentDb = studentDao.findById(1);

        studentDao.delete(studentDb.get());

        assertTrue(studentDao.findById(1).isEmpty());
    }

}