import java.util.Scanner;

public class ReturnStart {

    protected static void returnStart() {
        System.out.println("Еще раз? y или n");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            Checks.checkWin();
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
