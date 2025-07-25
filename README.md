# 🏥 Hospital-Management-system

A simple **Hospital Management System** built using **Core Java** and **MySQL** that allows administrators to manage patients, doctors, and appointments through a command-line interface.

---

## 📌 Features

- 👨‍⚕️ Add and view patient details  
- 🩺 View doctor information  
- 📅 Book appointments between patients and doctors  
- 🔐 Admin login authentication  

---

## 🛠️ Tech Stack

| Technology | Purpose                        |
|------------|--------------------------------|
| Java       | Core programming language      |
| JDBC       | Database connectivity          |
| MySQL      | Backend database               |
| CLI        | Console-based user interface   |

---

## 💾 Database Setup

1. **Create the database:**
   ```sql
   CREATE DATABASE hospital;
   USE hospital;
   

2. **Create necessary tables:**
   ```
   CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(10) NOT NULL
   );

    CREATE TABLE patients (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255),
        age INT,
        gender VARCHAR(255)
    );

    CREATE TABLE doctor (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255),
        specialization VARCHAR(255)
    );

    CREATE TABLE appointments (
        id INT AUTO_INCREMENT PRIMARY KEY,
        patient_id INT,
        doctor_id INT,
        appointment_date DATE,
        FOREIGN KEY (patient_id) REFERENCES patients(id),
        FOREIGN KEY (doctor_id) REFERENCES doctors(id)
    );

   
3. **Insert sample admin (for login):**
   ```
    INSERT INTO admins (username, password) VALUES ('admin', 'admin123');


##🚀 How to Run

1. Clone the repository
   ```
   git clone https://github.com/your-username/Hospital-Management-System-Java.git
   cd Hospital-Management-System-Java

2. Compile the project
   Ensure you have the MySQL JDBC driver (mysql-connector-java) added to your classpath.
   ```
   javac -cp ".;path/to/mysql-connector-java.jar" HospitalManagementSystem/*.java


3. Run the application
   ```
   java -cp ".;path/to/mysql-connector-java.jar" HospitalManagementSystem.HospitalManagementSystem


##🔐 Admin Login
Upon launching, the program will ask for admin login:

Username: admin

Password: admin123

##🧑‍💻 Author
Megha Saini
Feel free to contribute or suggest improvements!

