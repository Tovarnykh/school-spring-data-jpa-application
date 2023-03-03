package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.generator;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Course;

@Component
public class CourseGenerator implements Generator<Course> {

    public static final List<String> COURSES = List.of("Mathematics", "Science", "Language Arts", "Health", "Handwriting",
            "Physical Education", "Art", "Music", "Instrumental Music", "Dance");

    @Override
    public List<Course> generate() {
        return COURSES.stream().map(Course::new).toList();
    }

}