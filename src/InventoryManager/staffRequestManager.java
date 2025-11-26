package InventoryManager;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class staffRequestManager {

    private final LinkedList<Request> requests = new LinkedList<>();

    staffRequestManager() {
        loadFromFile();
    }

    public void submitRequest(Request request) {
        requests.add(request);
        System.out.println("Request sent!");
        saveToFile();
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
                    request.getRequestId(),
                    request.getItemName(),
                    request.getProposedPrice(),
                    request.getReason());
        }

    }

    public void pendingRequest() {
        if (requests.isEmpty()) {
            System.out.println("There are no Pending request Today");
        }
        System.out.println("========================================");
        System.out.printf("%-5s %-15s %-15s %-25s",
                "ID", "Item", "Proposed Price", "Reason");
        System.out.println("========================================");

        for (Request request : requests) {
            System.out.printf("%-5d %-15s %-15.2f %-25s",
                    request.getRequestId(),
                    request.getItemName(),
                    request.getProposedPrice(),
                    request.getReason());
        }
    }

    public void processRequest(Admin admin, InventoryManager managerInventory) {
        Scanner sc = new Scanner(System.in);
        for (Request req : requests) {

            System.out.println("Processing Request:");
            System.out.println("Request ID: " + req.getRequestId());
            System.out.println("Item: " + req.getItemName());
            System.out.println("Proposed Price: " + req.getProposedPrice());
            System.out.println("Reason: " + req.getReason());

            System.out.print("Approve or Reject? (Accepted/Rejected): ");
            String decision = sc.nextLine().trim();

            boolean approved = admin.reviewRequest(decision);

            if (approved) {
                managerInventory.updateItem(req.getItemId(), req.getProposedPrice());
                System.out.println("Item price updated successfully!");
            } else {
                System.out.println("Request rejected.");
            }
        }

        requests.clear();
        saveToFile();
    }

    public boolean updatedItemPrice(Request priceChange) {
        System.out.println("Price for: " + priceChange.getItemName() +
                " Updated Price " + priceChange.getProposedPrice());
        return true;
    }

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

        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter("NotepadDatabase\\request.txt"))) {
            for (Request requests : this.requests) {
                dataWriter.write(requests.toString());
                dataWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving request: " + e.getMessage());
        }

    }

    public void loadFromFile() {
        try (BufferedReader dataReader = new BufferedReader(new FileReader("NotepadDatabase\\request.txt"))) {
            String line;
            while ((line = dataReader.readLine()) != null) {
                Request request = Request.fromFile(line);
                requests.add(request);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error loading request: " + e.getMessage());
        } catch (IOException | NumberFormatException e) {
            System.out.println("error: " + e.getMessage());
        }

    }
}