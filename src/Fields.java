import java.util.Scanner;

public class Fields {

    protected static int dimension = sizeField();
    protected static String[][] field = new String[dimension][dimension];

    protected static int sizeField() {
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
    protected static void initField() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                field[i][j] = Shots.emptyField;
            }
        }
    }

    protected static void printField() {
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
}
