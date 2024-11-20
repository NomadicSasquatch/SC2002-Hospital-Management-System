package controller;

import java.util.Scanner;
import interfaces.IController;
import views.View;

public abstract class Controller implements IController {
    private static Scanner scanner = new Scanner(System.in);
    public abstract void start();
    public void start(View view, String exitChar) {
        String choice;
        do {
            view.displayMenu();
            choice = scanner.nextLine();
            handleMenuChoice(choice);
        } while (!choice.equals(exitChar));
    }

    public void logout() {
        System.out.println("Logging out...");
    }

}
