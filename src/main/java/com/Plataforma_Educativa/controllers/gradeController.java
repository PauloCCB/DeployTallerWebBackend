package com.Plataforma_Educativa.controllers;

import com.Plataforma_Educativa.domains.entity.grade;
import com.Plataforma_Educativa.services.gradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grades")
public class gradeController {

    @Autowired
    private gradeService gradeService;

    @GetMapping
    public List<grade> listGrades() {
        return gradeService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<grade> getGrade(@PathVariable Long id) {
        return gradeService.getById(id);
    }

    @PostMapping
    public grade createGrade(@RequestBody grade grade) {
        return gradeService.save(grade);
    }

    @DeleteMapping("/{id}")
    public String deleteGrade(@PathVariable Long id) {
        boolean deleted = gradeService.delete(id);
        return deleted ? "Grade deleted" : "Error, could not delete";
    }
}
