package com.Plataforma_Educativa.controller;

import com.Plataforma_Educativa.model.dto.CursoDTO;
import com.Plataforma_Educativa.model.entity.Contenido;
import com.Plataforma_Educativa.model.entity.Curso;
import com.Plataforma_Educativa.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping("/")
    public ResponseEntity<Curso> crearCurso(@RequestBody CursoDTO cursoDTO) {
        return ResponseEntity.ok(cursoService.crearCurso(cursoDTO));
    }

    @PutMapping("/{cursoID}")
    public ResponseEntity<Curso> editarCurso(@PathVariable Long cursoID, @RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.editarCurso(cursoID, curso));
    }

    @DeleteMapping("/{cursoID}")
    public ResponseEntity<String> eliminarCurso(@PathVariable Long cursoID) {
        cursoService.eliminarCurso(cursoID);
        return ResponseEntity.ok("Curso eliminado correctamente.");
    }

    @GetMapping("/{cursoID}")
    public ResponseEntity<Curso> obtenerCursoPorID(@PathVariable Long cursoID) {
        return ResponseEntity.ok(cursoService.obtenerCursoPorID(cursoID));
    }

    @PostMapping("/{cursoID}/contenido")
    public ResponseEntity<Contenido> agregarContenido(@PathVariable Long cursoID, @RequestBody Contenido contenido) {
        return ResponseEntity.ok(cursoService.agregarContenido(cursoID, contenido));
    }
}
