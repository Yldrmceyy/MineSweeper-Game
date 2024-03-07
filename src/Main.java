
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper Game!");
        System.out.print("Enter the line number for the width of the game:");  // Item 7
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            scanner.next();
            System.out.print("Enter the line number for the width of the game:");
        }
        int rows = scanner.nextInt();

        System.out.print("Enter the column number for the height of the game:"); //TItem 7
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            scanner.next();
            System.out.print("Enter the column number for the height of the game:");
        }
        int columns = scanner.nextInt();

        if (rows < 2 || columns < 2) {
            System.out.println("The number of rows and columns must be at least 2.");
            return;
        }

        MineSweeper mine = new MineSweeper(rows, columns);
        mine.run();
    }
}