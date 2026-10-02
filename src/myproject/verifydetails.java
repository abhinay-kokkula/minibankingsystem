package myproject;

import java.sql.*;

public class verifydetails {
Connection connection;
public verifydetails(Connection connection) {
	this.connection = connection;
}

public boolean userdetails(String namekey , int passkey) {
	String query = "select username,password from userdetails where username = ? AND password = ?";
	try {	
	PreparedStatement ps = connection.prepareStatement(query);
	ps.setString(1, namekey);
	ps.setInt(2,passkey);
	ResultSet rs = ps.executeQuery();
	if(!rs.next()) {
		return false;
	}
	else {
		return true;
	}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return false;
}
}
