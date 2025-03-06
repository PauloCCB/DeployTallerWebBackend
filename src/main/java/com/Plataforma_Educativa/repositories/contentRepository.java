package com.Plataforma_Educativa.repositories;

import com.Plataforma_Educativa.domains.entity.content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface contentRepository extends JpaRepository<content, Long> {
    List<content> findByCourseId(Long courseId);
}
