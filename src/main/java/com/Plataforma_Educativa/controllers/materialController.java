package com.Plataforma_Educativa.controllers;

import com.Plataforma_Educativa.domains.entity.material;
import com.Plataforma_Educativa.services.materialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class materialController {

    @Autowired
    private materialService materialService;

    @GetMapping
    public List<material> listMaterials() {
        return materialService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<material> getMaterial(@PathVariable Long id) {
        return materialService.getById(id);
    }

    @PostMapping
    public material createMaterial(@RequestBody material material) {
        return materialService.save(material);
    }

    @DeleteMapping("/{id}")
    public String deleteMaterial(@PathVariable Long id) {
        boolean deleted = materialService.delete(id);
        return deleted ? "Material deleted" : "Error, could not delete";
    }
}
