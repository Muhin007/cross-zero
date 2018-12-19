import java.util.Scanner;

public class Menu {
    protected void gameMenu(){
        System.out.println("Выбери пункт меню:");
        System.out.println("1. Начинаем игру.");
        System.out.println("2. Выход.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch ( i ) {
            case 1: {
                Checks.checkWin();
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
}
