package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "table_task")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "localDate")
    private LocalDate localDate;

    @Column(name = "taskName")
    private String taskName;

    @Column(name = "taskStatus")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "description")
    private String description;
}
