import java.io.*;
import java.util.LinkedHashMap;

public class UserManager {
    private final LinkedHashMap<String, User> users = new LinkedHashMap<>();

    UserManager() {
        loadFromFile();
    }

    //Add User
    public void addUserByAdmin(String userName, String userPassword, String role) {
        User user = new User(userName, userPassword, role);
        users.put(userName, user);
        System.out.println("User added Successfully");
        SaveToFile();
        System.out.println("USER ID: " + user.getID() + " User added: " + userName + " (" + role + ")");

    }

    public void displayUser() {
        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void removeUser(String user) {
        displayUser();
        if (users.containsKey(user)) {
            users.remove(user);
            System.out.println("Removed Successfully!");
            SaveToFile();
        } else {
            System.out.println("User not found.");
        }

    }

    public String loginMethod(String userName, String passWord) {
        User user = users.get(userName);

        if (user != null && user.getUserPassword().equals(passWord)) {
            System.out.println("Login Successfully. Welcome " + user.getRole());
            return user.getRole().toLowerCase();
        } else {
            System.out.println("Login failed.");
            return "none";
        }
    }
    // Save data to the user.txt
    public void SaveToFile() {
        // I'm going to define file path
        String filePath = "NotepadDatabase\\user.txt";
        File file = new File(filePath);

        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            if (!parentDirectory.mkdir()) {
                System.err.println("Error creating Directory: " + parentDirectory.getAbsolutePath());
                return;
            }
        }

        try(BufferedWriter dataWriter = new BufferedWriter(new FileWriter("NotepadDatabase\\user.txt"))) {
            for (User users : users.values()) {
                dataWriter.write(users.toString());
                dataWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving user accounts");
        }

    }

    public void loadFromFile() {
        try (BufferedReader dataReader = new BufferedReader(new FileReader("NotepadDatabase\\user.txt"))) {
            String line;
            while ((line = dataReader.readLine()) != null) {
                User user = User.fromFile(line);
                users.put(user.getUserName(), user);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error loading user accounts: " + e.getMessage());
        } catch (IOException | NumberFormatException e) {
            System.out.println("error: " + e.getMessage());
        }

    }


}
