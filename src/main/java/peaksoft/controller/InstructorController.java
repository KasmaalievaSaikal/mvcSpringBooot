package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;
    private final CourseService courseService;

    @GetMapping
    public String getAllInstructors(Model model) {
        model.addAttribute("allInstructors", instructorService.getAllInstructors());
        return "getAllInstructors";
    }


    @GetMapping("/new")
    public String createInstructor(Model model) {
        model.addAttribute("newInstructor", new Instructor());
        return "newInstructor";
    }

    @PostMapping("/save")
    public String saveInstructor(@ModelAttribute("newInstructor") Instructor instructor) {
        instructorService.saveInstructor(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/{instructorId}")
    public String getCourseById(@PathVariable("instructorId") Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        return "instructorDetails";
    }

    @GetMapping("/edit/{id}")
    public String updateInstructor(@PathVariable("id") Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        return "editInstructor";
    }

    @PostMapping("/{id}/update")
    public String updateInstructor(@PathVariable("id") Long id, @ModelAttribute("instructor") Instructor instructor) {
        instructorService.updateInstructor(id, instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable("id") Long id) {
        instructorService.deleteInstructor(id);
        return "redirect:/instructors";
    }

    @GetMapping("/{id}/courses")
    public String getCoursesByInstructor(@PathVariable("id") Long id, Model model) {
        List<Course> courses = courseService.getAllCoursesByInstructorId(id);
        model.addAttribute("courses", courses);

        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);

        return "instructorCourses";
    }
}

