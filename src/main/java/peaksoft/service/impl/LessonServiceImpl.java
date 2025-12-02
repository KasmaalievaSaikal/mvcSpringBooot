package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course with id " + courseId + " not found"));

        lesson.setCourse(course);
        lessonRepository.save(lesson);
        course.getLessons().add(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Lesson with id " + id + " not found"));
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        Lesson lesson1 = lessonRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Lesson with id " + id + " not found"));
        lesson1.setTitle(lesson.getTitle());
        lesson1.setImageUrl(lesson.getImageUrl());
        lesson1.setPublisherDate(lesson.getPublisherDate());
        lesson1.setDescription(lesson.getDescription());
        lessonRepository.save(lesson1);
    }

    @Override
    public void deleteLesson(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new NoSuchElementException("Lesson with id " + id + " not found");
        }
        lessonRepository.deleteById(id);
    }

    @Override
    public void addLessonToCourse(Long courseId, Lesson lesson) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NoSuchElementException("Course with id " + courseId + " not found"));
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        course.getLessons().add(lesson);
    }

    @Override
    public List<Lesson> getAllLessonWithCourse(Long courseId) {
        return lessonRepository.getAllLessonsByCourseId(courseId);
    }
}
