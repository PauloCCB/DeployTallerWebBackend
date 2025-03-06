package com.Plataforma_Educativa.controllers;

import com.Plataforma_Educativa.domains.entity.video;
import com.Plataforma_Educativa.services.videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videos")
public class videoController {

    @Autowired
    private videoService videoService;

    @GetMapping
    public List<video> listVideos() {
        return videoService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<video> getVideo(@PathVariable Long id) {
        return videoService.getById(id);
    }

    @PostMapping
    public video createVideo(@RequestBody video video) {
        return videoService.save(video);
    }

    @DeleteMapping("/{id}")
    public String deleteVideo(@PathVariable Long id) {
        boolean deleted = videoService.delete(id);
        return deleted ? "Video deleted" : "Error, could not delete";
    }
}
