package InventoryManager;

import java.io.*;
import java.util.TreeMap;

public class UserManager {

    private final TreeMap<Integer, User> users = new TreeMap<>();

    public UserManager() {
        loadFromFile();
    }

    public void addUserByAdmin(String userName, String userPassword, String role) {
        User user = new User(userName, userPassword, role);
        users.put(user.getID(), user);

        System.out.println("User added successfully.");
        System.out.println("USER ID: " + user.getID() + " | Username: " + userName + " (" + role + ")");

        saveToFile();
    }

    public void displayUser() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void removeUser(int id) {

        if (users.containsKey(id)) {
            users.remove(id);
            System.out.println("User removed successfully!");
            saveToFile();
        } else {
            System.out.println("User not found.");
        }
    }

    public String loginMethod(String userName, String passWord) {


        for (User user : users.values()) {
            if (user.getUserName().equals(userName)) {

                if (user.getUserPassword().equals(passWord)) {
                    System.out.println("Login successful. Welcome " + user.getRole());
                    return user.getRole().toLowerCase();
                }

                System.out.println("Incorrect password.");
                return "none";
            }
        }

        System.out.println("User not found.");
        return "none";
    }

    public void saveToFile() {
        String filePath = "NotepadDatabase/user.txt";

        File file = new File(filePath);
        File parentDirectory = file.getParentFile();

        if (parentDirectory != null && !parentDirectory.exists()) {
            if (!parentDirectory.mkdirs()) {
                System.err.println("Error creating directory: " + parentDirectory.getAbsolutePath());
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (User user : users.values()) {
                writer.write(user.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving user accounts: " + e.getMessage());
        }
    }


    public void loadFromFile() {
        String filePath = "NotepadDatabase/user.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {

                User user = User.fromFile(line);


                users.put(user.getID(), user);
            }

        } catch (FileNotFoundException e) {
            System.out.println("User database not found. Creating new one...");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading user accounts: " + e.getMessage());
        }
    }
}
