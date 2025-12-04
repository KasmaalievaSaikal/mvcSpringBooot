package peaksoft.service;

import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {

    void saveInstructor(Instructor instructor);

    List<Instructor> getAllInstructors();

    Instructor getInstructorById(Long id);

    void updateInstructor(Long id, Instructor instructor);

    void deleteInstructor(Long id);

    void assignInstructorToCourse(Long courseId, Long instructorId);

//    List<Instructor> getAllInstructorsWithoutCourse();
    List<Instructor> getInstructorsNotAssignedToCourse(Long courseId);
}