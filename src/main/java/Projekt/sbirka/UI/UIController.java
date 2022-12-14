package Projekt.sbirka.UI;
import Projekt.sbirka.Service.ZnackaService;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UIController implements Initializable {

    @FXML
    private ImageView exitBtn;

    @FXML
    private BorderPane login;

    @FXML
    private Button loginBtn;

    @FXML
    private BorderPane menu;

    @FXML
    private Button menuBtn;

    @FXML
    private ImageView minimalizeBtn;

    @FXML
    private BorderPane pridatModel;

    @FXML
    private Button pridatModelbtn;

    @FXML
    private BorderPane pridatObrazek;

    @FXML
    private BorderPane pridatSbirku;

    @FXML
    private Button pridatSbirkuBtn;

    @FXML
    private BorderPane sbirky;

    @FXML
    private Button sbirkyBtn;

    @FXML
    private BorderPane titleColor;

    @FXML
    private Text titleTxt;

    @FXML
    private Button vybratObrBtn;

    @FXML
    private Button vytvorSbirku;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void Minimalize(MouseEvent event) {
        Stage mini;
        mini = (Stage) minimalizeBtn.getScene().getWindow();
        mini.setIconified(true);
    }

    public void Exit(javafx.scene.input.MouseEvent event) {
        System.exit(40);
    }
    public void Add_users (){
        try {

            JOptionPane.showMessageDialog(null, "Users Add succes");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void handleClick(ActionEvent event) {
        if (event.getSource() == vytvorSbirku) {
            System.out.println("funguje!");
        }
        if (event.getSource() == loginBtn) {
            login.toFront();
            titleTxt.setText("LOGIN");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(37, 109, 133, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == menuBtn) {
            titleTxt.setText("MENU");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(6, 40, 61, 1), CornerRadii.EMPTY, Insets.EMPTY)));
            menu.toFront();
        }
        if (event.getSource() == pridatSbirkuBtn) {
            pridatSbirku.toFront();
            titleTxt.setText("PŘIDAT SBÍRKU");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(48, 71, 94, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == pridatModelbtn) {
            pridatModel.toFront();
            titleTxt.setText("PŘIDAT MODEL");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(49, 85, 143, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == sbirkyBtn) {
            sbirky.toFront();
            titleTxt.setText("SBÍRKY");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(17, 43, 115, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == vybratObrBtn) {
            pridatObrazek.toFront();
            titleTxt.setText("VYBERTE OBRÁZKY");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 43, 155, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }
}