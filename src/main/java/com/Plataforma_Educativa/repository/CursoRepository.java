package com.Plataforma_Educativa.repository;

import com.Plataforma_Educativa.model.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c JOIN c.docentes d WHERE d.id = :docenteId")
    List<Curso> findByDocenteId(@Param("docenteId") Long docenteId);
    //ESTO NO LO MODIFICO QUE ME LO PASÓ CHATGPT
}
