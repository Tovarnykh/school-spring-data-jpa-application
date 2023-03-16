package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.service.GroupService;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.GroupRepository;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Group;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Mock
    private GroupRepository groupDao;

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