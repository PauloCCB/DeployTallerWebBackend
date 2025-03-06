package com.Plataforma_Educativa.controllers;

import com.Plataforma_Educativa.domains.entity.assessment;
import com.Plataforma_Educativa.services.assessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assessments")
public class assessmentController {

    @Autowired
    private assessmentService assessmentService;

    @GetMapping
    public List<assessment> listAssessments() {
        return assessmentService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<assessment> getAssessment(@PathVariable Long id) {
        return assessmentService.getById(id);
    }

    @PostMapping
    public assessment createAssessment(@RequestBody assessment assessment) {
        return assessmentService.save(assessment);
    }

    @DeleteMapping("/{id}")
    public String deleteAssessment(@PathVariable Long id) {
        boolean deleted = assessmentService.delete(id);
        return deleted ? "Assessment deleted" : "Error, could not delete";
    }
}
