package com.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.repository.BookRepository;

@Service
public class BookService {
    private BookRepository bookRepository;

    // Setter method for Spring IoC container to inject BookRepository
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void processBookRecord() {
        System.out.println("[BookService] Running core business logic for library operation...");
        bookRepository.saveData();
    }
}