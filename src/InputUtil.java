import java.util.Scanner;
 
/**
 * Shared console-input helpers.
 * Every menu uses these methods, so the program never crashes on bad input.
 */
public class InputUtil {
 
    /** Keeps asking until the user types a whole number. */
    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
 
    /** Keeps asking until the number is between min and max (inclusive). */
    public static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            int value = readInt(sc, prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }
 
    /** Keeps asking until the user types some text (not empty). */
    public static String readNonEmpty(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be empty.");
        }
    }
 
    /** Asks a yes/no question and returns true for yes. */
    public static boolean readYesNo(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String line = sc.nextLine().trim().toLowerCase();
            if (line.equals("y") || line.equals("yes")) {
                return true;
            }
            if (line.equals("n") || line.equals("no")) {
                return false;
            }
            System.out.println("Please type y or n.");
        }
    }
}
