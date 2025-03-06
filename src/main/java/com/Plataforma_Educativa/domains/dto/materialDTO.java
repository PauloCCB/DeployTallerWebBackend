package com.Plataforma_Educativa.domains.dto;

import lombok.Data;
import java.util.Date;

@Data
public class materialDTO {
    private String title;
    private String description;
    private Date uploadDate;
    private String url;
    private String fileType; // PDF, DOCX, etc.
}
