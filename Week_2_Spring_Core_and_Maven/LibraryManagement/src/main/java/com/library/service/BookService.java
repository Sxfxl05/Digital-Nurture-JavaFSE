package com.library.service;

import com.library.repository.BookRepository;
import com.library.annotation.TrackExecutionTime; // <-- Ensure this import is here
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @TrackExecutionTime // <-- Ensure this annotation is right here
    public void processBookRecord() {
        System.out.println("[BookService] Running core business logic for library operation...");
        bookRepository.saveBook();
    }
}