package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.pojo.StudentCourse;

@Component
public class StudentCourseGenerator implements Generator<StudentCourse> {

    @Override
    public List<StudentCourse> generate() {
        int studentToGenerate = 200;
        int coursesToAsign = 4;
        AtomicInteger iterator = new AtomicInteger(0);
        List<StudentCourse> studentsCourses = new ArrayList<>();

        Stream.iterate(1, n -> n + 1)
        .limit(studentToGenerate)
        .forEachOrdered(studentId -> {
            List<Integer> numbers = new ArrayList<>();
            iterator.set(0);

            Stream.iterate(1, n -> n + 1)
            .limit(getRandom().nextInt(1, coursesToAsign))
            .forEach(courseId -> {
                int generatedCourse = getRandom().nextInt(1, CourseGenerator.COURSES.size() + 1);

                if (!(numbers.contains(generatedCourse))) {
                    numbers.add(generatedCourse);

                    StudentCourse studentCourse = new StudentCourse();
                    studentCourse.setStudentId(studentId);
                    studentCourse.setCourseId(numbers.get(iterator.getAndIncrement()));
                    studentsCourses.add(studentCourse);
                }
            });
        });
        return studentsCourses;
    }

}