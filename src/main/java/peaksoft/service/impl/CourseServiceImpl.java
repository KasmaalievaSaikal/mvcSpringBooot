package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.CourseService;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Course with id " + id + " not found"));
    }

    @Override
    public void updateCourse(Long id, Course course) {
        Course course1 = courseRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Course with id " + id + " not found"));
        course1.setTitle(course.getTitle());
        course1.setImageUrl(course.getImageUrl());
        course1.setDateOfStart(course.getDateOfStart());
        course1.setDescription(course.getDescription());
        courseRepository.save(course1);
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new NoSuchElementException("Course with id " + id + " not found");
        }
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAllCoursesByInstructorId(Long instructorId) {
        return courseRepository.getAllCoursesByInstructorId(instructorId);
    }

    @Override
    public List<Course> getAllCoursesByStudentId(Long studentId) {
        return courseRepository.getAllCoursesByStudentId(studentId);
    }
}
