import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /**
     * This is the main method of the program. It reads input from a file, processes the data,
     * and prints the game board along with its field number.
     *
     * @param args the command line arguments
     * @throws FileNotFoundException if the input file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {

        // Create a scanner and add minesweeper_input.txt file as source.
        Scanner scanner = new Scanner(new File("src/minesweeper_input.txt"));
        int fieldNumber = 1; // Everytime we get done with a matrix, this field goes up by one.

        // While loop for running the program over multiple matrices, so we don't stop at the first one.
        while (scanner.hasNextLine()) {

            // Make a new string to parse the input to find the Length X Width numbers.
            String dimensions = scanner.nextLine();

            // Stop if there's nothing left (end of file).
            if (dimensions.isEmpty()) {
                continue;
            }

            // Convert string to integer, this should take the two numbers at the start of the file
            // and use them for the length and width.
            int length = Integer.parseInt(dimensions.split(" ")[0]);
            int width = Integer.parseInt(dimensions.split(" ")[1]);
            char[][] gameBoard = new char[length][width];

            // Create a new game-board with the provided parameters.
            fillNewGameBoard(gameBoard, length, width, scanner);

            // Update the game-board and replace all '0's with the right number
            // from 0-8 according to the mine placement.
            updateGameBoard(gameBoard);

            // Print the game-board along with its field number.
            System.out.printf("Field #%d:%n", fieldNumber);
            printBoard(gameBoard);
            System.out.println();
            fieldNumber++;
        }
    }

    /**
     * Fills the new game board with values read from a file using scanner.
     *
     * @param gameBoard the 2D array representing the game board
     * @param length    the length of the game board
     * @param width     the width of the game board
     * @param scanner   a Scanner object to read input
     */
    private static void fillNewGameBoard(char[][] gameBoard, int length, int width, Scanner scanner) {
        for (int y = 0; y < length; y++) {
            String line = scanner.nextLine();

            // Loop over all the characters of the game board and fill our game board
            // array with '0' if we find a '.' or '*' if we find a '*' from the input file.
            for (int x = 0; x < width; x++) {
                if (line.charAt(x) == '*') {
                    gameBoard[y][x] = '*';
                } else if (line.charAt(x) == '.') {
                    gameBoard[y][x] = '0';
                }
            }
        }
    }

    /**
     * Updates the game board by incrementing the neighboring cells of each mine cell according to '*' or the mines.
     *
     * @param gameBoard the 2D array representing the game board
     */
    private static void updateGameBoard(char[][] gameBoard) {
        // This loops over all the different elements of the 2d array.
        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[y].length; x++) {
                // If the yx position of the game board array has a '*', we update all
                // the values or neighbours around it.
                if (gameBoard[y][x] == '*') {
                    updateNewPosition(gameBoard, y, x);
                }
            }
        }
    }

    /**
     * Prints the game board.
     *
     * @param gameBoard the 2D array representing the game board
     */
    private static void printBoard(char[][] gameBoard) {
        // I was using a regular for-loop for printing before but Intellij suggested this other wierd way of doing it
        // so here I am using it lol.
        for (char[] chars : gameBoard) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    /**
     * Updates the neighboring cells of the given position on the game board by incrementing their values if valid.
     *
     * @param gameBoard the 2D array representing the game board
     * @param y         the vertical position on the game board
     * @param x         the horizontal position on the game board
     */
    private static void updateNewPosition(char[][] gameBoard, int y, int x) {

        // The row and colum offsets are basically just coordinate offsets for what the neighboring values should be.
        // For example, the row and colum offsets combined into one coordinate would be (-1, -1) for top-left,
        // (-1, 0) for right above or top, (-1, 1) for top-right, (0, -1) for left, (0, 1) for right, etc.
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < rowOffsets.length; i++) {
            int newPositionY = y + rowOffsets[i];
            int newPositionX = x + colOffsets[i];

            // Here we calculate the value a neighbour around the '*' or mine. We repeat this 8 times to cover
            // all the surrounding values and increment them by 1 if the value isn't out of bounds and within 0-8.
            if (inBounds(gameBoard, newPositionY, newPositionX) && isValidInt(gameBoard[newPositionY][newPositionX])) {
                gameBoard[newPositionY][newPositionX] += 1;
            }
        }
    }

    /**
     * Checks if the given position is within the bounds of the game board.
     *
     * @param board the 2D array representing the game board
     * @param y     the vertical position on the game board
     * @param x     the horizontal position on the game board
     * @return true if the position is within the bounds, false otherwise
     */
    private static boolean inBounds(char[][] board, int y, int x) {
        return y >= 0 && y < board.length && x >= 0 && x < board[0].length;
    }

    /**
     * Checks if the given character value is a valid integer, since only a max of 8 is possible for
     * a 3x3 surrounding area around the mines.
     *
     * @param theValue the value to check
     * @return true if the value represents is within 0 and 8, false otherwise
     */
    private static boolean isValidInt(char theValue) {
        return theValue >= '0' && theValue <= '8';
    }
}
