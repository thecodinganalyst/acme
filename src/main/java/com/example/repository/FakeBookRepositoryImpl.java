package com.example.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.acme.model.Book;
import com.github.javafaker.Faker;

@Component
public class FakeBookRepositoryImpl implements BookRepository {
    private final Faker faker = new Faker();

    @Override
    @NonNull
    public Iterable<Book> findAll(Sort sort) {
        // Simulate a list of 20 books
        List<Book> books = LongStream.range(1, 21)
            .mapToObj(this::generateBook)
            .collect(Collectors.toList());

        // Apply sorting
        Comparator<Book> comparator = null;

        for (Sort.Order order : sort) {
            Comparator<Book> fieldComparator = switch (order.getProperty()) {
                case "title" -> Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER);
                case "author" -> Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER);
                default -> null;
            };

            if (fieldComparator != null) {
                if (order.isDescending()) {
                    fieldComparator = fieldComparator.reversed();
                }

                comparator = (comparator == null) ? fieldComparator : comparator.thenComparing(fieldComparator);
            }
        }

        if (comparator != null) {
            books.sort(comparator);
        }

        return books;
    }

    @Override
    @NonNull
    public Page<Book> findAll(@NonNull Pageable pageable) {
        long offset = pageable.getOffset();
        int pageSize = pageable.getPageSize();

        List<Book> books = LongStream.range(offset, offset + pageSize)
                .mapToObj(this::generateBook)
                .collect(Collectors.toList());

        return new PageImpl<>(books, pageable, 10_000); // simulate total elements
    }

    private Book generateBook(Long id) {
        return new Book(id, faker.book().title(), faker.book().author());
    }
}
