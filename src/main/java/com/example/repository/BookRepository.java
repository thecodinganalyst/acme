package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.acme.model.Book;

@RepositoryRestResource(path = "books", exported = true)
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}
