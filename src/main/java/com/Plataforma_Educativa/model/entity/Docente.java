package com.Plataforma_Educativa.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "docentes") // Agregar nombre de la tabla para evitar problemas
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    //@OneToMany(mappedBy = "docente", cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "docente_curso",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )

    private List<Curso> cursos;
}
