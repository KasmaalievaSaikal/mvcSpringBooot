package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;


    @Override
    public void saveInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Instructor with id " + id + " not found"));
    }


    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = instructorRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Course with id " + id + " not found"));
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setImageUrl(instructor.getImageUrl());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructorRepository.save(instructor1);
    }


    @Override
    public void deleteInstructor(Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new NoSuchElementException("Instructor with id " + id + " not found");
        }
        instructorRepository.deleteById(id);
    }


    @Override
    public void assignInstructorToCourse(Long courseId, Long instructorId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NoSuchElementException("Course with id " + courseId + " not found"));

        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new NoSuchElementException("Instructor with id " + instructorId + " not found"));
        instructor.getCourses().add(course);
        course.getInstructors().add(instructor);
    }

    @Override
    public List<Instructor> getAllInstructorsWithoutCourse() {
        return instructorRepository.findInstructorsByCoursesIsNull();
    }
}
