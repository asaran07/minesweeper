import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String sampleString = """
                ..*..
                ...*.
                .....
                .*...
                .....
                """;
        int length = 5;
        int width = 5;
        char[][] gameBoard = new char[length][width];
        Scanner scanner = new Scanner(sampleString);
        for (int i = 0; i < length; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < width; j++) {
                if (line.charAt(j) == '*') {
                    gameBoard[i][j] = '*';
                }
                else if (line.charAt(j) == '.') {
                    gameBoard[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
}