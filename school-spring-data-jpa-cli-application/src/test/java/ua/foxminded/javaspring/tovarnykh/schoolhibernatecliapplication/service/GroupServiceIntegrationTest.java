package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.IntegrationTest;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.GroupDao;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Group;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.service.GroupService;

@SpringBootTest
class GroupServiceIntegrationTest extends IntegrationTest {

    @Mock
    private GroupDao groupDao;

    @InjectMocks
    private GroupService groupService;

    @Test
    void add_CheckIsGroupAdd_True() {
        groupService.add("tt-00");

        verify(groupDao, times(1)).save(new Group("tt-00"));
    }

    @Test
    void get_CanGetGroup_True() {
        groupService.get(1);

        verify(groupDao, times(1)).findById(1);
    }

    @Test
    void getAll_CheckCanGetAllList_True() {
        groupService.getAll();

        verify(groupDao, times(1)).findAll();
    }

    @Test
    void update_IsRowUpdatedOnDb_False() {
        groupService.update(1, "tt-01");

        verify(groupDao, times(1)).findById(1);
    }

    @Test
    void delete_IsRowDeleted_False() {
        groupService.delete(1);

        verify(groupDao, times(1)).findById(1);
    }

}