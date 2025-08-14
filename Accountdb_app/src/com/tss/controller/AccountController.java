
// AccountController.java
package com.tss.controller;

import com.tss.database.DBConnection;
import com.tss.service.AccountService;

import java.sql.Connection;
import java.util.Scanner;

public class AccountController {

    AccountService service = new AccountService();

    public void startFundTransfer() {
        try (Scanner sc = new Scanner(System.in);
             Connection conn = DBConnection.connect()) {

            System.out.print("Enter Sender's Account ID: ");
            int fromId = sc.nextInt();

            System.out.print("Enter Receiver's Account ID: ");
            int toId = sc.nextInt();

            System.out.print("Enter amount to transfer: ");
            double amount = sc.nextDouble();

            service.transferFunds(fromId, toId, amount, conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
