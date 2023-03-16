package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.pojo;

import java.util.Objects;

public class StudentCourse {

    private int studentId;
    private int courseId;

    private String studentFullName;
    private String courseName;

    public StudentCourse() {

    }

    public StudentCourse(int studentId, int courseID, String studentFullName, String courseName) {
        super();
        this.studentId = studentId;
        this.courseId = courseID;
        this.studentFullName = studentFullName;
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseID) {
        this.courseId = courseID;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentCourse other = (StudentCourse) obj;
        return courseId == other.courseId && studentId == other.studentId;
    }

    @Override
    public String toString() {
        return "StudentCourse [studentId=" + studentId + ", courseID=" + courseId + "]";
    }

}