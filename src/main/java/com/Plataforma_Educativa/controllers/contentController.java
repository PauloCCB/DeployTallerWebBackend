package com.Plataforma_Educativa.controllers;

import com.Plataforma_Educativa.domains.entity.content;
import com.Plataforma_Educativa.services.contentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contents")
public class contentController {

    @Autowired
    private contentService contentService;

    @GetMapping
    public List<content> listContents() {
        return contentService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<content> getContent(@PathVariable Long id) {
        return contentService.getById(id);
    }

    @PostMapping
    public content createContent(@RequestBody content content) {
        return contentService.save(content);
    }

    @DeleteMapping("/{id}")
    public String deleteContent(@PathVariable Long id) {
        boolean deleted = contentService.delete(id);
        return deleted ? "Content deleted" : "Error, could not delete";
    }
}
