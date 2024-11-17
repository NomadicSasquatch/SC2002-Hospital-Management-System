package com.hms;

import java.time.LocalDate;
import java.util.Arrays;

import com.enumclass.UserRole;
import com.hms.Manager.AdminManager;
import com.hms.Manager.FileManager;
import com.hms.Viewer.LoginMenu;
import com.hms.Manager.UserManager;
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
        // LocalDate date = LocalDate.of(2000, 3, 20);
        // System.out.println(date); // test output

        // CSVFile testinput = new CSVFile("hms/src/main/java/com/data/test_csv.csv");

        // System.out.printf("%s\n",(testinput.getAllRecords())); // test output
        // testinput.updateCSVFile();

        // UserRole test = UserRole.ADMIN;
        // System.out.printf("%s, %d", test, test.ordinal()); // test output

        // LocalDate date = LocalDate.of(2000, 3, 20);
        // System.out.println(date); // test output

        
        
        // Start the login process
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.showMenu();
        //System.out.println(Users.hashPassword("null"));
        // Users user = new Users("A003", "email", "name", Users.hashPassword("null"), LocalDate.parse("2000-10-10"), UserRole.DOCTOR, true);
        // UserManager userManager = new AdminManager();
        // userManager.add(user);
        // final String ADMIN_ROOT = "hms/src/main/java/com/data/admin";
        // CSVFile adminCSV = FileManager.loadFile(ADMIN_ROOT + "/admin.csv", Arrays.asList("userid", "email", "name", "hashedpassword", "dob", "role", "gender"));
        // adminCSV.add(Arrays.asList("userid2", "email", "name", "password", "2000-10-10", "DOCTOR", "true"));
        // adminCSV.updateCSVFile();



    }
    //randomtest
}
