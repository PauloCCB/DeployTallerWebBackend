package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.domains.entity.material;
import com.Plataforma_Educativa.repositories.materialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class materialService {

    @Autowired
    private materialRepository materialRepository;

    public List<material> getAll() {
        return materialRepository.findAll();
    }

    public Optional<material> getById(Long id) {
        return materialRepository.findById(id);
    }

    public material save(material material) {
        return materialRepository.save(material);
    }

    public boolean delete(Long id) {
        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
