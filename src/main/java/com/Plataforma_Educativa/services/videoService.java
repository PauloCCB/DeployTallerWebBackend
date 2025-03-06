package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.domains.entity.video;
import com.Plataforma_Educativa.repositories.videoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class videoService {

    @Autowired
    private videoRepository videoRepository;

    public List<video> getAll() {
        return videoRepository.findAll();
    }

    public Optional<video> getById(Long id) {
        return videoRepository.findById(id);
    }

    public video save(video video) {
        return videoRepository.save(video);
    }

    public boolean delete(Long id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
