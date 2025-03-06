package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.domains.entity.assessment;
import com.Plataforma_Educativa.repositories.assessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class assessmentService {

    @Autowired
    private assessmentRepository assessmentRepository;

    public List<assessment> getAll() {
        return assessmentRepository.findAll();
    }

    public Optional<assessment> getById(Long id) {
        return assessmentRepository.findById(id);
    }

    public assessment save(assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    public boolean delete(Long id) {
        if (assessmentRepository.existsById(id)) {
            assessmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
