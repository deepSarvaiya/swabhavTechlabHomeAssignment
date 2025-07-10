package com.tss.test;
import com.tss.model.Voter;

public class VoterTest {
	public static void main(String []args) {
		try
		{
			Voter voter = new Voter("deep",210,25);
			
			Voter voter2 = new Voter("deeppp",310,17);
			
		}
		catch(Exception exception)
		{
			System.out.println(exception);
		}
	}
}
