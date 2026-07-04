package com.banking.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

public class BankingService {
    // Switching to an in-memory DB configuration that persists for the lifetime of the JVM run
    private final String url = "jdbc:h2:mem:banking_db;DB_CLOSE_DELAY=-1";
    private final String user = "sa";
    private final String password = ""; 

    public BankingService() {
        // Initialize infrastructure tables and seed records dynamically
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE IF NOT EXISTS Accounts (account_id INT PRIMARY KEY, customer_name VARCHAR(100), balance DECIMAL(12,2))");
            stmt.execute("TRUNCATE TABLE Accounts"); // Reset state
            stmt.execute("INSERT INTO Accounts VALUES (101, 'Safal Chaturvedi', 50000.00)");
            stmt.execute("INSERT INTO Accounts VALUES (102, 'Aditya Sharma', 1500.00)");
            System.out.println("[Database Engine] Embedded DB initialized and seeded successfully.\n");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void transferFunds(int fromAccount, int toAccount, double amount) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false); // Begin manual transaction demarcation

            // 1. Debit operation with balance checking constraints
            String debitSQL = "UPDATE Accounts SET balance = balance - ? WHERE account_id = ? AND balance >= ?";
            try (PreparedStatement psDebit = conn.prepareStatement(debitSQL)) {
                psDebit.setDouble(1, amount);
                psDebit.setInt(2, fromAccount);
                psDebit.setDouble(3, amount);
                int rows = psDebit.executeUpdate();
                if (rows == 0) throw new SQLException("Debit failed: Insufficient funds or invalid account.");
            }

            // 2. Credit operation
            String creditSQL = "UPDATE Accounts SET balance = balance + ? WHERE account_id = ?";
            try (PreparedStatement psCredit = conn.prepareStatement(creditSQL)) {
                psCredit.setDouble(1, amount);
                psCredit.setInt(2, toAccount);
                psCredit.executeUpdate();
            }

            conn.commit(); // Transaction ends cleanly
            System.out.println("[TX COMMIT] Atomically transferred " + amount + " from Account " + fromAccount + " to " + toAccount);

            // Print verified final balances
            try (Statement stmt = conn.createStatement();
                 java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts")) {
                System.out.println("\n--- Current Account Matrix State ---");
                while(rs.next()) {
                    System.out.println("ID: " + rs.getInt("account_id") + " | User: " + rs.getString("customer_name") + " | Balance: " + rs.getDouble("balance"));
                }
            }

        } catch (SQLException e) {
            System.err.println("[TX ROLLBACK] Transaction aborted: " + e.getMessage());
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}