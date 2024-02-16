package com.example.dz_6.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "table_notes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Note
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "localDateTime")
    @CreationTimestamp
    private LocalDateTime localDateTime;

}
