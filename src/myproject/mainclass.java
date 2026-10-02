package myproject;

import java.sql.*;
import java.util.Scanner;

public class mainclass {
	private static final String url = "jdbc:mysql://localhost:3306/atm";
	private static final String username = "root";
	private static final String password= "@Abhinay789";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// this is used to connect jdbc to the mysql database
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		Scanner s = new Scanner(System.in);
		try {
			Connection connection = DriverManager.getConnection(url,username,password);
			Withdraw withdraw = new Withdraw(connection,s);
			verifydetails vd = new verifydetails(connection);
			System.out.println("Enter you username:");
			String namekey = s.nextLine();
			System.out.println("Enter your Password");
			int passkey = s.nextInt();
			boolean valid = vd.userdetails(namekey, passkey);
			if(valid) {
			while(true) {
				System.out.println("Hello there Welcome to Mini atm");
				System.out.println("1.View Balance");
				System.out.println("2.Withdraw Amount");
				System.out.println("3.Deposit Amount");
				System.out.println("4.Exit");
				System.out.println("Please enter your choice!");
				int choice = s.nextInt();
				s.nextLine();
				switch(choice) {
				case 1:
					withdraw.show_balance();
					break;
				case 2:
					System.out.println("Enter Amount to withdraw");
					int value = s.nextInt();
					withdraw.withdraw_amount(value);
					System.out.println();
					break;
				case 3:
					System.out.println("Enter Amount to Deposit");
					int val = s.nextInt();
					withdraw.deposit_amount(val);
					System.out.println();
					break;
				case 4:
					System.out.println("Thank you for Visiting :)");
					return;
				default:
					System.out.println("Please enter valid CHOICE");
					break;
				}
			}
			}
			else {
				System.out.println("Oops you Don't have an account,want to create it!");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		s.close();
	}

}
