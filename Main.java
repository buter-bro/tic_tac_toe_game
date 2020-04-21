package tictactoe;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static boolean isWin(char[][] a, char s) {
        for (int i = 0; i < 3; i++) {
            if (a[i][0] == s && a[i][1] == s && a[i][2] == s) {
                return true;
            }
            if (a[0][i] == s && a[1][i] == s && a[2][i] == s) {
                return true;
            }
        }
        if (a[0][0] == s && a[1][1] == s && a[2][2] == s) {
            return true;
        }
        if (a[0][2] == s && a[1][1] == s && a[2][0] == s) {
            return true;
        }
        return false;
    }
    public static boolean isImpossible(char[][] a) {
        int x = 0;
        int o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == 'X') {
                    x++;
                }
                if (a[i][j] == 'O') {
                    o++;
                }
            }
        }
        if (x - o > 1 || o - x > 1) {
            return true;
        }
        if (isWin(a, 'X') && isWin(a, 'O')) {
            return true;
        }
        return false;
    }
    public static boolean isFinished(char[][] a) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }
    public static char[][] firstMove(char[][] field, char s) {
        boolean isInputCorrect = false;
        while (!isInputCorrect) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the coordinates: ");
            String xS = scanner.next();
            if (xS.length() > 1) {
                if (xS.matches("[0-9]+")) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                } else {
                    System.out.println("You should enter numbers!");
                    continue;
                }
            }
            String yS = scanner.next();
            if (yS.length() > 1) {
                if (yS.matches("[0-9]+")) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                } else {
                    System.out.println("You should enter numbers!");
                    continue;
                }
            }
            if (!xS.matches("[0-9]+") || !yS.matches("[0-9]+")) {
                System.out.println("You should enter numbers!");
            } else {
                int x = Integer.parseInt(xS);
                int y = Integer.parseInt(yS);
                if (x > 3 || x < 1 || y > 3 || y < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (field[3 - y][x - 1] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        field[3 - y][x - 1] = s;
                        isInputCorrect = true;
                    }
                }
            }
        }
        return field;
    }
    public static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    System.out.print("| " + field[i][j] + " ");
                }
                if (j == 2) {
                    System.out.print(field[i][j] + " |\n");
                } else if (j != 0){
                    System.out.print(field[i][j] + " ");
                }
            }
        }
        System.out.println("---------");
    }
    public static void main(String[] args) {
        char[][] field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = '_';
            }
        }
        while (true) {
            printField(field);
            field = firstMove(field, 'X');
            if (isImpossible(field)) {
                printField(field);
                System.out.print("Impossible");
                break;
            } else if (isWin(field, 'X')) {
                printField(field);
                System.out.print("X wins");
                break;
            } else if (isWin(field, 'O')) {
                printField(field);
                System.out.print("O wins");
                break;
            } else if (!isWin(field, 'X') && !isWin(field, 'O') && isFinished(field)) {
                printField(field);
                System.out.print("Draw");
                break;
            }
            printField(field);
            field = firstMove(field, 'O');
            if (isImpossible(field)) {
                printField(field);
                System.out.print("Impossible");
                break;
            } else if (isWin(field, 'X')) {
                printField(field);
                System.out.print("X wins");
                break;
            } else if (isWin(field, 'O')) {
                printField(field);
                System.out.print("O wins");
                break;
            } else if (!isWin(field, 'X') && !isWin(field, 'O') && isFinished(field)) {
                printField(field);
                System.out.print("Draw");
                break;
            }
        }
    }
}
