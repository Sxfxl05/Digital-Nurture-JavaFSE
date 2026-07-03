package com.library;

import com.library.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        // Run the embedded Spring Boot container
        ApplicationContext context = SpringApplication.run(LibraryManagementApplication.class, args);
        
        // Grab our container-managed service bean and trigger business logic
        BookService bookService = context.getBean(BookService.class);
        bookService.processBookRecord();
    }
}