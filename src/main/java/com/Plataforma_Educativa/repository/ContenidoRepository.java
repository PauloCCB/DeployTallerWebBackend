package com.Plataforma_Educativa.repository;

import com.Plataforma_Educativa.model.entity.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Long> {
}