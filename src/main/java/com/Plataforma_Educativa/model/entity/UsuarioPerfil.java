package com.Plataforma_Educativa.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario_perfil")
public class UsuarioPerfil {

    @Id
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String dni;
    
    @OneToOne
    @MapsId
    @JoinColumn(name="id")
    private Usuario usuario;
}
