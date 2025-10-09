public class Items {
    private static int counterItemId = 100;
    private int itemId;
    private String itemName;
    private double itemPrice;
    private int itemQuantity;

    Items(String itemName, double itemPrice, int itemQuantity) {
        this.itemId = counterItemId++;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public int getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public double getItemPrice() { return itemPrice; }
    public int getItemQuantity() { return itemQuantity; }

    public void setItemName(int itemId) { this.itemId = itemId; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }
    public void setItemQuantity(int itemQuantity ) { this.itemQuantity = itemQuantity; }


    @Override
    public String toString() {
        return "ID: " + itemId + " | Item name: " + itemName + " | Item price: " + itemPrice + " | Item Quantity: " + itemQuantity;
    }
}
