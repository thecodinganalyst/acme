package com.example.acme.runner;

import com.example.acme.model.Book;
import com.example.acme.repository.BookRepository;

import net.datafaker.Faker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookSeeder {

    @Bean
    public CommandLineRunner seedBooks(BookRepository bookRepository, Faker faker) {
        return args -> {
            for(int i=0; i<10_000; i++){
                bookRepository.save(Book.fakeBook(faker));
            }
        };
    }
}
