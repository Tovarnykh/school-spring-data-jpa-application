package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.pojo.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.CourseRepository;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.StudentRepository;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Course;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.repository.entity.Student;

@Service
public class StudentCourseService {

    private CourseRepository courseDao;
    private StudentRepository studentDao;
    private Generator<StudentCourse> generator;

    public StudentCourseService(CourseRepository courseDao, StudentRepository studentDao,
            @Qualifier("studentCourseGenerator") Generator<StudentCourse> generator) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.generator = generator;
    }

    @Transactional
    public void generateData() {
        List<StudentCourse> studentsCourses = generator.generate();
        enrollAllStudents(studentsCourses);
    }

    public void enrollStudent(int studentId, int courseId) {
        Optional<Student> studentDb = studentDao.findById(studentId);
        Optional<Course> courseDb = courseDao.findById(courseId);
        
        if (studentDb.isPresent() && courseDb.isPresent()) {
            Course course = courseDb.get();
            Student student = studentDb.get();

            student.getCourses().add(course);
            studentDao.save(student);
        }
    }

    @Transactional
    public void enrollAllStudents(List<StudentCourse> studentCourses) {

        studentCourses
                .forEach(studentCourse -> enrollStudent(studentCourse.getStudentId(), studentCourse.getCourseId()));

    }

    public List<StudentCourse> getAllEnrolledStudents() {
        List<StudentCourse> studentCourses = new ArrayList<>();

        studentDao.findAll().forEach(
                student -> student.getCourses().forEach(course -> studentCourses.add(new StudentCourse(student.getId(),
                        course.getId(), student.getFirstName() + "" + student.getLastName(), course.getName()))));

        return studentCourses;
    }

    public List<Student> getStudentsByCourseName(String courseName) {
        Optional<Course> courseDb = courseDao.findByName(courseName);

        if (courseDb.isPresent()) {
            return courseDb.get().getStudents();
        }
        return List.of();
    }

    public void expelStudent(int studentId, int courseId) {
        Optional<Student> studentDb = studentDao.findById(studentId);
        Optional<Course> courseDb = courseDao.findById(courseId);

        if (studentDb.isPresent() && courseDb.isPresent()) {
            Student student = studentDb.get();
            Iterator<Student> iterator = courseDb.get().getStudents().iterator();

            while (iterator.hasNext()) {
                student = iterator.next();

                if (student.getId() == studentId) {
                    iterator.remove();
                    break;
                }
            }
            studentDao.save(student);
        }
    }

}