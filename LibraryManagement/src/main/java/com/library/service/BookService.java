package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Exercise 6 & 7: Constructor Injection
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void processBookRecord() {
        System.out.println("[BookService] Running core business logic for library operation...");
        bookRepository.saveBook();
    }
}