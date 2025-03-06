package com.Plataforma_Educativa.services;

import com.Plataforma_Educativa.repositories.materialRepository;
import com.Plataforma_Educativa.domains.entity.material;

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
class MaterialServiceTest {
    @Mock
    private materialRepository materialRepository;

    @InjectMocks
    private materialService materialService;

    @Test
    void getAllShouldReturnListOfMaterials() {
        // Arrange
        List<material> materialList = Arrays.asList(new material(), new material());
        when(materialRepository.findAll()).thenReturn(materialList);

        // Act
        List<material> result = materialService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(materialRepository).findAll();
    }

    @Test
    void getAllShouldReturnEmptyList() {
        // Arrange
        when(materialRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<material> result = materialService.getAll();

        // Assert
        assertTrue(result.isEmpty());
        verify(materialRepository).findAll();
    }

    @Test
    void getByIdShouldReturnMaterialWhenExists() {
        // Arrange
        Long id = 1L;
        material material = new material();
        when(materialRepository.findById(id)).thenReturn(Optional.of(material));

        // Act
        Optional<material> result = materialService.getById(id);

        // Assert
        assertTrue(result.isPresent());
        verify(materialRepository).findById(id);
    }

    @Test
    void getByIdShouldReturnEmptyWhenNotExists() {
        // Arrange
        Long id = 1L;
        when(materialRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<material> result = materialService.getById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(materialRepository).findById(id);
    }

    @Test
    void saveShouldReturnSavedMaterial() {
        // Arrange
        material material = new material();
        material.setTitle("Test Material");
        when(materialRepository.save(any(material.class))).thenReturn(material);

        // Act
        material result = materialService.save(material);

        // Assert
        assertNotNull(result);
        assertEquals("Test Material", result.getTitle());
        verify(materialRepository).save(material);
    }

    @Test
    void deleteShouldReturnTrueWhenMaterialExists() {
        // Arrange
        Long id = 1L;
        when(materialRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = materialService.delete(id);

        // Assert
        assertTrue(result);
        verify(materialRepository).existsById(id);
        verify(materialRepository).deleteById(id);
    }

    @Test
    void deleteShouldReturnFalseWhenMaterialNotExists() {
        // Arrange
        Long id = 1L;
        when(materialRepository.existsById(id)).thenReturn(false);

        // Act
        boolean result = materialService.delete(id);

        // Assert
        assertFalse(result);
        verify(materialRepository).existsById(id);
        verify(materialRepository, times(0)).deleteById(id);
    }
}