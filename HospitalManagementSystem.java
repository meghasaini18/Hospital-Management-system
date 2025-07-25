package HospitalManagementSystem;

import java.sql.*;
import java.util.Collection;
import java.util.Scanner;

public class HospitalManagementSystem {
	private static final String url = "jdbc:mysql://localhost:3306/hospital";

	private static final String username = "root";

	private static final String password = ".#@meghafor17";

	public static void main(String[] args) throws SQLException {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			Scanner scanner = new Scanner(System.in);

			Admins admin = new Admins(connection, scanner);

			if(!admin.authnticateAdmins(connection, scanner)){
				return;
			}

			Patient patient = new Patient(connection, scanner);
			Doctor doctor = new Doctor(connection);
			while(true){
				System.out.println("HOSPITAL MANAGEMENT SYSTEM");
				System.out.println("1. Add Patient");
				System.out.println("2. View Patient");
				System.out.println("3. View Doctors");
				System.out.println("4. Make Appointment");
				System.out.println("5. Exit");
				System.out.println("Enter your choice: ");
				int choice = scanner.nextInt();

				switch(choice){
					case 1:
						//Add patient
						patient.addPatient();
						System.out.println();
						break;
					case 2:
						//view patient
						patient.viewPatient();
						System.out.println();
						break;
					case 3:
						//view doctors
						doctor.viewDoctor();
						System.out.println();
						break;
					case 4:
						//make appointment
						makeAppointment(patient, doctor, connection, scanner);
						System.out.println();
						break;
					case 5:
						System.out.println("Thank You!");
						return;
					default:
						System.out.print("Enter valid choice!!");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void makeAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner){
		System.out.println("Enter Patient Id: ");
		int patientId = scanner.nextInt();

		System.out.println("Enter Doctor Id: ");
		int doctorId = scanner.nextInt();

		System.out.println("Enter appointment date (YYYY-MM-DD): ");
		String appointmentDate = scanner.next();

		if(!patient.getPatientById(patientId) || !doctor.getDoctorById(doctorId)) {
			System.out.println("Either patient or doctor doesn't exist");
			return;
		}
		if(!checkDoctorAvailability(doctorId, appointmentDate,  connection)) {
			System.out.println("Doctor is not available on this date");
			return;
		}
		String appointmentQuery = "Insert into appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
		        try{
					PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
					preparedStatement.setInt(1, patientId);
					preparedStatement.setInt(2, doctorId);
					preparedStatement.setString(3, appointmentDate);
					int rows = preparedStatement.executeUpdate();
					if(rows > 0){
						System.out.println("Appointment Booked!");
					}else{
						System.out.println("Failed to book appointment!");
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
	}

	private static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
		String query = "Select count(*) from appointments where doctor_Id = ? AND appointment_date = ?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, doctorId);
			preparedStatement.setString(2, appointmentDate);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				int count = resultSet.getInt(1);
				if(count == 0){
					return true;
				}else{
					return false;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

}
