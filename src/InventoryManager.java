import org.w3c.dom.ls.LSOutput;

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

        System.out.print("Enter Price: ");
        int itemPrice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Item Quantity: ");
        int itemQuantity = sc.nextInt();
        sc.nextLine();

        Items items = new Items(itemName, itemPrice, itemQuantity);
        inventory.put(items.getItemId(), items);
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
            System.out.println("Item removed Successfully...");
        } else {
            System.out.println("Item removal failed....");
        }
    }

    public void SearchElementById(int id) {
        // SEARCH BY ELEMENT ID
        Items items = inventory.get(id);
        if (id != items.getItemId()) {
            System.out.println("ID not found");
        } else {
            System.out.println("ID Found: " + items.getItemName());
        }
    }

    public void SearchElementByKeyword(String keyword) {
        boolean found = false;
        for (Items item : inventory.values()) {
            if (item.getItemName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("FOUND!" + item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("NOT FOUND! " + keyword);
        }
    }

    public void updateItem(int id, double newPrice) {
        if (inventory.containsKey(id)) {
            Items items = inventory.get(id);
            items.setItemPrice(newPrice);
            System.out.println("Updated Successfully");

        } else {
            System.out.println("Update not Success");
        }
    }

    public void SaveToFile() {
        String filePath = "NotepadDatabase\\items.txt";
        File file = new File(filePath);

        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            if (!parentDirectory.mkdir()) {
                System.err.println("Error creating Directory: " + parentDirectory.getAbsolutePath());
                return;
            }
        }


        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter("NotepadDatabase\\user.txt"))) {
            for (Items items : inventory.values()) {
                dataWriter.write(items.toString());
                dataWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving user accounts");
        }
    }
}