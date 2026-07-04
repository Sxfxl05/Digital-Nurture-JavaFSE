package com.banking;

import com.banking.service.BankingService;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== WEEK 3: DATA ACCESS LAYER & TRANSACTION ENGINE ===\n");
        
        // 1. Initialize the Banking Service layer and target in-memory database
        BankingService bankingService = new BankingService();
        
        // SCENARIO A: Test an authorized atomic transaction (Should COMMIT)
        System.out.println("--- Scenario A: Processing Authorized Transfer ---");
        System.out.println("[Executing Testing Sequence] Transferring 2500.00 from Account 101 to 102...");
        bankingService.transferFunds(101, 102, 2500.00);
        System.out.println("\n--------------------------------------------------\n");

        // SCENARIO B: Test an unauthorized transaction due to balance constraint (Should ROLLBACK)
        System.out.println("--- Scenario B: Processing Violating Transfer ---");
        System.out.println("[Executing Testing Sequence] Attempting to transfer 60000.00 from Account 102 (Overdraft)...");
        bankingService.transferFunds(102, 101, 60000.00);
        System.out.println("\n--------------------------------------------------\n");

        // 2. Teardown sequence to terminate lingering background threads gracefully
        try {
            com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
            System.out.println("[Driver Engine] MySQL cleanup background threads terminated cleanly.");
        } catch (Exception e) {
            System.err.println("[Driver Engine Error] Failed to explicitly shutdown cleaner thread: " + e.getMessage());
        }
    }
}