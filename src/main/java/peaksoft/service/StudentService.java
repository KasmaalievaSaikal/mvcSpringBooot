package peaksoft.service;

import peaksoft.entity.Student;

import java.util.List;

public interface StudentService {

    void saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    void updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    void assignStudentToCourse(Long courseId, Long studentId);

    List<Student> getAllStudentsWithoutCourse();

}
