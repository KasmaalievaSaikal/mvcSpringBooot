package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.entity.Lesson;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;
import peaksoft.service.LessonService;
import peaksoft.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final LessonService lessonService;

    @GetMapping
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();

        Map<Long, List<Instructor>> instructorsMap = new HashMap<>();

        for (Course course : courses) {
            instructorsMap.put(
                    course.getId(),
                    instructorService.getInstructorsNotAssignedToCourse(course.getId())
            );
        }

        model.addAttribute("allCourses", courseService.getAllCourses());
        model.addAttribute("instructorsMap", instructorsMap);
        model.addAttribute("allStudents", studentService.getAllStudentsWithoutCourse());
        return "getAllCourses";
    }

    @PostMapping("/{courseId}/assign-instructor")
    public String assignInstructor(@PathVariable("courseId") Long courseId,
                                   @RequestParam("instructorId") Long instructorId) {
        instructorService.assignInstructorToCourse(courseId, instructorId);
        return "redirect:/courses";
    }

    @PostMapping("/{courseId}/assign-student")
    public String assignStudent(@PathVariable("courseId") Long courseId,
                                @RequestParam("studentId") Long studentId) {
        studentService.assignStudentToCourse(courseId, studentId);
        return "redirect:/courses";
    }

    @GetMapping("/{courseId}/lessons")
    public String courseLessons(@PathVariable Long courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        List<Lesson> lessons = lessonService.getAllLessonWithCourse(courseId);
        model.addAttribute("course", course);
        model.addAttribute("lessons", lessons);
        return "getAllLessonByCourseId";
    }

    @GetMapping("/new")
    public String createCourse(Model model) {
        model.addAttribute("newCourse", new Course());
        return "newCourse";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("newCourse") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/{courseId}")
    public String getCourseById(@PathVariable("courseId") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "courseDetails";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "editCourse";
    }

    @PostMapping("/{id}/update")
    public String updateCourse(@PathVariable("id") Long id, @ModelAttribute("course") Course course) {
        courseService.updateCourse(id, course);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}