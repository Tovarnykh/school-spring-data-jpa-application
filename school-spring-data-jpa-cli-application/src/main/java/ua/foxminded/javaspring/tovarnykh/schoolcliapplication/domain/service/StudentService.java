package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.GroupRepository;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.StudentRepository;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Group;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Student;

@Service
public class StudentService {

    private static final String MESSAGE_UPDATE_EXCEPTION = "Error: Problem with updating student";
    private static final String MESSAGE_DELETE_EXCEPTION = "Error: No such student to delete";

    private StudentRepository studentDao;
    private GroupRepository groupDao;
    private Generator<Student> generator;

    public StudentService(StudentRepository studentDao, GroupRepository groupDao,
            @Qualifier("studentGenerator") Generator<Student> generator) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
        this.generator = generator;
    }

    @Transactional
    public void generateData() {
        List<Student> students = generator.generate();
        students.forEach(student -> add(student.getGroup().getId(), student.getFirstName(), student.getLastName()));
    }

    @Transactional
    public void add(int groupId, String firstName, String lastName) {
        Optional<Group> groupDb = groupDao.findById(groupId);
        Group group = null;

        if (groupDb.isPresent()) {
            group = groupDb.get();
        }

        studentDao.save(new Student(group, firstName, lastName));
    }

    public Student get(int studentId) {
        Optional<Student> studentDb = studentDao.findById(studentId);

        if (studentDb.isEmpty()) {
            return new Student();
        }

        return studentDb.get();
    }

    public List<Student> getAll() {
        return studentDao.findAll();
    }

    public void update(int studentId, int groupId, String firstName, String lastName) {
        try {
            Optional<Student> studentDb = studentDao.findById(studentId);
            Optional<Group> groupDb = groupDao.findById(groupId);

            if (studentDb.isEmpty() || groupDb.isEmpty()) {
                throw new IllegalArgumentException();
            }

            Student student = studentDb.get();
            Group group = groupDb.get();

            student.setGroup(group);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            studentDao.save(student);
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_UPDATE_EXCEPTION);
        }
    }

    public void delete(int studentId) {
        try {
            Optional<Student> studentDb = studentDao.findById(studentId);

            if (studentDb.isEmpty()) {
                throw new IllegalArgumentException();
            }

            studentDao.delete(studentDb.get());
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_DELETE_EXCEPTION);
        }
    }

}