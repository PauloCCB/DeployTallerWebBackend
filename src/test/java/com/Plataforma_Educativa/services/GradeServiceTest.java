package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.repositories.gradeRepository;
import com.Plataforma_Educativa.domains.entity.grade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GradeServiceTest {
    @Mock
    private gradeRepository gradeRepository;

    @InjectMocks
    private gradeService gradeService;

    @Test
    void getAllShouldReturnListOfGrades() {
        // Arrange
        List<grade> gradeList = Arrays.asList(new grade(), new grade());
        when(gradeRepository.findAll()).thenReturn(gradeList);

        // Act
        List<grade> result = gradeService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(gradeRepository).findAll();
    }

    @Test
    void getByIdShouldReturnGradeWhenExists() {
        // Arrange
        Long id = 1L;
        grade grade = new grade();
        when(gradeRepository.findById(id)).thenReturn(Optional.of(grade));

        // Act
        Optional<grade> result = gradeService.getById(id);

        // Assert
        assertTrue(result.isPresent());
        verify(gradeRepository).findById(id);
    }

    @Test
    void getByIdShouldReturnEmptyWhenNotExists() {
        // Arrange
        Long id = 1L;
        when(gradeRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<grade> result = gradeService.getById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(gradeRepository).findById(id);
    }

    @Test
    void saveShouldReturnSavedGrade() {
        // Arrange
        grade grade = new grade();
        when(gradeRepository.save(any(grade.class))).thenReturn(grade);

        // Act
        grade result = gradeService.save(grade);

        // Assert
        assertNotNull(result);
        verify(gradeRepository).save(grade);
    }

    @Test
    void deleteShouldReturnTrueWhenGradeExists() {
        // Arrange
        Long id = 1L;
        when(gradeRepository.existsById(id)).thenReturn(true);
        doNothing().when(gradeRepository).deleteById(id);

        // Act
        boolean result = gradeService.delete(id);

        // Assert
        assertTrue(result);
        verify(gradeRepository).existsById(id);
        verify(gradeRepository).deleteById(id);
    }

    @Test
    void deleteShouldReturnFalseWhenGradeNotExists() {
        // Arrange
        Long id = 1L;
        when(gradeRepository.existsById(id)).thenReturn(false);

        // Act
        boolean result = gradeService.delete(id);

        // Assert
        assertFalse(result);
        verify(gradeRepository).existsById(id);
        verify(gradeRepository, never()).deleteById(id);
    }
}