package Tic_Tac_Toe;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private final static char[][] matrix = new char[3][3];

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            Arrays.fill(matrix[i] ,' ');
        }
        display();
        boolean move = true;

        while (analyse()) {
            if (move) {
                int num1 = 0, num2 = 0;
                boolean flag = true;
                while (flag) {
                    System.out.print("Enter the coordinates: ");
                    String inputT = scanner.nextLine();
                    boolean tempFlag = true;
                    try {
                        String [] pieces = inputT.split(" ");
                        num1 = Integer.parseInt(pieces[0]);
                        num2 = Integer.parseInt(pieces[1]);

                    }catch (NumberFormatException e) {
                        System.out.println("You should enter numbers!");
                        tempFlag = false;
                    }

                    if (tempFlag) {
                        if ((num1 < 4) && (num1 > 0) && (num2 < 4) && (num2 > 0)) {
                            num1 = num1 - 1;
                            num2 = 3 - num2;
                            int temp = num1;
                            num1 = num2;
                            num2 = temp;
                            if (matrix[num1][num2] == ' ') {
                                flag = false;
                            } else {
                                System.out.println("This cell is occupied! Choose another one!");
                            }
                        } else {
                            System.out.println("Coordinates should be from 1 to 3!");
                        }
                    }

                }

                insertX(num1, num2);
                display();
                move = false;
            }else if (!move) {
                int num1 = 0, num2 = 0;
                boolean flag = true;
                while (flag) {
                    System.out.print("Enter the coordinates: ");
                    String inputT = scanner.nextLine();
                    boolean tempFlag = true;
                    try {
                        String [] pieces = inputT.split(" ");
                        num1 = Integer.parseInt(pieces[0]);
                        num2 = Integer.parseInt(pieces[1]);

                    }catch (NumberFormatException e) {
                        System.out.println("You should enter numbers!");
                        tempFlag = false;
                    }

                    if (tempFlag) {
                        if ((num1 < 4) && (num1 > 0) && (num2 < 4) && (num2 > 0)) {
                            num1 = num1 - 1;
                            num2 = 3 - num2;
                            int temp = num1;
                            num1 = num2;
                            num2 = temp;
                            if (matrix[num1][num2] == ' ') {
                                flag = false;
                            } else {
                                System.out.println("This cell is occupied! Choose another one!");
                            }
                        } else {
                            System.out.println("Coordinates should be from 1 to 3!");
                        }
                    }
                }
                insertO(num1, num2);
                display();
                move = true;
            }
        }

    }

    private static void insertO(int x, int y) {
        if(matrix[x][y] == ' '){
            matrix[x][y] = 'O';
        }
    }

    private static void insertX(int x, int y) {
        if(matrix[x][y] == ' '){
            matrix[x][y] = 'X';
        }
    }

    private static void display() {
        System.out.println("---------");
        for (int i = 0; i < 3 ; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static boolean analyse() {
        if(doesXWins() && doesOWins() || countMismatch()){
            System.out.println("Impossible");
            return false;
        } else if (doesXWins()) {
            System.out.println("X wins");
            return false;
        } else if (doesOWins()) {
            System.out.println("O wins");
            return false;
        } else if (incompleteGame()) {
            return true;
        } else {
            System.out.println("Draw");
            return false;
        }
    }

    private static boolean doesXWins() {
        boolean flag = false;

        //Checking Rows
        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] ==  'X') {
                    count++;
                }
            }
            if (count == 3) {
                flag = true;
            }
        }
        //Checking Columns
        if (!flag) {
            for (int i = 0; i < 3; i++) {
                int count = 0;
                for (int j = 0; j < 3; j++) {
                    if (matrix[j][i] == 'X') {
                        count++;
                    }
                }
                if (count == 3) {
                    flag = true;
                }
            }
        }
        //Checking Diagonals
        if (!flag) {
            if ('X' == matrix[0][0] && 'X' == matrix[1][1] && 'X' == matrix[2][2]) {
                flag = true;
            }else if ('X' == matrix[0][2] && 'X' == matrix[1][1] && 'X' == matrix[2][0]) {
                return true;
            }
        }
        return flag;
    }

    private static boolean doesOWins() {
        boolean flag = false;

        //Checking Rows
        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == 'O') {
                    count++;
                }
            }
            if (count == 3) {
                flag = true;
            }
        }
        //Checking Columns
        if (!flag) {
            for (int i = 0; i < 3; i++) {
                int count = 0;
                for (int j = 0; j < 3; j++) {
                    if (matrix[j][i] == 'O') {
                        count++;
                    }
                }
                if (count == 3) {
                    flag = true;
                }
            }
        }
        //Checking Diagonals
        if (!flag) {
            if ('O' == matrix[0][0] && 'O' == matrix[1][1] && 'O' == matrix[2][2]) {
                flag = true;
            }else if ('O' == matrix[0][2] && 'O' == matrix[1][1] && 'O' == matrix[2][0]) {
                return true;
            }
        }
        return flag;
    }

    private static boolean countMismatch() {
        int countOfX = 0;
        int countOfO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ('X' == matrix[i][j]) {
                    countOfX++;
                } else if ('O' == matrix[i][j]) {
                    countOfO++;
                }
            }
        }
        return Math.abs(countOfO - countOfX) > 1;
    }

    private static boolean incompleteGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(matrix[i][j] == '_' || matrix[i][j] == ' '){
                    return true;
                }
            }
        }
        return false;
    }

}
