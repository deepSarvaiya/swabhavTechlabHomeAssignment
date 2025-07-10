
package com.tss.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.model.DiwaliFestival;
import com.tss.model.FDCalculator;
import com.tss.model.FDPrint;
import com.tss.model.FixedDepositFestival;
import com.tss.model.HoliFestival;
import com.tss.model.IFestival;
import com.tss.model.NewYearFestival;
import com.tss.model.OtherFestival;

public class FDFestivalTest {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<FixedDepositFestival> fdList = new ArrayList<>();
        FDCalculator calculator = new FDCalculator();
        FDPrint printer = new FDPrint(calculator);

        System.out.print("How many FDs: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.println("\nFD #" + (i + 1));
            System.out.print("Account Number: ");
            int acc = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Principal: ");
            double principal = scanner.nextDouble();

            System.out.print("Duration: ");
            int duration = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Festival? 1.NewYear 2.Diwali 3.Holi 4.Others");
            int choice = scanner.nextInt();
            IFestival festival;
            switch (choice) {
                case 1: festival = (IFestival) new NewYearFestival(); break;
                case 2: festival = new DiwaliFestival(); break;
                case 3: festival = (IFestival) new HoliFestival(); break;
                default: festival = (IFestival) new OtherFestival(); break;
            }

            fdList.add(new FixedDepositFestival(acc, name, principal, duration, festival));
        }

        printer.printAll(fdList);

        scanner.close();
    }
}