import java.io.*;
import java.util.*;

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name + "," + email;  
    }

   
    public static User fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
            return new User(parts[0].trim(), parts[1].trim());
        }
        return null;
    }
}

class UserManager {
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    
    public void addUser(String name, String email) {
        users.add(new User(name, email));
    }

    
    public void saveUsersToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
            System.out.println("Users saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    
    public void loadUsersFromFile(String filename) {
        users.clear(); 
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromString(line);
                if (user != null) {
                    users.add(user);
                }
            }
            System.out.println("Users loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }

    
    public void printUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users) {
                System.out.println("Name: " + user.getName() + ", Email: " + user.getEmail());
            }
        }
    }
}

public class Main4 {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();

       
        userManager.addUser("John Doe", "john.doe@example.com");
        userManager.addUser("Jane Smith", "jane.smith@example.com");
        userManager.addUser("Alice Johnson", "alice.johnson@example.com");

        
        System.out.println("Users before saving to file:");
        userManager.printUsers();

        
        String filename = "users.txt";
        userManager.saveUsersToFile(filename);

       
        System.out.println("\nLoading users from the file...");
        userManager.loadUsersFromFile(filename);

        System.out.println("Users after loading from file:");
        userManager.printUsers();
    }
}
