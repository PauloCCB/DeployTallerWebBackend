package com.Plataforma_Educativa.repository;

import com.Plataforma_Educativa.model.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface  DocenteRepository extends JpaRepository<Docente, Long>{
    // Buscar docentes por nombre (opcional si se quiere filtrar docentes por nombre)
    List<Docente> findByNombreContainingIgnoreCase(String nombre);

    // Buscar docentes por correo (para validar si ya existe un docente con ese correo)
    Docente findByCorreo(String correo);
}
