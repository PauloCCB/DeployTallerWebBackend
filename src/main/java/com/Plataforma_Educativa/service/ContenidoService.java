package com.Plataforma_Educativa.service;

import com.Plataforma_Educativa.model.dto.ContenidoDTO;
import com.Plataforma_Educativa.model.entity.Contenido;
import java.util.List;

public interface ContenidoService {
    ContenidoDTO subirVideo(ContenidoDTO contenidoDTO);
    ContenidoDTO obtenerContenidoPorId(Long id);
    List<ContenidoDTO> obtenerTodosLosContenidos();
    ContenidoDTO editarContenido(Long id, ContenidoDTO contenidoDTO);
    void eliminarContenido(Long id);
    byte[] descargarContenido(Long id);
}
