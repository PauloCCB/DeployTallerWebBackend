package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.domains.entity.course;
import com.Plataforma_Educativa.repositories.courseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class courseService {

    @Autowired
    private courseRepository courseRepository;

    public List<course> getAll() {
        return courseRepository.findAll();
    }

    public Optional<course> getById(Long id) {
        return courseRepository.findById(id);
    }

    public course save(course course) {
        return courseRepository.save(course);
    }

    public boolean delete(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
