package interfaces;

import views.View;

/**
 * IController interface defines the contract for controller classes in the
 * application. Controllers handle user interactions and business logic between
 * views and models.
 *
 */
public interface IController {

    /**
     * Starts the controller with the given view and exit character.
     *
     * @param view the view to be used by the controller
     * @param exitChar the character that triggers the exit of the controller
     */
    void start(View view, String exitChar);

    /**
     * Handles the user's menu choice.
     *
     * @param choice the menu option selected by the user
     */
    void handleMenuChoice(String choice);

    /**
     * Logs out the current user from the system. This method should be called
     * to terminate the user's session and perform any necessary cleanup.
     */
    void logout();
}
