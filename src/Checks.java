public class Checks {


    protected static boolean isCellBusy(int x, int y) {
        return x >= 0 && y >= 0 && x <= Fields.dimension - 1 && y <= Fields.dimension - 1 && !Fields.field[x][y].equals(Shots.emptyField);
    }

    private static boolean checkLine(int startX, int startY, int dx, int dy, String sign) {
        for (int i = 0; i < Fields.dimension; i++) {
            if (!Fields.field[startX + i * dx][startY + i * dy].equals(sign))
                return false;
        }
        return true;
    }

    protected static boolean checkWin(String sign) {
        for (int i = 0; i < Fields.dimension; i++) {
            if (checkLine(i, 0, 0, 1, sign)) return true;
            if (checkLine(0, i, 1, 0, sign)) return true;
        }
        return checkLine(0, 0, 1, 1, sign) || checkLine(0, Fields.dimension - 1, 1, -1, sign);
    }

    protected static void checkWin() {
        int count = 0;
        Fields.initField();
        while (true) {
            Fields.printField();
            Shots.userShot(Shots.playerSign);
            count++;
            if (checkWin(Shots.playerSign)) {
                System.out.println("Поздравляю! Вы победили!");
                Fields.printField();
                ReturnStart.returnStart();
                break;
            }
            Shots.compShot();
            count++;
            if (checkWin(Shots.compSign)) {
                System.out.println("Компьютер победил!");
                Fields.printField();
                ReturnStart.returnStart();
                break;
            }
            if (count == Math.pow(Fields.dimension, 2)) {
                break;
            }
        }
    }
}
