package com.Plataforma_Educativa.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contenidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String url;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
