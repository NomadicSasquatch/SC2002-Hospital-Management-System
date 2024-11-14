import java.time.LocalDate;


public class Doctor extends User {

    // Constructor
    public Doctor(String hospitalID, String name, UserRole role, String email, boolean gender, LocalDate dob, String password) {
        super(hospitalID, name, role, email, gender, dob, password);
        this.menu = new DoctorMenu();  // Set the Doctor's menu
        }



}
