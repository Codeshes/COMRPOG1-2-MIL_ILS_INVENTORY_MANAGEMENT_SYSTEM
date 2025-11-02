public class Request {
    private static int counter = 100;
    private final int id;
    private final int itemId;
    private double proposedPrice;
    private final String reason;


    Request(int itemId, double proposedPrice, String reason) {
        this.id = counter++;
        this.itemId = itemId;
        this.proposedPrice = proposedPrice;
        this.reason = reason;
    }

    public int getId() { return  id; }
    public int getItemName() { return itemId; }
    public double getProposedPrice() { return proposedPrice; }
    public String getReason() { return reason;}

    public void setProposedPrice(double proposedPrice) { this.proposedPrice = proposedPrice; }

    @Override
    public String toString() {
        return "Price Change Request:" +
                "ID = " + id +
                ", Item name: " + itemId + '\'' +
                ", Proposed Price: " + proposedPrice +
                ", REASON: " + reason + '\'';
    }

}
