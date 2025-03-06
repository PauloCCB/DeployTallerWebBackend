package com.Plataforma_Educativa.controllers;

import com.Plataforma_Educativa.domains.entity.course;
import com.Plataforma_Educativa.services.courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class courseController {

    @Autowired
    private courseService courseService;

    @GetMapping
    public List<course> listCourses() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<course> getCourse(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @PostMapping
    public course createCourse(@RequestBody course course) {
        return courseService.save(course);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.delete(id);
        return deleted ? "Course deleted" : "Error, could not delete";
    }
}
