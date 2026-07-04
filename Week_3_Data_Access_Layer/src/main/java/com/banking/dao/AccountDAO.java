package com.banking.dao;

import com.banking.model.Account;
import java.sql.SQLException;

public interface AccountDAO {
    Account getAccount(int accountId) throws SQLException;
    void updateBalance(int accountId, double newBalance) throws SQLException;
}