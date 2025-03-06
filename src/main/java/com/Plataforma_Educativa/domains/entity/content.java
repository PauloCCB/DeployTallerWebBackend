package com.Plataforma_Educativa.domains.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "content")
public class content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Date uploadDate;
    private String downloadUrl;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private course course;
}
