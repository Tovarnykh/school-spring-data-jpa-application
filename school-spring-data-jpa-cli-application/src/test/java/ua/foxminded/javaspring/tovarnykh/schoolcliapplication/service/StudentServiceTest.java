package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.IntegrationTest;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.service.StudentService;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.GroupRepository;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.StudentRepository;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Student;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest extends IntegrationTest {

    @Mock
    private StudentRepository studentDao;
    
    @Mock
    private GroupRepository groupDao;

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