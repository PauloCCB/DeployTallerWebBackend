package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.domains.entity.grade;
import com.Plataforma_Educativa.repositories.gradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class gradeService {

    @Autowired
    private gradeRepository gradeRepository;

    public List<grade> getAll() {
        return gradeRepository.findAll();
    }

    public Optional<grade> getById(Long id) {
        return gradeRepository.findById(id);
    }

    public grade save(grade grade) {
        return gradeRepository.save(grade);
    }

    public boolean delete(Long id) {
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
