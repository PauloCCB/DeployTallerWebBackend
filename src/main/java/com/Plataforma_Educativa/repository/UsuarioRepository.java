package com.Plataforma_Educativa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Plataforma_Educativa.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    // Consulta derivada para buscar usuarios por coincidencia de email
    List<Usuario> findByEmailContaining(String email);

    // Consulta JPQL para buscar usuarios por coincidencia de email
    @Query("SELECT u FROM Usuario u WHERE u.email LIKE %:email%")
    List<Usuario> searchByEmail(String email);


    //Metodos para login

    //Para la autentificación
    Optional<Usuario> findByEmail(String email);

    //Verifica si existe algun usuario con ese correo
    boolean existsByEmail(String email);



}
