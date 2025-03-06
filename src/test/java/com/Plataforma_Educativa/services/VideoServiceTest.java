package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.repositories.videoRepository;
import com.Plataforma_Educativa.domains.entity.video;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {
    @Mock
    private videoRepository videoRepository;

    @InjectMocks
    private videoService videoService;

    @Test
    void getAllShouldReturnListOfVideos() {
        // Arrange
        List<video> videoList = Arrays.asList(new video(), new video());
        when(videoRepository.findAll()).thenReturn(videoList);

        // Act
        List<video> result = videoService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(videoRepository).findAll();
    }

    @Test
    void getAllShouldReturnEmptyList() {
        // Arrange
        when(videoRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<video> result = videoService.getAll();

        // Assert
        assertTrue(result.isEmpty());
        verify(videoRepository).findAll();
    }

    @Test
    void getByIdShouldReturnVideoWhenExists() {
        // Arrange
        Long id = 1L;
        video video = new video();
        video.setTitle("Test Video");
        when(videoRepository.findById(id)).thenReturn(Optional.of(video));

        // Act
        Optional<video> result = videoService.getById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Test Video", result.get().getTitle());
        verify(videoRepository).findById(id);
    }

    @Test
    void getByIdShouldReturnEmptyWhenNotExists() {
        // Arrange
        Long id = 1L;
        when(videoRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<video> result = videoService.getById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(videoRepository).findById(id);
    }

    @Test
    void saveShouldReturnSavedVideo() {
        // Arrange
        video video = new video();
        video.setTitle("Test Video");
        video.setDescription("Test Description");
        video.setUrl("http://test.com/video");
        video.setDuration(120);

        when(videoRepository.save(any(video.class))).thenReturn(video);

        // Act
        video result = videoService.save(video);

        // Assert
        assertNotNull(result);
        assertEquals("Test Video", result.getTitle());
        assertEquals("Test Description", result.getDescription());
        assertEquals("http://test.com/video", result.getUrl());
        assertEquals(120, result.getDuration());
        verify(videoRepository).save(video);
    }

    @Test
    void deleteShouldReturnTrueWhenVideoExists() {
        // Arrange
        Long id = 1L;
        when(videoRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = videoService.delete(id);

        // Assert
        assertTrue(result);
        verify(videoRepository).existsById(id);
        verify(videoRepository).deleteById(id);
    }

    @Test
    void deleteShouldReturnFalseWhenVideoNotExists() {
        // Arrange
        Long id = 1L;
        when(videoRepository.existsById(id)).thenReturn(false);

        // Act
        boolean result = videoService.delete(id);

        // Assert
        assertFalse(result);
        verify(videoRepository).existsById(id);
        verify(videoRepository, never()).deleteById(id);
    }
}