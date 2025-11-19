import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class staffRequestManager {

    private final LinkedList<Request> requests = new LinkedList<>();

    staffRequestManager() {
        loadFromFile();
    }

    public void submitRequest(Request request) {
        // Price change
        requests.add(request);
        saveToFile();
        System.out.println("Request sent!");
    }

    public void viewRequest() {

        if (requests.isEmpty()) {
            System.out.println("There are no request today");
        }
        System.out.println("========================================");
        System.out.printf("%-5s %-10s  %-15s %-20s%n",
                "ID", "Item", "Proposed Price", "Reason");
        System.out.println("========================================");

        for (Request request : requests) {
            System.out.printf("%-5d %-9s %-15.2f %-20s%n",
                    request.getId(),
                    request.getItemName(),
                    request.getProposedPrice(),
                    request.getReason());
        }

    }

    public void processRequest(Admin admin, InventoryManager managerInventory) {
        Scanner sc = new Scanner(System.in);
        for (Request priceChange : requests) {
            System.out.println("Processing request ID " + priceChange.getId());
            System.out.println(priceChange);


            //For admin
            System.out.println("Approve or decline this request? (Accepted or Rejected)");
            String decision = sc.nextLine().trim();

            boolean ifApproved = admin.reviewRequest(decision);
            if (ifApproved) {
                System.out.println("Ticket Approved! " + priceChange.getId() + " " + decision);
            } else {
                System.out.println("Ticket not Approved! " + priceChange.getId() + " " + decision);
            }
            managerInventory.updateItem(priceChange.getId(), priceChange.getProposedPrice());
            saveToFile();

        }
        requests.clear();
    }

    public boolean updatedItemPrice(Request priceChange) {
        System.out.println("Price for: " + priceChange.getItemName() +
                " Updated Price " + priceChange.getProposedPrice());
        return true;
    }

    // write files here
    public void saveToFile() {

        String filePath = "NotepadDatabase\\request.txt";
        File file = new File(filePath);

        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            if (!parentDirectory.mkdir()) {
                System.err.println("Error creating Directory: " + parentDirectory.getAbsolutePath());
                return;
            }
        }

        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter("NotepadData\\request.txt"))) {
            for (Request requests : this.requests) {
                dataWriter.write(requests.toString());
                dataWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving request: " + e.getMessage());
        }

    }

    // load files here
    public void loadFromFile() {
        try (BufferedReader dataReader = new BufferedReader(new FileReader("NotepadDatabase\\request.txt"))) {
            String line;
            while ((line = dataReader.readLine()) != null) {

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading requests: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
