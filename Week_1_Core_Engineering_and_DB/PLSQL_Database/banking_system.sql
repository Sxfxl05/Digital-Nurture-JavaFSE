-- ==========================================
-- 1. SETUP SCHEMA TABLES
-- ==========================================
CREATE TABLE Accounts (
    account_id NUMBER PRIMARY KEY,
    customer_name VARCHAR2(100),
    balance NUMBER(12, 2)
);

CREATE TABLE Transaction_Logs (
    log_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_id NUMBER,
    transaction_type VARCHAR2(20),
    amount NUMBER(12, 2),
    log_date TIMESTAMP DEFAULT SYSTIMESTAMP
);

-- Insert dummy seed account records
INSERT INTO Accounts VALUES (101, 'Safal Chaturvedi', 50000.00);
INSERT INTO Accounts VALUES (102, 'Aditya Sharma', 1500.00);
COMMIT;


-- ==========================================
-- 2. STORED PROCEDURE WITH EXCEPTION HANDLING
-- ==========================================
CREATE OR REPLACE PROCEDURE Process_Withdrawal (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) AS
    v_current_balance NUMBER(12, 2);
    v_insufficient_funds EXCEPTION;
BEGIN
    -- Fetch the balance while locking the row for transaction isolation
    SELECT balance INTO v_current_balance 
    FROM Accounts 
    WHERE account_id = p_account_id
    FOR UPDATE;

    -- Business logic check
    IF v_current_balance < p_amount THEN
        RAISE v_insufficient_funds;
    END IF;

    -- Update account records
    UPDATE Accounts 
    SET balance = balance - p_amount 
    WHERE account_id = p_account_id;

    -- Log transaction record explicitly
    INSERT INTO Transaction_Logs (account_id, transaction_type, amount)
    VALUES (p_account_id, 'WITHDRAWAL', p_amount);
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transaction complete. Successfully debited ' || p_amount || ' from account ' || p_account_id);

EXCEPTION
    WHEN v_insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transaction Aborted: Insufficient balance for Account ID ' || p_account_id);
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transaction Error: Target Account ID ' || p_account_id || ' does not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transaction Failed: Unexpected error code ' || SQLCODE);
END;
/


-- ==========================================
-- 3. ROW-LEVEL AUDIT TRIGGER
-- ==========================================
CREATE OR REPLACE TRIGGER Account_Balance_Audit
AFTER UPDATE OF balance ON Accounts
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('[AUDIT TRIGGER] Account ' || :NEW.account_id || ' updated. Old Balance: ' || :OLD.balance || ' | New Balance: ' || :NEW.balance);
END;
/