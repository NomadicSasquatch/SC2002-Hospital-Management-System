package repositories;

import models.ReplenishmentRequest;
import models.User;
import utils.CSVReader;
import utils.CSVWriter;

import java.util.ArrayList;
import java.util.List;

import abstract_class.CrudRepository;
import enums.UserRole;

/**
 * UserRepository handles CRUD operations for User entities.
 */
public class UserRepository extends CrudRepository<User, String> {

    private static final String USERS_CSV_FILE = "hms/src/data/users.csv";

    public UserRepository() {
        super(USERS_CSV_FILE);
    }

    /**
     * Updates an existing user.
     *
     * @param user The user with updated information.
     */
    public void updateUser(User user) {
        List<User> existingUsers = getDataById(user.getUserId());
    
        if (!existingUsers.isEmpty()) {
            // Assuming we handle the first matching user
            User existingUser = existingUsers.get(0);
            removeItem(existingUser.getUserId());
            addItem(user);
            saveRepository();
        }
    }

    /**
     * Retrieves users by their role.
     *
     * @param role The role of the users to retrieve.
     * @return List of users with the specified role.
     */
    public List<User> getUsersByRole(UserRole  role) {
        List<User> roleUsers = new ArrayList<>();
        for (User user : items) {
            if (user.getRole().equals(role)) {
                roleUsers.add(user);
            }
        }
        return roleUsers;
    }
    @Override
    protected User fromCSV(String[] record) {
        return User.fromCSV(record);
    }
    @Override
    protected String[] toCSV(User item) {
        return item.toCSV();
    }
    @Override
    protected String getId(User item) {
        return item.getUserId();
    }
}
