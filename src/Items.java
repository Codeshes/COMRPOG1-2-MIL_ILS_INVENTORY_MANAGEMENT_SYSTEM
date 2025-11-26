import java.util.Collections;
import java.util.Comparator;

public class Items {
    protected static int counterItemId = 100;
    protected int itemId;
    protected final String itemName;
    protected double itemPrice;
    protected int itemQuantity;

    Items(String itemName, double itemPrice, int itemQuantity) {
        this.itemId = counterItemId++;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public Items(int id, String itemName, double itemPrice, int itemQuantity) {
        this.itemId = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;

        if (id >= counterItemId) counterItemId = id + 1;
    }


    public int getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public double getItemPrice() { return itemPrice; }
    public int getItemQuantity() { return itemQuantity; }

    public void setItemName(int itemId) { this.itemId = itemId; }
    public void setItemPrice(int itemPrice) { this.itemPrice = itemPrice; }
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }
    public void setItemQuantity(int itemQuantity ) { this.itemQuantity = itemQuantity; }



    @Override
    public String toString() {
        return "ID: " + itemId + " | " +
                " Item name: " + itemName + " | " +
                " Item Price: " + itemPrice + " | " +
                " Item Quantity: " + itemQuantity;
    }

    public static Items fromFile(String line) {
        String[] p = line.split("\\|");

        if (p.length != 4) {
            throw new IllegalArgumentException("Invalid item data: " + line);
        }

        int id = Integer.parseInt(p[0].replace("ID:", "").trim());
        String name = p[1].replace("Item name:", "").trim();
        double price = Double.parseDouble(p[2].replace("Item Price:", "").trim());
        int qty = Integer.parseInt(p[3].replace("Item Quantity:", "").trim());

        return new Items(id, name, price, qty);
    }

}
