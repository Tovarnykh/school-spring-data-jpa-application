package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.service.CourseService;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.service.GroupService;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.service.StudentCourseService;
import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.service.StudentService;

@Component
public class DataGenerator {

    private GroupService groupService;
    private StudentService studentService;
    private CourseService courseService;
    private StudentCourseService studentCourseService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    DataGenerator(GroupService groupService, StudentService studentService, CourseService courseService,
            StudentCourseService studentCourseService) {
        this.groupService = groupService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentCourseService = studentCourseService;
    }

    public void populateDatabase() {
        logger.info("Database has started");
        logger.info("Checking data in database...");

        if (groupService.getAll().isEmpty()) {
            groupService.generateData();
        }
        if (studentService.getAll().isEmpty()) {
            studentService.generateData();
        }
        if (courseService.getAll().isEmpty()) {
            courseService.generateData();
        }
        if (studentCourseService.getAllEnrolledStudents().isEmpty()) {
            studentCourseService.generateData();
        }
    }

}