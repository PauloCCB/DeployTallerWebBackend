package com.Plataforma_Educativa.controllers;

import com.Plataforma_Educativa.domains.entity.history;
import com.Plataforma_Educativa.services.historyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/history")
public class historyController {

    @Autowired
    private historyService historyService;

    @GetMapping
    public List<history> listHistories() {
        return historyService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<history> getHistory(@PathVariable Long id) {
        return historyService.getById(id);
    }

    @PostMapping
    public history createHistory(@RequestBody history history) {
        return historyService.save(history);
    }

    @DeleteMapping("/{id}")
    public String deleteHistory(@PathVariable Long id) {
        boolean deleted = historyService.delete(id);
        return deleted ? "History deleted" : "Error, could not delete";
    }
}
