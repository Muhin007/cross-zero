import java.util.Random;
import java.util.Scanner;

public class Shots {
    protected static final String playerSign = "X";
    protected static final String compSign = "O";
    protected static final String emptyField = "*";

    protected static void userShot(String sign) {
        int x, y;
        do {
            System.out.println("Игрок, введи координаты x");
            System.out.println("                        y");
            System.out.println("    в интервале от 1 до " + Fields.dimension);
            Scanner sc = new Scanner(System.in);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }
        while (Checks.isCellBusy(x, y));
        Fields.field[x][y] = sign;
    }
    protected static void compShot() {
        int x = -1, y = -1;
        boolean compWin = false;
        boolean userWin = false;
        for (int i = 0; i < Fields.dimension; i++) {
            for (int j = 0; j < Fields.dimension; j++) {
                if (!Checks.isCellBusy(i, j)) {
                    Fields.field[i][j] = compSign;
                    if (Checks.checkWin(compSign)) {
                        x = i;
                        y = j;
                        compWin = true;
                    }
                    Fields.field[i][j] = emptyField;
                }
            }
        }
        if (!compWin) {
            for (int i = 0; i < Fields.dimension; i++) {
                for (int j = 0; j < Fields.dimension; j++) {
                    if (!Checks.isCellBusy(i, j)) {
                        Fields.field[i][j] = playerSign;
                        if (Checks.checkWin(playerSign)) {
                            x = i;
                            y = j;
                            userWin = true;
                        }
                        Fields.field[i][j] = emptyField;
                    }
                }
            }
        }
        if (!compWin && !userWin) {
            do {
                Random rnd = new Random();
                x = rnd.nextInt(Fields.dimension);
                y = rnd.nextInt(Fields.dimension);
            }
            while (Checks.isCellBusy(x, y));
        }
        Fields.field[x][y] = compSign;
    }

}
