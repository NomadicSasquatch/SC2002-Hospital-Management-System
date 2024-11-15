package com.hms;

import com.enumclass.UserRole;
import com.utils.CSVFile;

/**
 * Hello world!
 */
public final class App {

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
<<<<<<< HEAD
        // LocalDate date = LocalDate.of(2000, 3, 20);
        // System.out.println(date); // test output

        CSVFile testinput = new CSVFile("hms/src/main/java/com/data/test_csv.csv");

        System.out.printf("%s\n",(testinput.getAllRecords())); // test output
        testinput.updateCSVFile();

        UserRole test = UserRole.ADMIN;
        System.out.printf("%s, %d", test, test.ordinal()); // test output
=======
        LocalDate date = LocalDate.of(2000, 3, 20);
        System.out.println(date); // test output

        LoginMenu loginMenu = new LoginMenu();
        
        // Start the login process
        loginMenu.displayMenu();
>>>>>>> ab6d56e02734fa3160e3c02ae67e3e661a0c1c1f
    }
}
