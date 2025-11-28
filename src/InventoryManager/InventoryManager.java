package InventoryManager;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.TreeMap;
import java.util.Scanner;
import java.io.*;

public class InventoryManager {
    private final TreeMap<Integer, Items> inventory = new TreeMap<>();
    private final Scanner sc = new Scanner(System.in);
    private final String FILE_NAME = "items.txt";

    InventoryManager() {
        loadFromFile();
    }

    public void AddItems() {
        System.out.print("Enter the item name: ");
        String itemName = sc.nextLine();

        System.out.print("Enter Price: ");
        double itemPrice = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Item Quantity: ");
        int itemQuantity = sc.nextInt();
        sc.nextLine();

        Items items = new Items(itemName, itemPrice, itemQuantity);
        inventory.put(items.getItemId(), items);
        SaveToFile();
        System.out.println("Item added Successfully: " + items);
    }

    public void DisplayItems() {
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

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty");
            return;
        }
        for (Items items : inventory.values()) {
            System.out.println(items);
        }
        System.out.println("=====================================================================");
        System.out.println("\nThe total Prices of all items were in stock are: " + formattedCurrency);
        System.out.println("\n");



    }

    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    public void RemoveItems(int id) {
            Items itemToDelete = inventory.remove(id);

            if (itemToDelete != null) {
                System.out.println("Item removed Successfully: " + itemToDelete.getItemName());
                SaveToFile();
            } else {
                System.out.println("Item removal failed: ID " + id + " not found.");
            }

    }

    public void SearchElementById(int id) {
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
            items.setItemPrice(newPrice);
            SaveToFile();
            System.out.println("Updated Successfully for ID: " + id);
        } else {
            System.out.println("Update not Success: ID " + id + " not found.");
        }
    }

    public String getItemName(int id) {
        if (inventory.containsKey(id)) {
            Items items = inventory.get(id);

            return items.getItemName();
        } else {
            System.out.println("Error: Item with ID " + id + " not found on inventory");
        }
        return "";
    }

    public void SaveToFile() {
        String filePath = "NotepadDatabase\\" + FILE_NAME;
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            if (!parentDirectory.mkdir()) {
                System.err.println("Error creating Directory: " + parentDirectory.getAbsolutePath());
                return;
            }
        }

        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(file))) {
            for (Items item : inventory.values()) {
                dataWriter.write(item.toString());
                dataWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory items: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try (BufferedReader dataReader = new BufferedReader(new FileReader("NotepadDatabase\\" + FILE_NAME))) {
            String line;
            while ((line = dataReader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    Items item = Items.fromFile(line);
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