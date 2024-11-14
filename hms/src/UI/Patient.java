import java.time.LocalDate;

public class Patient extends User {

    // Constructor with full parameters
    public Patient(String hospitalID, String name, UserRole role, String email, boolean gender, LocalDate dob, String password) {
        super(hospitalID, name, role, email, gender, dob, password);
        this.menu = new PatientMenu();  // Associate Patient with PatientMenu
    }


  
}
