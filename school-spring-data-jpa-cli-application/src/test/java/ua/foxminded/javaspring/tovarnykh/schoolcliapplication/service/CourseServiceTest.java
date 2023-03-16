package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.service.CourseService;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.CourseRepository;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Course;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    
    @Mock
    private CourseRepository courseDao;
    
    @InjectMocks
    private CourseService coursService;

    @Test
    void add_CheckIsCourseAdd_True() {
        coursService.add("Health", "");

        verify(courseDao, times(1)).save(new Course(0, "Health", ""));
    }

    @Test
    void get_CanGetCourse_True() {
        coursService.get(1);

        verify(courseDao, times(1)).findById(1);
    }

    @Test
    void getAll_CheckCanGetAllList_True() {
        coursService.getAll();

        verify(courseDao, times(1)).findAll();
    }

    @Test
    void update_IsRowUpdated_True() {
        coursService.update(1, "Health", "");

        verify(courseDao, times(1)).findById(1);
    }

    @Test
    void delete_IsRowDeleted_False() {
        coursService.delete(1);

        verify(courseDao, times(1)).findById(1);
    }

}