package InventoryManager;

public class Request {
    private static int counter = 100;
    private final int requestId;
    private final int itemId;
    private final String itemName;
    private final double proposedPrice;
    private final String reason;

    public Request(int itemId, String itemName, double proposedPrice, String reason) {
        this.requestId = counter++;
        this.itemId = itemId;
        this.itemName = itemName;
        this.proposedPrice = proposedPrice;
        this.reason = reason;
    }

    public Request(int requestId, int itemId, String itemName, double proposedPrice, String reason) {
        this.requestId = requestId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.proposedPrice = proposedPrice;
        this.reason = reason;
        if (requestId >= counter) counter = requestId + 1;
    }


    public int getRequestId() { return requestId; }
    public int getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public double getProposedPrice() { return proposedPrice; }
    public String getReason() { return reason; }


    @Override
    public String toString() {
        return "RID:" + requestId +
                "|ID:" + itemId +
                "|Item:" + itemName +
                "|Price:" + proposedPrice +
                "|Reason:" + reason;
    }

    public static Request fromFile(String line) {
        String[] p = line.split("\\|");

        int rid = Integer.parseInt(p[0].replace("RID:", "").trim());
        int iid = Integer.parseInt(p[1].replace("IID:", "").trim());
        String itemName = p[2].replace("Item:", "").trim();
        double price = Double.parseDouble(p[3].replace("Price:", "").trim());
        String reason = p[4].replace("Reason:", "").trim();

        return new Request(rid, iid, itemName, price, reason);
    }
}
