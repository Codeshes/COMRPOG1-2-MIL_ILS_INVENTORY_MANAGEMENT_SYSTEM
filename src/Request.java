public class Request {
    private static int counter = 100;
    private final int id;
    private final String itemName;
    private double proposedPrice;
    private final String reason;


    Request(String itemName, double proposedPrice, String reason) {
        this.id = counter++;
        this.itemName = itemName;
        this.proposedPrice = proposedPrice;
        this.reason = reason;
    }

    Request (int id, String itemName, double proposedPrice, String reason ) {
        this.id = id;
        this.itemName = itemName;
        this.proposedPrice = proposedPrice;
        this.reason = reason;

        if (id >= counter) counter = id + 1;

    }

    public int getId() { return  id; }
    public String getItemName() { return itemName; }
    public double getProposedPrice() { return proposedPrice; }
    public String getReason() { return reason;}

    public void setProposedPrice(double proposedPrice) { this.proposedPrice = proposedPrice; }

    @Override
    public String toString() {
        return "ID:" + id +
                "|Item name:" + itemName +
                "|Proposed Price: " + proposedPrice +
                "|REASON:" + reason;

    }

    public static Request fromFile(String line) {
        String[] p = line.split("\\|");

        if (p.length != 4){
            throw new IllegalArgumentException("Invalid user data format: " + line);
        }

            int itemId = Integer.parseInt(p[0].replace("ID:", "").trim());
            String itemNAME = p[1].replace("Item name:", "").trim();
            double proposedPrices = Double.parseDouble(p[2].replace("Proposed Price: ", "").trim());
            String reason = p[3].replace("REASON:", "").trim();


            return  new Request(itemId, itemNAME, proposedPrices, reason);
    }

}
