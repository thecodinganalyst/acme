package com.example.acme.runner;

import com.example.acme.model.Book;
import com.example.acme.repository.BookRepository;

import net.datafaker.Faker;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookSeeder {

    @Bean
    public CommandLineRunner seedBooks(BookRepository bookRepository, Faker faker) {
        return args -> {
            if(bookRepository.count() > 0) return;
            List<Book> books = IntStream.of(10_000)
                .mapToObj(i -> fakeBook(faker))
                .toList();
            bookRepository.saveAll(books);
        };
    }

    public static Book fakeBook(Faker faker){
        return Book.builder()
            .author(faker.book().author())
            .title(faker.book().title())
            .build();
    }
}
