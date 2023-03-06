package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.IntegrationTest;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.GroupDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.StudentDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.service.StudentService;

@SpringBootTest
class StudentServiceIntegrationTest extends IntegrationTest {

    @Mock
    private StudentDao studentDao;
    
    @Mock
    private GroupDao groupDao;

    @InjectMocks
    private StudentService studentService;
    
    @Test
    void add_CheckIsCourseAdd_True() {
        studentService.add(1, "Adam", "Adamson");

        verify(studentDao, times(1)).save(new Student("Adam", "Adamson"));
    }

    @Test
    void get_CanGetStudent_True() {
        studentService.get(1);

        verify(studentDao, times(1)).findById(1);
    }

    @Test
    void getAll_CheckCanGetAllList_True() {
        studentService.getAll();

        verify(studentDao, times(1)).findAll();
    }

    @Test
    void update_IsRowUpdatedOnDb_False() {
        studentService.update(1, 1, "Adam", "Adamson");

        verify(studentDao, times(1)).findById(1);
    }

    @Test
    void delete_IsRowDeleted_True() {
        studentService.delete(1);

        verify(studentDao, times(1)).findById(1);
    }

}