package com.Plataforma_Educativa.domains.dto;

import lombok.Data;
import java.util.Date;

@Data
public class historyDTO {
    private Long contentId;
    private Long userId;
    private Date accessDate;
}
