import java.util.ArrayList;

public class Admin {
    public boolean reviewRequest(String decision) {
        if ("Accepted".equalsIgnoreCase(decision)) {
            System.out.println("Ticket accepted");
            return true;
        } else if ("Rejected".equalsIgnoreCase(decision)) {
            System.out.println("Ticket Rejected.");
            return false;
        } else {
            System.out.println("Invalid decision. ACCEPTED OR REJECTED decision only Accepted...");
            return false;
        }
    }
}
