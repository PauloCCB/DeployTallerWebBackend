package com.Plataforma_Educativa.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private UsuarioPerfil usuarioPerfil;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( //aca se crea la tabla intermedia
            name="usuario_roles", // nombre de la tabla itnermedia

            //Asociación de las tablas foraneas
            joinColumns = @JoinColumn(name = "usuario_id",
                              //en caso de no declarar el nombre de la foreign spring le asigna un nombre aleatorio
                              foreignKey = @ForeignKey(name="FK_usuario_roles_usuario")),
            inverseJoinColumns =  @JoinColumn(name = "role_id",
                    foreignKey = @ForeignKey(name="FK_usuario_roles_role"))
    )
    private List<Role> roles = new ArrayList<>();
}
