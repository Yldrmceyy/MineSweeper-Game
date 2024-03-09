import java.util.Random;
import java.util.Scanner;

// Class representing a Minesweeper game
public class MineSweeper { //Item 5
    public int rowNumber;
    public int columnNumber;
    public int numberMines;
    public String[][] mineMap;
    public String[][] board;
    public boolean isGameOver = false;


    // Constructor for the MineSweeper class.
    public MineSweeper(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        mineMap = new String[rowNumber][columnNumber];
        board = new String[rowNumber][columnNumber];
    }

    //Starts the game by calling createBoard() and placeMines() methods to create the game board and place mines.
    //Prints the mineMap and board matrices to the console.Initiates the game loop by calling the play() method.
    public void run() { //Item 6
        createBoard();
        placeMines();

        System.out.println("Mine Map");
        printBoard(mineMap);

        System.out.println("Game Board:");
        printBoard(board);

        play();
    }

    // Method to create the game board
    public void createBoard() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                mineMap[i][j] = "-";
                board[i][j] = "-";
            }
        }
    }

    // Method to print the game board
    public void printBoard(String[][] board) {
        for (String[] row : board) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    //Places mines randomly on the board.Calculates the total number of mines and places that number of mines at random coordinates.
    //Marks each mine's coordinate with the '*' character in the mineMap.
    public void placeMines() { //Item 8
        Random random = new Random();
        int totalElement = rowNumber * columnNumber;
        this.numberMines = totalElement / 4;

        for (int i = 0; i < numberMines; i++) {
            int randomRow = random.nextInt(rowNumber); //Item 9
            int randomColumn = random.nextInt(columnNumber); //Item 9

            while (mineMap[randomRow][randomColumn].equals("*")) {
                randomRow = random.nextInt(rowNumber);
                randomColumn = random.nextInt(columnNumber);
            }

            mineMap[randomRow][randomColumn] = "*";
        }
        System.out.println("Total number of mines: " + numberMines);
    }

    // Method to handle the main gameplay loop
    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            //Prompts the user to select a cell by entering its coordinates.
            System.out.print("Please enter the row number (starting from 0): "); //Item 9
            int row = scanner.nextInt();

            System.out.print("Please enter the column number (starting from 0): "); //Item 9
            int column = scanner.nextInt();

            //Checks if the entered coordinates are valid.
            if (row < 0 || row >= rowNumber || column < 0 || column >= columnNumber) { //Item 10
                System.out.println("Invalid coordinates. Please re-enter row and column index values.");
                continue;
            }

            //Checks if the selected cell has already been opened.
            if (!board[row][column].equals("-")) {
                System.out.println("You have already opened this cell. Please choose another one.");
                continue;
            }

            //If the selected cell contains a mine, ends the game and prints the game over message along with the mine locations.
            if (mineMap[row][column].equals("*")) { //Item 13
                System.out.println("You stepped on a mine! Game Over. 😥"); //Item 15
                isGameOver = true;
                printBoard(mineMap);
            } else {
                checkNearbyCells(row, column);
                printBoard(board);

                // Count the number of open cells on the board
                int openCells = countOpenCells();

                // Check if the number of open cells equals the total number of cells minus the number of mines.
                // If true, print a congratulatory message indicating that the player has won the game.
                if (openCells == (rowNumber * columnNumber - numberMines)) { //Item 14
                    System.out.println("Congratulations, You Won! 🎉"); //Item 15
                    isGameOver = true;
                }
            }
        }
    }

    // Method to check nearby cells for mines and update the board
    public void checkNearbyCells(int row, int col) { //Item 12
        int counter = 0;
        if (mineMap[row][col].equals("-")) {
            // Check for mines in vertical positions: top, bottom, left, and right
            if (col < (columnNumber - 1) && mineMap[row][col + 1].equals("*")) {
                counter++;
            }
            if (row < (rowNumber - 1) && mineMap[row + 1][col].equals("*")) {
                counter++;
            }
            if (col > 0 && mineMap[row][col - 1].equals("*")) {
                counter++;
            }
            // Check for mines in diagonal positions
            if (row > 0 && col < (columnNumber - 1) && mineMap[row - 1][col + 1].equals("*")) {
                counter++;
            }
            if (row < (rowNumber - 1) && col < (columnNumber - 1) && mineMap[row + 1][col + 1].equals("*")) {
                counter++;
            }
            if (row > 0 && col > 0 && mineMap[row - 1][col - 1].equals("*")) {
                counter++;
            }
            if (row < (rowNumber - 1) && col > 0 && mineMap[row + 1][col - 1].equals("*")) {
                counter++;
            }
        }
        // Update the board with the count of nearby mines
        board[row][col] = String.valueOf(counter); //Item 11
    }


    // Method to count open cells on the board
    //Counts all cells in the board matrix that are different from '-' and '*' and returns that number.
    public int countOpenCells() {
        int openCells = 0;
        for (String[] row : board) {
            for (String cell : row) {
                if (!cell.equals("-") && !cell.equals("*")) {
                    openCells++;
                }
            }
        }
        return openCells;
    }
}
