
// AccountService.java
package com.tss.service;

import com.tss.dao.AccountDao;
import com.tss.model.Account;
import java.sql.*;

public class AccountService {
    AccountDao dao = new AccountDao();

    public void transferFunds(int fromId, int toId, double amount, Connection conn) {
        Savepoint debitSavepoint = null;

        try {
            conn.setAutoCommit(false);

            Account sender = dao.getAccountById(conn, fromId);
            Account receiver = dao.getAccountById(conn, toId);

            if (sender == null || receiver == null) {
                throw new SQLException("Invalid account ID(s)");
            }

            if (sender.getBalance() < amount) {
                throw new SQLException("Insufficient balance in sender's account.");
            }

            // Deduct from sender
            double updatedSenderBalance = sender.getBalance() - amount;
            dao.updateBalance(conn, fromId, updatedSenderBalance);

            // Savepoint after debit
            debitSavepoint = conn.setSavepoint("AfterDebit");

            // Credit to receiver
            double updatedReceiverBalance = receiver.getBalance() + amount;
            dao.updateBalance(conn, toId, updatedReceiverBalance);

            conn.commit();
            System.out.println("Transfer successful!");

        } catch (SQLException e) {
            try {
                if (debitSavepoint != null) {
                    conn.rollback(debitSavepoint);
                    conn.commit();
                    System.out.println("Credit failed. Debit retained. Reason: " + e.getMessage());
                } else {
                    conn.rollback();
                    System.out.println(" Transfer failed completely. Rolled back.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}