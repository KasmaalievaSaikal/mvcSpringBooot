package peaksoft.service;

import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonService {

    void saveLesson(Long courseId, Lesson lesson);

    List<Lesson> getAllLessons();

    Lesson getLessonById(Long id);

    void updateLesson(Long id, Lesson lesson);

    void deleteLesson(Long id);

    void addLessonToCourse(Long courseId, Lesson lesson);

    List<Lesson> getAllLessonWithCourse(Long courseId);

}
