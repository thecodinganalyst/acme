package com.example.acme.runner;

import com.example.acme.model.Book;
import com.example.repository.BookRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final Faker faker = new Faker();

    public BookSeeder(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 10_000; i++) {
            bookRepository.save(new Book(faker.book().title(), faker.book().author()));
        }
    }
}
