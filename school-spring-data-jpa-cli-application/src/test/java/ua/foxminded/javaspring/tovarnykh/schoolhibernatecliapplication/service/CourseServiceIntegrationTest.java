package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.IntegrationTest;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.service.CourseService;

@SpringBootTest
class CourseServiceIntegrationTest extends IntegrationTest {
    
    @Mock
    private CourseDao courseDao;
    
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