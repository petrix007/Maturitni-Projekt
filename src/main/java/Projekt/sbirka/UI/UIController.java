package Projekt.sbirka.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
@Component
public class UIController implements Initializable {

    @FXML
    private BorderPane login;

    @FXML
    private Button loginBtn;

    @FXML
    private BorderPane menu;

    @FXML
    private Button menuBtn;
    @FXML
    private BorderPane pridat;

    @FXML
    private Button pridatBtn;


    @FXML
    private Text titleTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleClick(ActionEvent event) {
        if (event.getSource() == loginBtn){
            login.toFront();
            titleTxt.setText("LOGIN");
        }
        if (event.getSource() == menuBtn){
            titleTxt.setText("MENU");
            menu.toFront();
        }
        if (event.getSource() == pridatBtn){
            pridat.toFront();
            titleTxt.setText("PŘIDAT");
        }

    }

}
