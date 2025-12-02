package peaksoft.service;

import peaksoft.entity.Course;

import java.util.List;

public interface CourseService {

    void saveCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    void updateCourse(Long id, Course course);

    void deleteCourse(Long id);

    List<Course> getAllCoursesByInstructorId(Long instructorId);

    List<Course> getAllCoursesByStudentId(Long studentId);
}
