package com.Plataforma_Educativa.repositories;

import com.Plataforma_Educativa.domains.entity.video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface videoRepository extends JpaRepository<video, Long> {
}
