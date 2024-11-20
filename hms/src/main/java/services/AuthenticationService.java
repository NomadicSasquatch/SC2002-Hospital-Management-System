package services;

import models.User;
import repositories.UserRepository;
import utils.PasswordUtil;

import java.util.Collections;
import java.util.List;
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
public List<User> authenticate(String userId, String password) {
    List<User> users = userRepository.getDataById(userId);

    if (users.isEmpty()) {
        System.out.println("User not found.");
        return Collections.emptyList(); // Return an empty list if no matching user is found
    }

    // Assuming we handle the first matching user
    User user = users.get(0);

    // Compare hashed passwords
    String hashedPassword = PasswordUtil.hashPassword(password);
    if (hashedPassword.equals(user.getPassword())) {
        System.out.println("Authentication successful.");
        return List.of(user); // Return a single-element list with the authenticated user
    } else {
        System.out.println("Invalid password.");
        return Collections.emptyList(); // Return an empty list if authentication fails
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
    
        // Verify old password
        String hashedOldPassword = PasswordUtil.hashPassword(oldPassword);
        if (!hashedOldPassword.equals(user.getPassword())) {
            System.out.println("Current password is incorrect.");
            return false;
        }
    
        // Update password
        String hashedNewPassword = PasswordUtil.hashPassword(newPassword);
        user.setPassword(hashedNewPassword);
        userRepository.updateUser(user);
    
        System.out.println("Password changed successfully.");
        return true;
    }
    
}
