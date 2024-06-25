import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String sampleString = """
                ..*..
                ....*
                .....
                .*...
                *....
                """;
        int length = 5;
        int width = 5;

        char[][] gameBoard = new char[length][width];
        Scanner scanner = new Scanner(sampleString);

        fillNewGameBoard(gameBoard, length, width, scanner);

        System.out.println("Original game board:");
        printBoard(gameBoard);

        updateGameBoard(gameBoard);

        System.out.println("Updated game board:");
        printBoard(gameBoard);
    }

    private static void fillNewGameBoard(char[][] gameBoard, int length, int width, Scanner scanner) {
        for (int y = 0; y < length; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (line.charAt(x) == '*') {
                    gameBoard[y][x] = '*';
                } else if (line.charAt(x) == '.') {
                    gameBoard[y][x] = '0';
                }
            }
        }
    }

    private static void updateGameBoard(char[][] gameBoard) {
        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[y].length; x++) {
                if (gameBoard[y][x] == '*') {
                    updateNewPosition(gameBoard, y, x);
                }
            }
        }
    }

    private static void printBoard(char[][] gameBoard) {
        for (char[] chars : gameBoard) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    private static void updateNewPosition(char[][] gameBoard, int y, int x) {
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < rowOffsets.length; i++) {
            int newPositionY = y + rowOffsets[i];
            int newPositionX = x + colOffsets[i];
            if (inBounds(gameBoard, newPositionY, newPositionX) && isValidInt(gameBoard[newPositionY][newPositionX])) {
                gameBoard[newPositionY][newPositionX] += 1;
            }
        }
    }

    private static boolean inBounds(char[][] board, int y, int x) {
        return y >= 0 && y < board.length && x >= 0 && x < board[0].length;
    }

    private static boolean isValidInt(char theValue) {
        return theValue >= '0' && theValue <= '8';
    }
}
