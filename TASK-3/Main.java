
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Fee Report System =====");
            System.out.println("1. Add Accountant");
            System.out.println("2. View Accountants");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addAccountant();
                    break;
                case 2:
                    viewAccountants();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addAccountant() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        boolean success = AccountantService.registerAccountant(name, email, phone, password);
        System.out.println(success ? "Accountant added successfully!" : "Error adding accountant.");
    }

    private static void viewAccountants() {
        System.out.println("\n===== Accountant List =====");
        AccountantService.getAllAccountants().forEach(acc ->
                System.out.println(acc.getId() + " | " + acc.getName() + " | " + acc.getEmail() + " | " + acc.getPhone()));
    }
}
