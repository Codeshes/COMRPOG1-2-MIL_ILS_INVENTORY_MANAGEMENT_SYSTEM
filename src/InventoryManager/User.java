package InventoryManager;

public class User {
    protected static int counterUserId = 200;
    protected final int userId;
    protected String userName;
    protected String userPassword;
    protected String role;

    public User(String userName, String userPassword, String role) {
        this.userId = counterUserId++;
        this.userName = userName;
        this.userPassword = userPassword;
        this.role = role;

    }

    public User(int id, String userName, String userPassword, String role) {
        this.userId = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.role = role;

        if (id >= counterUserId) counterUserId = id + 1;
    }
    public int getID() { return userId; }
    public String getUserName() { return userName; }
    public String getUserPassword() { return userPassword; }
    public String getRole() { return  role; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
    public void setRole(String role) { this.role = role; }

    @Override

    public String toString() {
        return "ID: " + userId + " | " +
                "Username: " + userName + " | " +
                "Password: " + userPassword + " | " +
                "Role: " + role;

        //return userId + "," + userName + "," + userPassword + "," + role;
    }

    public static User fromFile(String line) {
        String[] p = line.split("\\|");

        if (p.length != 4) {
            throw new IllegalArgumentException("Invalid user data format: " + line);
        }


            int id = Integer.parseInt(p[0].replace("ID:", "").trim());
            String userName = p[1].replace("Username:", "").trim();
            String userPassword = p[2].replace("Password: ", "").trim();
            String role = p[3].replace("Role:", "").trim();

            return new User(id, userName, userPassword, role);
    }
}
