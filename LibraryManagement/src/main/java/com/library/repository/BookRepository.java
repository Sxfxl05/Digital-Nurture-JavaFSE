package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository

public class BookRepository {
    public void saveData() {
        System.out.println("[BookRepository] Successfully connecting to database and saving book records!");
    }
}