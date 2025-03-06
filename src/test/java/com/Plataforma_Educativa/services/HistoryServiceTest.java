package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.repositories.historyRepository;
import com.Plataforma_Educativa.domains.entity.history;

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
class HistoryServiceTest {
    @Mock
    private historyRepository historyRepository;

    @InjectMocks
    private historyService historyService;

    @Test
    void getAllShouldReturnListOfHistories() {
        // Arrange
        List<history> historyList = Arrays.asList(new history(), new history());
        when(historyRepository.findAll()).thenReturn(historyList);

        // Act
        List<history> result = historyService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(historyRepository).findAll();
    }

    @Test
    void getByIdShouldReturnHistoryWhenExists() {
        // Arrange
        Long id = 1L;
        history history = new history();
        when(historyRepository.findById(id)).thenReturn(Optional.of(history));

        // Act
        Optional<history> result = historyService.getById(id);

        // Assert
        assertTrue(result.isPresent());
        verify(historyRepository).findById(id);
    }

    @Test
    void getByIdShouldReturnEmptyWhenNotExists() {
        // Arrange
        Long id = 1L;
        when(historyRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<history> result = historyService.getById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(historyRepository).findById(id);
    }

    @Test
    void saveShouldReturnSavedHistory() {
        // Arrange
        history history = new history();
        when(historyRepository.save(any(history.class))).thenReturn(history);

        // Act
        history result = historyService.save(history);

        // Assert
        assertNotNull(result);
        verify(historyRepository).save(history);
    }

    @Test
    void deleteShouldReturnTrueWhenHistoryExists() {
        // Arrange
        Long id = 1L;
        when(historyRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = historyService.delete(id);

        // Assert
        assertTrue(result);
        verify(historyRepository).existsById(id);
        verify(historyRepository).deleteById(id);
    }

    @Test
    void deleteShouldReturnFalseWhenHistoryNotExists() {
        // Arrange
        Long id = 1L;
        when(historyRepository.existsById(id)).thenReturn(false);

        // Act
        boolean result = historyService.delete(id);

        // Assert
        assertFalse(result);
        verify(historyRepository).existsById(id);
        verify(historyRepository, times(0)).deleteById(id);
    }
}