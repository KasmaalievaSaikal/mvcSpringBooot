package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Lesson;
import peaksoft.service.LessonService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/new")
    public String createLesson(@RequestParam Long courseId, Model model) {
        model.addAttribute("newLesson", new Lesson());
        model.addAttribute("courseId", courseId);
        return "newLesson";
    }

    @PostMapping("/save")
    public String saveLesson(@ModelAttribute("newLesson") Lesson lesson,
                             @RequestParam Long courseId) {
        lessonService.saveLesson(courseId, lesson);
        return "redirect:/courses";
    }

    @GetMapping("/{lessonId}")
    public String getLessonById(@PathVariable("lessonId") Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("lesson", lesson);
        return "lessonDetails";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("lesson", lesson);
        return "editLesson";
    }

    @PostMapping("/update/{id}")
    public String updateLesson(@PathVariable("id") Long id, @ModelAttribute("lesson") Lesson lesson) {
        Lesson existingLesson = lessonService.getLessonById(id);
        existingLesson.setTitle(lesson.getTitle());
        existingLesson.setDescription(lesson.getDescription());
        existingLesson.setPublisherDate(lesson.getPublisherDate());
        lessonService.updateLesson(id, existingLesson);
        return "redirect:/courses";
    }

    @PostMapping("/delete/{id}")
    public String deleteLesson(@PathVariable("id") Long id) {
        Lesson lesson = lessonService.getLessonById(id);
        Long courseId = lesson.getCourse().getId();

        lesson.setCourse(null);
        lessonService.deleteLesson(id);
        return "redirect:/courses/" + courseId + "/lessons";
    }
}


