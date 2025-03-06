package com.Plataforma_Educativa.repositories;

import com.Plataforma_Educativa.domains.entity.assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface assessmentRepository extends JpaRepository<assessment, Long> {
}
