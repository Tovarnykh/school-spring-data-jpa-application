package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.GroupRepository;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Group;

@Service
public class GroupService {

    private static final String MESSAGE_UPDATE_EXCEPTION = "Error: Problem with updating group";
    private static final String MESSAGE_DELETE_EXCEPTION = "Error: No such group to delete";

    private GroupRepository groupDao;
    private Generator<Group> generator;

    public GroupService(GroupRepository groupDao, @Qualifier("groupGenerator") Generator<Group> generator) {
        this.groupDao = groupDao;
        this.generator = generator;
    }

    @Transactional
    public void generateData() {
        List<Group> groups = generator.generate();
        groupDao.saveAll(groups);
    }

    public void add(String name) {
        groupDao.save(new Group(name));
    }

    public Group get(int groupId) {
        Optional<Group> groupDb = groupDao.findById(groupId);

        if (groupDb.isEmpty()) {
            return new Group("No Data");
        }

        return groupDb.get();
    }

    public List<Group> getAll() {
        return groupDao.findAll();
    }

    public void update(int groupId, String name) {
        try {
            Optional<Group> groupDb = groupDao.findById(groupId);

            if (groupDb.isEmpty()) {
                throw new IllegalArgumentException();
            }

            Group group = groupDb.get();

            group.setName(name);
            groupDao.save(group);
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_UPDATE_EXCEPTION);
        }
    }

    public void delete(int groupId) {
        try {
            Optional<Group> groupDb = groupDao.findById(groupId);

            if (groupDb.isEmpty()) {
                throw new IllegalArgumentException();
            }

            groupDao.delete(groupDb.get());
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_DELETE_EXCEPTION);
        }
    }

    public List<Group> getGroupsWithLessStudents(int numberOfStudents) {
        return groupDao.findAll().stream()
                .filter(group -> group.getStudents().size() <= numberOfStudents)
                .toList();
    }

}