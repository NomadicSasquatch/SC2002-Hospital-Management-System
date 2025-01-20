# Hospital Management System (HMS)

Welcome to the Hospital Management System (HMS) repository! This simulated system is a comprehensive Java-based application designed to streamline hospital operations. The HMS follows a modular design using an MVC architecture, ensuring clean separation of concerns and maintainability.

## Table of Contents
1. Key Features
2. Technologies Used
3. Prerequisites
4. Installation and Running
5. Usage Overview
6. Security Features
##
### 1. Key Features
The key features can be separated into user roles and their funtionalities:
- ### Admin
  - Manage hospital staff
  - Oversee appointments and inventory
  - Approve replenishment requests
- ### Doctor
  - Manage schedules and appointments
  - Access and update patient medical records
  - Write prescriptions
  - Create appointment outcome records for each appointment
- ### Patient
  - Schedule, reschedule, and cancel appointments
  - View personal medical records and prescriptions
  - Access appointment outcomes
- ### Pharmacist
  - Dispense prescriptions
  - Manage medication inventory
  - Submit replenishment requests

### 2. Technologies Used
- Java: Core programming language
- CSV Files: Data persistence for users, appointments, medical records, and inventory
- Object-Oriented Design: Ensures modularity and code reuse.

### 3. Prerequisites
- Java 8+
- A terminal or IDE to run the application.

### 4. Installation and Running
1. Clone the repository
```
git clone https://github.com/NomadicSasquatch/SC2002-Hospital-Management-System.git
cd SC2002-Hospital-Management-System
```
2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse)
3. Compile and run the Main.java file

### 5. Usage Overview
1. Sign in to a new account, depending on what role you would like the new account to have. Patient, Doctor, Pharmacist and Admin IDs must start with P, D, PH and A respectively, followed by 3 numbers
2. Reset your password to a desired password (will be prompted to do so for every new account)
3. Explore the different functions available to each role

### 6. Security Features
- Password Hashing: Passwords are stored securely using SHA-256 hashing (implemented in PasswordUtil.java)
- Role-Based Access Control (RBAC): Each role has access only to its designated functionalities
