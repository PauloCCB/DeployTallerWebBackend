package com.Plataforma_Educativa.domains.dto;

import lombok.Data;

@Data
public class gradeDTO {
    private Long userId;
    private Long contentId;
    private Double score;
}
