package models;

import enums.UserRole;

/**
 * The User class represents a user in the system with various attributes such as userId, password, role, name, dateOfBirth, gender, and contactInfo.
 * It provides constructors to create a User object, methods to convert the User object to and from a CSV record, and getters and setters for its fields.
 * 
 * <p>Attributes:</p>
 * <ul>
 *   <li>userId - The unique identifier for the user.</li>
 *   <li>password - The password for the user.</li>
 *   <li>role - The role of the user in the system.</li>
 *   <li>name - The name of the user.</li>
 *   <li>dateOfBirth - The date of birth of the user.</li>
 *   <li>gender - The gender of the user.</li>
 *   <li>contactInfo - The contact information of the user.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #User()} - Default constructor.</li>
 *   <li>{@link #User(String, String, UserRole, String, String, String, String)} - Parameterized constructor.</li>
 *   <li>{@link #toCSV()} - Converts the user to a CSV record.</li>
 *   <li>{@link #fromCSV(String[])} - Creates a User object from a CSV record.</li>
 *   <li>{@link #getUserId()} - Gets the userId.</li>
 *   <li>{@link #getRole()} - Gets the role.</li>
 *   <li>{@link #setPassword(String)} - Sets the password.</li>
 *   <li>{@link #getPassword()} - Gets the password.</li>
 *   <li>{@link #getDateOfBirth()} - Gets the date of birth.</li>
 *   <li>{@link #getGender()} - Gets the gender.</li>
 *   <li>{@link #setContactInfo(String)} - Sets the contact information.</li>
 *   <li>{@link #getContactInfo()} - Gets the contact information.</li>
 *   <li>{@link #getName()} - Gets the name.</li>
 * </ul>
 */
public class User {
    private String userId;
    private String password;
    private UserRole role;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String contactInfo;

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
