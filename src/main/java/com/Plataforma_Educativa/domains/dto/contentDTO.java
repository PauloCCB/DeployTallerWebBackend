package com.Plataforma_Educativa.domains.dto;

import lombok.Data;
import java.util.Date;

@Data
public class contentDTO {
    private String title;
    private String description;
    private Date uploadDate;
    private String downloadUrl;
    private Long courseId;  // Relationship with Course
}
