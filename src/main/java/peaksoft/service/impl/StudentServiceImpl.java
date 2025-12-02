package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Student with id " + id + " not found"));
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = studentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Student with id " + id + " not found"));
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setImageUrl(student.getImageUrl());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        studentRepository.save(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NoSuchElementException("Student with id " + id + " not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NoSuchElementException("Course with id " + courseId + " not found"));

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new NoSuchElementException("Student with id " + studentId + " not found"));
        student.getCourses().add(course);
        course.getStudents().add(student);
    }

    @Override
    public List<Student> getAllStudentsWithoutCourse() {
        return studentRepository.findStudentsByCoursesIsNull();
    }
}
