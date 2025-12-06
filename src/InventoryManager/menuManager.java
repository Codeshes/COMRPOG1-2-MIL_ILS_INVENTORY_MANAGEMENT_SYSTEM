package InventoryManager;

import java.util.InputMismatchException;
import java.util.Scanner;


public class menuManager {


    Scanner sc = new Scanner(System.in);
    InventoryManager manager = new InventoryManager();
    staffRequestManager requestManager = new staffRequestManager();
    UserManager manageUser = new UserManager();
    int choice;


    boolean signupPageRunning = true;

    public void menuStart() {

        while (signupPageRunning) {

            boolean isValid = false;
            while (!isValid) {
                try {
                    System.out.println("""
                            
                            === SIGN UP PAGE ===
                                [1]. Login
                                [0]. EXIT  \s""");

                    System.out.println("====== ENTER YOUR CHOICE ======");
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    isValid = true;

                } catch (InputMismatchException e) {
                    System.out.println("The choice must be a NUMBER, please try again.");
                    System.out.println("---------------------------");
                    sc.nextLine();
                }
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("====== ENTER YOUR CREDENTIALS ======");
                    System.out.print("Enter your username: ");
                    String userName = sc.nextLine();

                    System.out.print("Enter your password: ");
                    String userPassword = sc.nextLine();
                    System.out.println("====================================");
                    String role = manageUser.loginMethod(userName, userPassword);


                    if (role.equals("admin")) {
                        adminMenu(manageUser, manager);
                        isAdminMenuRunning = true;
                    } else if (role.equals("staff")) {
                        staffMenu(manager, requestManager);
                        isStaffMenuRunning = true;
                    } else {
                        System.out.println("Credentials not matched");

                    }


                }
                case 0 -> {
                    try {
                        System.out.print("Exiting Program");
                        for (int i = 0; i < 4; i++) {
                            Thread.sleep(250);
                            System.out.print(". ");
                        }
                        System.exit(0);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        signupPageRunning = false;
                        System.exit(0);
                    }
                }

                default -> {
                    try {
                        System.out.print("Invalid Choice. Please try again");
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(300);
                            System.out.print(".");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Exception occurred: " + e.getMessage());
                    }
                }
            }

        }
    }


    boolean isAdminMenuRunning = true;

    public void adminMenu(UserManager userManager, InventoryManager manageInventory) {
        int choice;
        while (isAdminMenuRunning) {
            try {
                System.out.println("""
                        ==================== ADMIN MENU ====================
                        [1]  Add Users
                        [2]  Display Users
                        [3]  Remove Users
                        -----------------------------------------------------
                                 Manage Inventory Section
                        -----------------------------------------------------
                        [4]  View All Items
                        [5]  Add Item
                        [6]  Remove Item
                        [7]  Search Item by ID
                        [8]  Search Item by Keyword
                        -----------------------------------------------------
                        [9]  View Staff Requests
                        [10] Process Request
                        [11] Logout
                        [0]  Exit
                        =====================================================
                        """);

                System.out.print("\nEnter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Choice must be a Number");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("Enter Username: ");
                        String userName = sc.nextLine().trim();

                        System.out.print("Enter password: ");
                        String userPassword = sc.nextLine().trim();

                        System.out.print("Enter the role. ADMIN/STAFF: ");
                        String role = sc.nextLine().trim();

                        if (userName.trim().isEmpty() || userPassword.trim().isEmpty() || role.trim().isEmpty()) {
                            System.out.println("ERROR: Field/s Cannot be empty.");
                            break;
                        } else {
                            userManager.addUserByAdmin(userName, userPassword, role);
                            validInput = true;
                        }


                    }

                }
                case 2 -> {
                    System.out.println("============= USER'S LIST =============");
                    userManager.displayUser();
                }
                case 3 -> {
                    userManager.displayUser();
                    int id;

                    while (true) {
                        System.out.print("Enter a USER ID to remove");
                        String input = sc.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("Field cannot be empty");
                            continue;
                        }

                        try {
                            id = Integer.parseInt(input);
                            userManager.removeUser(id);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid output");
                        }
                    }

                }
                case 4 -> {
                    System.out.println("============= INVENTORY ITEM LIST'S =============");
                    manageInventory.DisplayItems();
                }
                case 5 -> manageInventory.AddItems();
                case 6 -> {
                    manageInventory.DisplayItems();
                    int id;
                    while (true) {
                        System.out.println("Enter an item ID to be DELETED");
                        String input = sc.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("Field cannot be empty");
                            continue;
                        }

                        try {
                            id = Integer.parseInt(input);
                            manageInventory.RemoveItems(id);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input");
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println("Exception has occur: " + e.getMessage());
                        }

                    }


                }
                case 7 -> {
                    int id;
                    while (true) {
                        System.out.println("Enter an ID to");
                        String input = sc.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("Field cannot be empty");
                            continue;
                        }

                        try {
                            id = Integer.parseInt(input);
                            manageInventory.SearchElementById(id);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input");
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println("Exception has occur: " + e.getMessage());
                        }
                    }


                }
                case 8 -> {
                    boolean isEmpty = false;

                    while (!isEmpty) {
                        System.out.println("Enter an KEYWORD to search for an ITEM");
                        String keyword = sc.nextLine().trim();

                        if (keyword.trim().isEmpty()) {
                            System.out.println("Field cannot be empty");
                        } else {
                            manageInventory.SearchElementByKeyword(keyword);
                            isEmpty = true;
                        }
                    }
                }
                case 9 ->  requestManager.viewRequest();


                case 10 -> {
                    Admin admin = new Admin();
                    requestManager.processRequest(admin, manager);

                    try {
                        System.out.print("Returning to ADMIN MENU");
                        for (int i = 0; i < 5; i++) {

                            Thread.sleep(700);
                            System.out.print(".");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                case 11 -> {
                    try {
                        System.out.print("Logging out");
                        for (int i = 0; i < 5; i++) {

                            Thread.sleep(200);
                            System.out.print(".");
                        }
                        isAdminMenuRunning = false;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        isAdminMenuRunning = false;
                        System.out.println("Interrupted");

                    }
                }

                case 0 -> {
                    try {
                        System.out.print("Exiting Program");
                        for (int i = 0; i < 4; i++) {
                            Thread.sleep(250);
                            System.out.print(". ");
                        }
                        isAdminMenuRunning = false;
                        System.exit(0);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        isAdminMenuRunning = false;
                        System.exit(0);
                    }
                }
                default -> {
                    try {
                        System.out.print("Invalid input.");
                        for (int i = 0; i < 4; i++) {
                            Thread.sleep(200);
                            System.out.println(".");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println(".");
                    }
                }
            }

        }
    }


    /*====================================================================================*/
    boolean isStaffMenuRunning = true;

    public void staffMenu(InventoryManager inventoryManager, staffRequestManager staffRequestManager) {
        int staffChoice;

        while (isStaffMenuRunning) {
            try {

                System.out.println("""
                        
                        ===================== STAFF MENU =====================
                        [1]. View Items
                        [2]. Edit items (to be approved by admin)
                        [3]. View pending request
                        [4]. LOGOUT
                        [0]. EXIT
                        ======================================================""");

                staffChoice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                sc.nextLine();
                continue;
            }
            switch (staffChoice) {
                case 1 -> {
                    System.out.println("============= INVENTORY ITEM LIST'S =============");
                    inventoryManager.DisplayItems();
                }
                case 2 -> {
                    System.out.println("============= INVENTORY ITEM LIST'S =============");
                    inventoryManager.DisplayItems();

                    int itemId;
                    try {
                        while (true) {
                            System.out.println("Enter an Item ID to edit");
                            String input = sc.nextLine();

                            try {
                                itemId = Integer.parseInt(input);
                                String itemName = inventoryManager.getItemName(itemId);
                                if (itemName == null || itemName.isEmpty()) {
                                    System.out.println("Error: Item id not found");
                                    continue;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input, enter a number");
                            }

                        }

                        // will do the double validation
                        double proposedPrice;
                        while (true) {
                            System.out.println("Enter the new PRICE of the ITEM");
                            String input = sc.nextLine();

                            try {
                                proposedPrice = Double.parseDouble(input);
                                if (proposedPrice < 0) {
                                    System.out.println("Price cannot be negative");
                                    continue;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Input! Please enter a valid Input");
                            }
                        }
                        // will do the string validation
                        String reason;
                        while (true) {
                            System.out.println("Enter the reason of the Change");
                            reason = sc.nextLine().trim();

                            if (reason.trim().isEmpty()) {
                                System.out.println("Field cannot be empty");
                                continue;
                            }
                            break;


                        }
                        Request request = new Request(itemId, inventoryManager.getItemName(itemId), proposedPrice, reason);
                        staffRequestManager.submitRequest(request);

                        try {
                            System.out.print("Submitting Request");
                            for (int i = 0; i < 5; i++) {
                                Thread.sleep(500);
                                System.out.print(".");
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }  catch (NumberFormatException e) {
                        System.out.println("Enter a valid Input");

                    }

                }
                case 3 ->
                    /*
                    This block of code is for view pending request
                     */
                    staffRequestManager.pendingRequest();


                case 4 -> {
                    try {
                        System.out.print("Logging out");
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(200);
                            System.out.print(".");
                        }
                        isStaffMenuRunning = false;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Interrupted");
                        isStaffMenuRunning = false;
                    }
                }

                case 0 -> {
                    try {

                        System.out.print("Exiting");
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(200);
                            System.out.print(".");
                        }
                        System.exit(0);
                        isStaffMenuRunning = false;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.exit(0);
                        isAdminMenuRunning = false;
                    }
                }
                default -> {
                    try {
                        System.out.print("Invalid Choices, please try again.");
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(200);
                            System.out.println(".");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Error occurred." + e.getMessage());
                    }
                }
            }
        }
    }
}
