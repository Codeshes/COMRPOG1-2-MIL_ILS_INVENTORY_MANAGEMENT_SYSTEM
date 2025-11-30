package InventoryManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Items {
    protected static int counterItemId = 100;
    protected int itemId;
    protected final String itemName;
    protected double itemPrice;
    protected int itemQuantity;

    private final LocalDate dateAdded;
    private LocalDate dateModified;

    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    public static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    Items(String itemName, double itemPrice, int itemQuantity) {
        this.itemId = counterItemId++;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;

        this.dateAdded = LocalDate.now();
        this.dateModified = dateAdded;
    }

    public Items(int id, String itemName, double itemPrice, int itemQuantity, LocalDate dateAdded, LocalDate dateModified) {
        this.itemId = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;

        this.dateAdded = dateAdded;
        this.dateModified = dateModified;

        if (id >= counterItemId) counterItemId = id + 1;
    }


    public int getItemId() { return itemId; }

    public String getItemName() { return itemName; }

    public double getItemPrice() { return itemPrice; }

    public int getItemQuantity() { return itemQuantity; }

    public String getFormattedDateAdded() { return dateAdded.format(DISPLAY_FORMAT); }

    public String getFormattedDateModified() { return dateModified.format(DISPLAY_FORMAT); }

    public String getDisplayDates() {
        String result = "Date Added: " + dateAdded.format(DISPLAY_FORMAT);
        if (!dateModified.equals(dateAdded)) {
            result += " | Date Modified: " + dateModified.format(DISPLAY_FORMAT);
        }
        return result;
    }

    public void updateModifiedDate() {
        this.dateModified = LocalDate.now();
    }

    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }





    @Override
    public String toString() {
        return "ID: " + itemId + " | " +
                " Item name: " + itemName + " | " +
                " Item Price: " + itemPrice + " | " +
                " Item Quantity: " + itemQuantity + " | " +
                getDisplayDates();
    }

    public String toFileString() {
        return "ID:" + itemId + " | " +
                "Item name:" + itemName + " | " +
                "Item Price:" + itemPrice + " | " +
                "Item Quantity:" + itemQuantity + " | " +
                "Date Added:" + dateAdded.format(FILE_FORMAT) + " | " +
                "Date Modified:" + dateModified.format(FILE_FORMAT);
    }


    public static Items fromFile(String line) {
        String[] p = line.split("\\|");

        if (p.length != 6) {
            throw new IllegalArgumentException("Invalid item data: " + line);
        }

        int id = Integer.parseInt(p[0].replace("ID:", "").trim());
        String name = p[1].replace("Item name:", "").trim();
        double price = Double.parseDouble(p[2].replace("Item Price:", "").trim());
        int qty = Integer.parseInt(p[3].replace("Item Quantity:", "").trim());
        LocalDate dateAdded = LocalDate.parse(p[4].replace("Date Added:", "").trim());
        LocalDate dateModified = LocalDate.parse(p[5].replace("Date Modified:", "").trim());

        return new Items(id, name, price, qty, dateAdded, dateModified);
    }

}
