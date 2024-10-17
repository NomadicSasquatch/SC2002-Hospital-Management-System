/**
 * The {@code People} class represents a person with a name, user ID, gender, and age.
 * It provides methods to access and modify these attributes.
 * 
 * <p>This class is part of the com.hms package.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 *     People person = new People("John Doe", "JD123", true, 30);
 *     System.out.println(person.getName()); // Outputs: John Doe
 * </pre>
 * 
 */
package com.hms;
public class People {

    /**
     * The name of the person.
     */
    private String name;

    /**
     * The user ID of the person.
     */
    private String userID;

    /**
     * The gender of the person. {@code true} for male, {@code false} for female.
     */
    private boolean gender;

    /**
     * The age of the person.
     */
    private int age;

    /**
     * Constructs a new {@code People} object with the specified name, user ID, gender, and age.
     * 
     * @param name the name of the person
     * @param userID the user ID of the person
     * @param gender the gender of the person, {@code true} for male, {@code false} for female
     * @param age the age of the person
     */
    public People(String name, String userID, boolean gender, int age) {
        this.name = name;
        this.userID = userID;
        this.gender = gender;
        this.age = age;
    }

    /**
     * Returns the age of the person.
     * 
     * @return the age of the person
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets the age of the person.
     * 
     * @param age the new age of the person
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the name of the person.
     * 
     * @return the name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the person.
     * 
     * @param name the new name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user ID of the person.
     * 
     * @return the user ID of the person
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Sets the user ID of the person.
     * 
     * @param userID the new user ID of the person
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Returns the gender of the person.
     * 
     * @return {@code true} if the person is male, {@code false} if the person is female
     */
    public boolean getGender() {
        return this.gender;
    }

    /**
     * Sets the gender of the person.
     * 
     * @param gender the new gender of the person, {@code true} for male, {@code false} for female
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
