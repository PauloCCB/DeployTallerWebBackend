package com.Plataforma_Educativa.domains.dto;

import lombok.Data;
import java.util.Date;

@Data
public class videoDTO {
    private String title;
    private String description;
    private Date uploadDate;
    private String url;
    private int duration;
}
