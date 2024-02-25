package com.example.aspect_dz_8.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Log
@Entity
@Table(name = "aspect_person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person
{
    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Поле не должно быть пустым! ")
    @Size(min = 2, max = 30, message = "имя должно содержать от 2 до 30 символов! ")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Поле не должно быть пустым! ")
    private String surname;

    @Column(name = "age")
    @NotNull(message = "Графу возраст необходмо заполнить! ") // для типа int применяеться @NotNull
    @Max(value = 300, message = "Возраст не может быть более 300 лет!")
    @Min(value = 0, message = "возраст должен быть больше 0! ")
    private int age;

    @Column(name = "email")
    @NotEmpty(message = "Поле не должно быть пустым! ")
    @Email(message = "почта должна соответствовать требованиям email! ")
    private String email;
}