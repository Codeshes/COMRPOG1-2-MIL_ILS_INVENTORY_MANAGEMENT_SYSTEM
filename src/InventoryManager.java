
import java.util.HashMap;
import java.util.Scanner;

public class InventoryManager {
    private HashMap<Integer, Items> inventory = new HashMap<>();
    private Scanner sc = new Scanner(System.in);


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
    }

    public boolean isEmpty() {  return inventory.isEmpty(); }

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


}
