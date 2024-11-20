package services;

import models.User;
import repositories.UserRepository;
import utils.PasswordUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * AuthenticationService handles user authentication and authorization.
 */

 
public class AuthenticationService {

    
    
    private UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates a user based on user ID and password.
     *
     * @param userId   The user's ID.
     * @param password The user's password.
     * @return An Optional containing the authenticated user if successful.
     */
    private static final String DEFAULT_PASSWORD_HASH = PasswordUtil.hashPassword("12345");

    /**
     * Checks if a user is logging in with the default password.
     *
     * @param user The user to check.
     * @return True if the user is using the default password, false otherwise.
     */
    public boolean isUsingDefaultPassword(User user) {
        return DEFAULT_PASSWORD_HASH.equals(user.getPassword());
    }

    // Modify authenticate method
    public List<User> authenticate(String userId, String password) {
        List<User> users = userRepository.getDataById(userId);

        if (users.isEmpty()) {
            System.out.println("User not found.");
            return Collections.emptyList();
        }

        User user = users.get(0);
        String hashedPassword = PasswordUtil.hashPassword(password);

        if (hashedPassword.equals(user.getPassword())) {
            System.out.println("Authentication successful.");
            return List.of(user);
        } else {
            System.out.println("Invalid password.");
            return Collections.emptyList();
        }
    }

    /**
     * Changes the password for a user.
     *
     * @param userId      The user's ID.
     * @param oldPassword The user's current password.
     * @param newPassword The new password.
     * @return True if the password was changed successfully, false otherwise.
     */
    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        List<User> users = userRepository.getDataById(userId);
    
        if (users.isEmpty()) {
            System.out.println("User not found.");
            return false;
        }
    
        // Assuming we handle the first matching user
        User user = users.get(0);


    // Hash and update the new password
    String hashedNewPassword = PasswordUtil.hashPassword(newPassword);
    user.setPassword(hashedNewPassword);
    userRepository.updateUser(user);

    System.out.println("Password changed successfully.");
    return true;
    }
}
