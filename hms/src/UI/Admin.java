import java.time.LocalDate;

public class Admin extends User {

    // Constructor with full parameters
    public Admin(String hospitalID, String name, UserRole role, String email, boolean gender, LocalDate dob, String password) {
        super(hospitalID, name, role, email, gender, dob, password);
        this.menu = new AdminMenu();  
    }


  
}
