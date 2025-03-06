package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.domains.entity.history;
import com.Plataforma_Educativa.repositories.historyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class historyService {

    @Autowired
    private historyRepository historyRepository;

    public List<history> getAll() {
        return historyRepository.findAll();
    }

    public Optional<history> getById(Long id) {
        return historyRepository.findById(id);
    }

    public history save(history history) {
        return historyRepository.save(history);
    }

    public boolean delete(Long id) {
        if (historyRepository.existsById(id)) {
            historyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
