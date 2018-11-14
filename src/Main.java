import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final String playerSign = "X";
    private static final String compSign = "O";
    private static final String emptyField = "*";
    private int dimension = sizeField();
    private String[][] field = new String[dimension][dimension];

    public static void main(String[] args) {
        Main main = new Main();
        main.gameMenu();
    }
    private void gameMenu(){
        System.out.println("Выбери пункт меню:");
        System.out.println("1. Начинаем игру.");
        System.out.println("2. Выход.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch ( i ) {
            case 1: {
                checkWin();
                break;
            }
            case 2: {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Было введено неверное значение!");
                System.out.println("Начнем с начала.");
                gameMenu();
            }
        }
    }

    private int sizeField() {
        System.out.println("Введите размер поля.");
        System.out.println("Это должно быть число >= 3.");
        Scanner sizeField = new Scanner(System.in);
        dimension = sizeField.nextInt();
        if (dimension < 3) {
            System.out.println("Больше либо равно 3");
            System.out.println();
            sizeField();
        } else {
            return dimension;
        }
        return dimension;
    }

    private void userShot(String sign) {
        int x, y;
        do {
            System.out.println("Игрок, введи координаты x");
            System.out.println("                        y");
            System.out.println("    в интервале от 1 до " + dimension);
            Scanner sc = new Scanner(System.in);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }
        while (isCellBusy(x, y));
        field[x][y] = sign;
    }

    private void initField() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                field[i][j] = emptyField;
            }
        }
    }

    private void printField() {
        for (int i = 0; i <= dimension; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < dimension; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < dimension; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void compShot() {
        int x = -1, y = -1;
        boolean compWin = false;
        boolean userWin = false;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (!isCellBusy(i, j)) {
                    field[i][j] = compSign;
                    if (checkWin(compSign)) {
                        x = i;
                        y = j;
                        compWin = true;
                    }
                    field[i][j] = emptyField;
                }
            }
        }
        if (!compWin) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (!isCellBusy(i, j)) {
                        field[i][j] = playerSign;
                        if (checkWin(playerSign)) {
                            x = i;
                            y = j;
                            userWin = true;
                        }
                        field[i][j] = emptyField;
                    }
                }
            }
        }
        if (!compWin && !userWin) {
            do {
                Random rnd = new Random();
                x = rnd.nextInt(dimension);
                y = rnd.nextInt(dimension);
            }
            while (isCellBusy(x, y));
        }
        field[x][y] = compSign;
    }

    private boolean isCellBusy(int x, int y) {
        return x >= 0 && y >= 0 && x <= dimension - 1 && y <= dimension - 1 && !field[x][y].equals(emptyField);
    }

    private boolean checkLine(int startX, int startY, int dx, int dy, String sign) {
        for (int i = 0; i < dimension; i++) {
            if (!field[startX + i * dx][startY + i * dy].equals(sign))
                return false;
        }
        return true;
    }

    private boolean checkWin(String sign) {
        for (int i = 0; i < dimension; i++) {
            if (checkLine(i, 0, 0, 1, sign)) return true;
            if (checkLine(0, i, 1, 0, sign)) return true;
        }
        return checkLine(0, 0, 1, 1, sign) || checkLine(0, dimension - 1, 1, -1, sign);
    }

    private void checkWin() {
        int count = 0;
        initField();
        while (true) {
            printField();
            userShot(playerSign);
            count++;
            if (checkWin(playerSign)) {
                System.out.println("Поздравляю! Вы победили!");
                printField();
                returnStart();
                break;
            }
            compShot();
            count++;
            if (checkWin(compSign)) {
                System.out.println("Компьютер победил!");
                printField();
                returnStart();
                break;
            }
            if (count == Math.pow(dimension, 2)) {
                break;
            }
        }
    }

    private void returnStart() {
        System.out.println("Еще раз? y или n");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            checkWin();
        }
        if (answer.equalsIgnoreCase("n")) {
            System.out.println("До свидания!");
            System.exit(0);
        } else {
            System.out.println("Вы ввели не ту букву");
            System.out.println("Нужно y или n");
            returnStart();
        }
    }
}
