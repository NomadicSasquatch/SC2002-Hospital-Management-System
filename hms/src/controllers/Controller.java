package controllers;

import java.util.Scanner;

import interfaces.IController;
import views.View;

/**
 * The abstract Controller class implements the IController interface and provides
 * a template for controllers in the application. It includes methods to start the
 * controller and handle user input.
 */
public abstract class Controller implements IController {
    private static Scanner scanner = new Scanner(System.in);
    /**
     * Starts the controller. This method should be implemented by subclasses
     * to define the specific behavior when the controller is started.
     */
    public abstract void start();
    /**
     * Starts the controller and displays the menu to the user.
     * The method will continue to display the menu and handle user input
     * until the user enters the specified exit character.
     *
     * @param view The view object responsible for displaying the menu.
     * @param exitChar The character that, when entered by the user, will exit the menu loop.
     */
    public void start(View view, String exitChar) {
        String choice;
        do {
            view.displayMenu();
            choice = scanner.nextLine();
            handleMenuChoice(choice);
        } while (!choice.equals(exitChar));
    }

    /**
     * Logs out the current user and prints a logout message to the console.
     */
    public void logout() {
        System.out.println("Logging out...");
    }

}
