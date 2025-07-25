package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admins {
	private Connection connection;

	private Scanner scanner;

	public Admins(Connection connection, Scanner scanner){
		this.connection = connection;
		this.scanner = scanner;
	}

	public static boolean authnticateAdmins(Connection connection, Scanner scanner){
		System.out.println("Enter admin username: ");
		String username = scanner.next();

		System.out.println("Enter password: ");
		String password = scanner.next();

		String query = "Select * from admins WHERE username = ? AND password = ?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				System.out.println("Login Successful!");
				return true;
			}else{
				System.out.println("Invalid credentials, Access Denied!");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
