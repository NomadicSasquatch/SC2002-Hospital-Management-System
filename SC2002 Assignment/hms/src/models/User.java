package models;

import enums.UserRole;

public class User {
    private String userId;  // e.g., "U001"
    private String password; // Ensure passwords are stored securely (hashed)
    private UserRole role;     // e.g., "Patient", "Doctor", "Pharmacist", "Administrator"
    private String name;
    private String dateOfBirth; // Consider using LocalDate
    private String gender;
    private String contactInfo; // e.g., "555-1234;john.doe@example.com"

    // Constructors, getters, and setters

    public User() {
    }

    public User(String userId, String password, UserRole role,
                String name, String dateOfBirth, String gender, String contactInfo) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters for all fields...

    /**
     * Converts the user to a CSV record.
     *
     * @return An array of strings representing the CSV record.
     */
    public String[] toCSV() {
        return new String[]{
                userId,
                password,
                role.toString(),
                name,
                dateOfBirth,
                gender,
                contactInfo
        };
    }

    /**
     * Creates a User object from a CSV record.
     *
     * @param record An array of strings representing the CSV record.
     * @return A User object.
     */
    public static User fromCSV(String[] record) {
        return new User(
                record[0],
                record[1],
                UserRole.valueOf(record[2].toUpperCase()),
                record[3],
                record[4],
                record[5],
                record[6]
        );
    }

    
    /** 
     * @return String
     */
    public String getUserId() {
        return userId;
    }


    public UserRole getRole() {
        return role;
    }

    public void setPassword(String hashedNewPassword) {
        password = hashedNewPassword;
    }

    public Object getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setContactInfo(String contactInfo2) {
        contactInfo = contactInfo2;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getName() {
        return name;
    }
}
