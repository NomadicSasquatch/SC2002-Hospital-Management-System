package com.hms;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Arrays;

import com.enumclass.UserRole;

/**
 * The Users class represents an abstract user in the hospital management
 * system. It contains common attributes and methods shared by all user types.
 *
 * @param email          the email address of the user
 * @param name           the name of the user
 * @param hashedpassword the hashed password of the user
 * @param userid         the unique user ID of the user
 * @param dob            the date of birth of the user
 * @param role           the role of the user in the system
 * @param gender         the gender of the user
 *
 */

public class Users {

    private String email, name, hashedpassword, userid;
    private LocalDate dob;
    private UserRole role;
    private boolean gender;

    public Users(String userid, String email, String name, String hashedpassword, LocalDate dob, UserRole role,
            boolean gender) {
        this.email = email;
        this.name = name;
        this.hashedpassword = hashedpassword;
        this.userid = userid;
        this.dob = dob;
        this.role = role;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the new email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Hashes the given password using SHA-256.
     *
     * @param password the password to hash
     * @return the hashed password as a string
     * @implSpec Do not store unhashed password in plaintext
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return toHexString(md.digest(password.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
    }

    /**
     * Gets the hashed password of the user.
     *
     * @return the hashed password of the user
     */
    public String gethashedPassword() {
        return hashedpassword;
    }

    /**
     * Sets the password of the user by hashing it.
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.hashedpassword = hashPassword(password);
    }

    /**
     * Gets the unique user ID of the user.
     *
     * @return the unique user ID of the user
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Sets the unique user ID of the user.
     *
     * @param userid the new unique user ID of the user
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * Gets the date of birth of the user.
     *
     * @return the date of birth of the user
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Sets the date of birth of the user.
     *
     * @param dob the new date of birth of the user
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Gets the age of the user.
     *
     * @return the age of the user
     */
    public int getAge() {
        return LocalDate.now().getYear() - dob.getYear();
    }

    /**
     * Gets the role of the user in the system.
     *
     * @return the role of the user
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets the role of the user in the system.
     *
     * @param role the new role of the user
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Gets the gender of the user.
     *
     * @return the gender of the user
     */
    public boolean getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender the new gender of the user
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
