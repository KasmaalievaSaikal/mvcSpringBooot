package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.service.CourseService;
import peaksoft.service.StudentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("allStudents", studentService.getAllStudents());
        return "getAllStudents";
    }

    @GetMapping("/new")
    public String createStudent(Model model) {
        model.addAttribute("newStudent", new Student());
        return "newStudent";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("newStudent") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/{studentId}")
    public String getStudentById(@PathVariable("studentId") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "studentDetails";
    }

    @GetMapping("/edit/{id}")
    public String updateStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("/{id}/update")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/{id}/courses")
    public String getCoursesByStudent(@PathVariable("id") Long id, Model model) {
        List<Course> courses = courseService.getAllCoursesByStudentId(id);
        model.addAttribute("courses", courses);

        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);

        return "studentCourses";
    }
}
