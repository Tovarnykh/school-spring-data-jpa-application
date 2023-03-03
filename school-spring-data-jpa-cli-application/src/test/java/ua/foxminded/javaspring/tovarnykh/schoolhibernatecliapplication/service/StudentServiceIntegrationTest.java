package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.IntegrationTest;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.StudentDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Group;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.service.StudentService;

@SpringBootTest
class StudentServiceIntegrationTest extends IntegrationTest {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentService studentService;

    @Test
    void get_CanGetStudent_True() {
        when(studentDao.read(1)).thenReturn(new Student(new Group("tt-00"), "Adam", "Adamson"));

        Student studentDb = studentService.get(1);

        assertNotNull(studentDb);
        assertEquals("Adam" ,studentDb.getFirstName());
    }

    @Test
    void getAll_CheckCanGetAllList_True() {
        when(studentDao.readAll()).thenReturn(List.of(new Student(new Group("tt-00"), "Adam", "Adamson"), new Student(new Group("tt-00"), "John", "Johnson")));

        List<Student> students = studentService.getAll();

        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    void update_IsRowUpdatedOnDb_False() {
        studentDao.update(new Student(new Group("tt-00"), "Adam", "Adamson"));

        verify(studentDao).update(new Student(new Group("tt-00"), "Adam", "Adamson"));
    }

    @Test
    void delete_IsRowDeleted_True() {
        when(studentDao.read(1)).thenReturn(new Student(new Group("tt-00"), "Adam", "Adamson"));
        
        studentService.delete(1);

        verify(studentDao).delete(1);
    }

}
