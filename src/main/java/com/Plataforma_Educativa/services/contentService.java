package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.domains.entity.content;
import com.Plataforma_Educativa.repositories.contentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class contentService {

    @Autowired
    private contentRepository contentRepository;

    public List<content> getAll() {
        return contentRepository.findAll();
    }

    public Optional<content> getById(Long id) {
        return contentRepository.findById(id);
    }

    public content save(content content) {
        return contentRepository.save(content);
    }

    public boolean delete(Long id) {
        if (contentRepository.existsById(id)) {
            contentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
