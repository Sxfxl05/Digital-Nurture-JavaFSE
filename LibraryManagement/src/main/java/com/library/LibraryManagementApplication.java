package com.library;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // 1. Initialize the context using the concrete ClassPathXmlApplicationContext class
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 2. Fetch the service bean
        BookService service = (BookService) context.getBean("bookService");

        // 3. Trigger the logic
        service.processBookRecord();

        // 4. Close the context explicitly to prevent the resource leak warning
        context.close();
    }
}