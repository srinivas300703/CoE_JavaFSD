import java.util.Scanner;

public class ReciprocalCalculator {
    
    public static void main(String[] args) {
        processInput();
    }

    
    public static void processInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number to calculate its reciprocal:");

        try {
            String input = scanner.nextLine();
            double number = Double.parseDouble(input); 

            if (number == 0) {
                throw new ArithmeticException("Cannot calculate reciprocal of zero.");
            }

            double reciprocal = 1 / number;
            System.out.println("The reciprocal of " + number + " is: " + reciprocal);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a valid number.");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close(); 
        }
    }
}