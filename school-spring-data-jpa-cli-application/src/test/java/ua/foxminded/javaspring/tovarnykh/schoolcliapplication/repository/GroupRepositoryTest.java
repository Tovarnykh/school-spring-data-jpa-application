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

import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Group;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GroupRepositoryTest {

    private GroupRepository groupDao;

    @Autowired
    GroupRepositoryTest(GroupRepository groupDao) {
        this.groupDao = groupDao;
    }

    @Test
    @Order(1)
    void add_CheckIsGroupSaved_True() {
        Group testGroup = new Group("test");

        groupDao.save(testGroup);

        Optional<Group> groupDb = groupDao.findById(1);
        assertTrue(groupDb.isPresent());
        assertEquals(testGroup.getName(), groupDb.get().getName());
    }

    @Test
    @Order(2)
    void addAll_CheckIsManyGroupsSaves_True() {
        List<Group> groups = List.of(new Group("te-11"), new Group("te-22"));

        groupDao.saveAll(groups);

        List<Group> groupsDb = groupDao.findAll();
        assertNotNull(groupsDb);
        assertEquals(3, groupsDb.size());
    }

    @Test
    @Order(3)
    void read_CheckIsSuchGroupExist_True() {
        Optional<Group> groupsDb = groupDao.findById(1);

        assertTrue(groupsDb.isPresent());
        assertEquals("test", groupsDb.get().getName());
    }

    @Test
    @Order(4)
    void readAll_TryToResolveAllRows_True() {
        List<Group> groupsDb = groupDao.findAll();

        assertNotNull(groupsDb);
        assertEquals(3, groupsDb.size());
    }

    @Test
    @Order(5)
    void delete_IsRowDeleted_True() {
        Optional<Group> groupDb = groupDao.findById(1);
        
        groupDao.delete(groupDb.get());

        assertTrue(groupDao.findById(1).isEmpty());
    }

}