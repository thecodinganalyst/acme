package com.example.acme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;

    public static Book fakeBook(Faker faker){
        return Book.builder()
            .author(faker.book().author())
            .title(faker.book().title())
            .build();
    }
}
