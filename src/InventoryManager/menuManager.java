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
                Thread.currentThread().interrupt();
                System.out.println("Choice must be a Number");
                break;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Username: ");
                    String userName = sc.nextLine().trim();

                    System.out.print("Enter password: ");
                    String userPassword = sc.nextLine().trim();

                    System.out.print("Enter the role. ADMIN/STAFF: ");
                    String role = sc.nextLine().trim();
                    userManager.addUserByAdmin(userName, userPassword, role);

                }
                case 2 -> {
                    System.out.println("============= USER'S LIST =============");
                    userManager.displayUser();
                }
                case 3 -> {
                    userManager.displayUser();
                    try {
                        System.out.println("Enter a user ID to remove in the user's List");
                        int id = sc.nextInt();
                        userManager.removeUser(id);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input");
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Exception has occur: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("============= INVENTORY ITEM LIST'S =============");
                    manageInventory.DisplayItems();
                }
                case 5 -> manageInventory.AddItems();
                case 6 -> {
                    manageInventory.DisplayItems();
                    try {

                        System.out.println("Enter an item ID to be DELETED");
                        int id = sc.nextInt();
                        manageInventory.RemoveItems(id);

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input");
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Exception has occur: " + e.getMessage());
                    }
                }
                case 7 -> {
                    try {


                        System.out.println("Enter an ID to search for an ITEM");
                        int id = sc.nextInt();
                        manageInventory.SearchElementById(id);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input");
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Exception has occur: " + e.getMessage());
                    }
                }
                case 8 -> {
                    System.out.println("Enter an KEYWORD to search for an ITEM");
                    String keyword = sc.nextLine();
                    manageInventory.SearchElementByKeyword(keyword);
                }
                case 9 -> requestManager.viewRequest();

                case 10 -> {
                    Admin admin = new Admin();
                    requestManager.processRequest(admin, manager);


                    System.out.print("Returning to ADMIN MENU");
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(700);
                            System.out.print(".");
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }

                case 11 -> {
                    System.out.print("Logging out");
                    try {
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
                    System.out.print("Invalid input.");

                    try {
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
                Thread.currentThread().interrupt();
                System.out.println("Invalid Input");
                break;
            }
            switch (staffChoice) {
                case 1 -> {
                    System.out.println("============= INVENTORY ITEM LIST'S =============");
                    inventoryManager.DisplayItems();
                }
                case 2 -> {
                    System.out.println("============= INVENTORY ITEM LIST'S =============");
                    inventoryManager.DisplayItems();

                    if (inventoryManager.isEmpty()) {
                        System.out.println("Inventory is EMPTY no ITEM to EDIT");
                        return;
                    }
                    try {

                        System.out.println("Enter Item ID to edit");
                        int itemId = sc.nextInt();
                        String itemName = inventoryManager.getItemName(itemId);

                        if (itemName == null || itemName.isEmpty()) {
                            System.out.println("Error: the entered Item ID was ot found or is invalid");
                            break;
                        }
                        System.out.println("Enter the new PRICE of the ITEM");
                        double proposedPrice = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("Enter the reason of Change");
                        String reason = sc.nextLine();

                        Request request = new Request(itemId, itemName, proposedPrice, reason);

                        staffRequestManager.submitRequest(request);
                        System.out.print("Submitting request");
                        try {
                            for (int i = 0; i < 4; i++) {

                                Thread.sleep(200);
                                System.out.print(".");
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input");
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Exception has occur: " + e.getMessage());
                    }

                }
                case 3 ->
                    /*
                    This block of code is for view pending request
                     */
                        staffRequestManager.pendingRequest();

                case 4 -> {
                    System.out.print("Logging out");
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(200);
                            System.out.print(".");
                            isStaffMenuRunning = false;
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Interrupted");
                            isStaffMenuRunning = false;
                        }
                    }
                }
                case 0 -> {
                    System.out.print("Exiting");

                    try {
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
                    System.out.print("Invalid Choices, please try again.");

                    try {
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