import java.text.NumberFormat;
import java.util.Locale;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class InventoryManager {
    private final HashMap<Integer, Items> inventory = new HashMap<>();
    private final Scanner sc = new Scanner(System.in);
    private final String FILE_NAME = "items.txt";

    InventoryManager() {
        loadFromFile();
    }

    public void AddItems() {
        System.out.print("Enter the item name: ");
        String itemName = sc.nextLine();

        // Use nextDouble() for prices if Items class supports double, or stick to int
        System.out.print("Enter Price: ");
        int itemPrice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Item Quantity: ");
        int itemQuantity = sc.nextInt();
        sc.nextLine();

        Items items = new Items(itemName, itemPrice, itemQuantity);
        inventory.put(items.getItemId(), items);
        SaveToFile(); // Added SaveToFile
        System.out.println("Item added Successfully: " + items);
    }

    public void DisplayItems() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty");
            return;
        }
        for (Items items : inventory.values()) {
            System.out.println(items);
        }

        Locale localCurrency = new Locale.Builder()
                .setLanguage("en")
                .setRegion("PH")
                .build();
        NumberFormat pesoSignFormatter = NumberFormat.getCurrencyInstance(localCurrency);

        double totalSumOfAllItemPrices = 0.0;

        for (Items item : inventory.values()) {
            // Assuming getItemPrice returns a number type compatible with double
            totalSumOfAllItemPrices += item.getItemPrice() * item.getItemQuantity();
        }

        String formattedCurrency = pesoSignFormatter.format(totalSumOfAllItemPrices);
        System.err.println("The total Prices of all items were in stock are: " + formattedCurrency);
    }

    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    public void RemoveItems(int id) {
        Items itemToDelete = inventory.remove(id);

        if (itemToDelete != null) {
            System.out.println("Item removed Successfully: " + itemToDelete.getItemName());
            SaveToFile(); // FIX: Added SaveToFile
        } else {
            System.out.println("Item removal failed: ID " + id + " not found.");
        }
    }

    public void SearchElementById(int id) {
        // FIX: Check for null to avoid NullPointerException
        Items item = inventory.get(id);

        if (item != null) {
            System.out.println("ID Found: " + item.getItemName() + " - " + item);
        } else {
            System.out.println("ID not found: " + id);
        }
    }

    public void SearchElementByKeyword(String keyword) {
        boolean found = false;
        for (Items item : inventory.values()) {
            if (item.getItemName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("FOUND! " + item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("NOT FOUND! Keyword: " + keyword);
        }
    }

    public void updateItem(int id, double newPrice) {
        if (inventory.containsKey(id)) {
            Items items = inventory.get(id);
            // Assuming Items.setItemPrice accepts a double
            items.setItemPrice(newPrice);
            SaveToFile(); // FIX: Added SaveToFile
            System.out.println("Updated Successfully for ID: " + id);
        } else {
            System.out.println("Update not Success: ID " + id + " not found.");
        }
    }

    // WRITE FILES
    public void SaveToFile() {
        // FIX: Define filePath using FILE_NAME
        String filePath = "NotepadDatabase\\" + FILE_NAME;
        File file = new File(filePath);

        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            if (!parentDirectory.mkdir()) {
                System.err.println("Error creating Directory: " + parentDirectory.getAbsolutePath());
                return;
            }
        }

        // FIX: Write to the correct file path (using 'file' object)
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(file))) {
            for (Items item : inventory.values()) {
                dataWriter.write(item.toString());
                dataWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory items: " + e.getMessage());
        }
    }

    // LOAD FILES
    public void loadFromFile() {
        // FIX: Read from the correct file path
        try (BufferedReader dataReader = new BufferedReader(new FileReader("NotepadDatabase\\" + FILE_NAME))) {
            String line;
            while ((line = dataReader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines
                try {
                    Items item = Items.fromFile(line); // Requires Items.fromFile(String) to be correct
                    inventory.put(item.getItemId(), item);
                } catch (Exception e) {
                    System.err.println("Skipping invalid item line during load: " + line + " Error: " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error loading items: File not found. Starting with empty inventory.");
        } catch (IOException e) {
            System.out.println("Error reading file during load: " + e.getMessage());
        }
    }
}