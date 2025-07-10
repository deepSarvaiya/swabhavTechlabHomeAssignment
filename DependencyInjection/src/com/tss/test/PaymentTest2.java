package com.tss.test;

import com.tss.model.CreditCard2;
import com.tss.model.IPayment;
import com.tss.model.ShoppingCart2;
import com.tss.model.UPIPayment;

public class PaymentTest2 {

	public static void main(String[] args) {
		IPayment payCC = new CreditCard2();
		IPayment payUPI = new UPIPayment();

		ShoppingCart2 cart = new ShoppingCart2(payUPI);
		cart.checkout(400);
		
		ShoppingCart2 cart2 = new ShoppingCart2(payCC);
		cart2.checkout(500);
	}

}
