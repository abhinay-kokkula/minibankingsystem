package myproject;
import java.sql.*;
import java.util.Scanner;
public class Withdraw {
	private Connection connection;
	private Scanner s;
//	private int balance = 0;
	public Withdraw(Connection connection, Scanner s) {
		this.connection = connection;
		this.s = s;
	}
	public void show_balance() {
		String query  = "select amount from balance";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			System.out.println("Current Balance is: ");
			if(rs.next()) {
				int amount = rs.getInt("amount");
				System.out.println("$"+amount);
			}else {
				System.out.println("invalid");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	public void withdraw_amount(int value) {
		try {
			String balancequery = "select amount from balance";
			PreparedStatement bps = connection.prepareStatement(balancequery);
			ResultSet brs = bps.executeQuery();
			if(!brs.next()) {
				System.out.println("Nothing found");
			}
			int amount = brs.getInt("amount");
			if(value > amount) {
				System.out.println("Sorry you have Low Balance :(");
				return;
			}
			int set = amount - value;
			String query = "update balance set amount = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, set);
			
			int effected = ps.executeUpdate();
			if(effected >0) {
				System.out.println("Withdrawn Successfully");
				System.out.print("Your Current Balance is:"+set);
			}
			else {
				System.out.println("Something went wrong");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void deposit_amount(int value) {
		try {
			String bquery = "select amount from balance";
			PreparedStatement bps = connection.prepareStatement(bquery);
			ResultSet rs = bps.executeQuery();
			if(!rs.next()) {
				System.out.println("Something went Wrong");
			}
			int amount = rs.getInt("amount");
			
			
			amount +=value;
			String query = "update balance set amount = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, amount);
			
			int effected = ps.executeUpdate();
			if(effected >0) {
				System.out.println("Successfully Deposited amount "+value);
				System.out.println("Your current Balance is:"+amount);
			}else {
				System.out.println("Something went wrong");
			}
		}catch(SQLException e ) {
			e.printStackTrace();
		}
	}
}
