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
        return "ID: " + itemId + " | Item name: " + itemName + " | Item price: " + itemPrice + " | Item Quantity: " + itemQuantity;
    }
}
