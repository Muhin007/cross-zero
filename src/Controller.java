import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    public TextArea crossZero;
    public TextArea talkUser;
    public TextField answerUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menu();
    }

    private void menu() {

        talkUser.setText("Выбери пункт меню:\n 1. Начинаем игру. \n 2. Выход.");
        Scanner sc = new Scanner(answerUser.getText());
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
                talkUser.setText("Было введено неверное значение!");
                talkUser.setText("Начнем с начала.");
                menu();
            }
        }
    }

}
