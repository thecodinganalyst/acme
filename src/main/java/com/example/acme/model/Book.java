package com.example.acme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
}
