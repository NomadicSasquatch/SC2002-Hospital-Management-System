package services;

import models.User;
import repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserService handles business logic related to user management.
 */
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adds a new user to the system.
     *
     * @param user The user to add.
     * @return True if the user was added successfully.
     */
    public boolean addUser(User user) {
        List<User> existingUser = userRepository.getDataById(user.getUserId());
        if (!existingUser.isEmpty()) {
            System.out.println("User with the same ID already exists.");
            return false;
        }
        userRepository.addItem(user);
        System.out.println("User added successfully.");
        return true;
    }

    /**
     * Updates an existing user.
     *
     * @param user The user with updated information.
     * @return True if the user was updated successfully.
     */
    public boolean updateUser(User user) {
        List<User> existingUser = userRepository.getDataById(user.getUserId());
        if (existingUser.isEmpty()) {
            System.out.println("User not found.");
            return false;
        }
        userRepository.updateUser(user);
        System.out.println("User updated successfully.");
        return true;
    }

    /**
     * Removes a user by their ID.
     *
     * @param userId The ID of the user to remove.
     * @return True if the user was removed successfully.
     */
    public boolean removeUser(String userId) {
        List<User> existingUser = userRepository.getDataById(userId);
        if (existingUser.isEmpty()) {
            System.out.println("User not found.");
            return false;
        }
        userRepository.removeItem(userId);
        System.out.println("User removed successfully.");
        return true;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The user's ID.
     * @return An Optional containing the user if found.
     */
    public User getUserById(String userId) {
        List<User> users = userRepository.getDataById(userId);
    
        if (users.isEmpty()) {
            return null; // Return null if no matching user is found
        }
    
        return users.get(0); // Return the first matching user
    }
    

    /**
     * Retrieves users filtered by role.
     *
     * @param role The role to filter by.
     * @return List of users with the specified role.
     */
    public List<User> getUsersByRole(String role) {
        return userRepository.getUsersByRole(role);
    }

    /**
     * Retrieves users filtered by gender.
     *
     * @param gender The gender to filter by.
     * @return List of users with the specified gender.
     */
    public List<User> getUsersByGender(String gender) {
        return userRepository.getAllData().stream()
                .filter(u -> u.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves users filtered by age range.
     *
     * @param minAge The minimum age.
     * @param maxAge The maximum age.
     * @return List of users within the specified age range.
     */
    public List<User> getUsersByAgeRange(int minAge, int maxAge) {
        return userRepository.getAllData().stream()
                .filter(u -> {
                    int age = calculateAge(u.getDateOfBirth());
                    return age >= minAge && age <= maxAge;
                })
                .collect(Collectors.toList());
    }

    /**
     * Calculates age based on date of birth.
     *
     * @param dateOfBirth The user's date of birth (format: "yyyy-MM-dd").
     * @return The calculated age.
     */
    private int calculateAge(String dateOfBirth) {
        // Implement age calculation using LocalDate
        java.time.LocalDate dob = java.time.LocalDate.parse(dateOfBirth);
        java.time.LocalDate currentDate = java.time.LocalDate.now();
        return java.time.Period.between(dob, currentDate).getYears();
    }

    /**
     * Retrieves all users.
     *
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.getAllData();
    }

    /**
     * Allows a user to update their personal contact information.
     *
     * @param userId      The user's ID.
     * @param contactInfo The new contact information.
     * @return True if the update was successful.
     */
    public boolean updateContactInfo(String userId, String contactInfo) {
        List<User> users = userRepository.getDataById(userId);
    
        if (users.isEmpty()) {
            System.out.println("User not found.");
            return false;
        }
    
        // Assuming we handle the first matching user
        User user = users.get(0);
        user.setContactInfo(contactInfo);
        userRepository.updateUser(user);
        System.out.println("Contact information updated successfully.");
        return true;
    }
    

    public List<User> getStaffByRole(String role) {
        if (role.equalsIgnoreCase("All")) {
            return userRepository.getAllData();
        }
        return userRepository.getUsersByRole(role);
    }


    public String generateUserId(String role) {
        return role + String.format("%03d",(userRepository.getAllData().size() + 1));
    }
}
