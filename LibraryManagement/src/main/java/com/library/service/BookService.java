package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    // Setter method for Spring IoC container to inject BookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void processBookRecord() {
        System.out.println("[BookService] Running core business logic for library operation...");
        bookRepository.saveData();
    }
}