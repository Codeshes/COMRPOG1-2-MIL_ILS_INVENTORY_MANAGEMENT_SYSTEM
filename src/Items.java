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
        return itemId + "," + itemName + "," + itemPrice + "," + itemQuantity;
    }

    public static Items fromFile(String line) {
        String[] p = line.split(",");

        if (p.length != 4) {
            throw new IllegalArgumentException("Invalid user data: " + line);
        }


        return new Items (
                Integer.parseInt(p[0].trim()),
                p[1],
                Double.parseDouble(p[2]),
                Integer.parseInt(p[3])
        );
    }

}
