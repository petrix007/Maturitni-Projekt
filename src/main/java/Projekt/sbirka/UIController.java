package Projekt.sbirka;

import Projekt.sbirka.Entity.Sbirka;
import Projekt.sbirka.Entity.Users;
import Projekt.sbirka.Service.SbirkaService;
import Projekt.sbirka.Service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.util.ResourceBundle;

@Component
public class UIController implements Initializable {
    public boolean loggedIn = false;
    @Autowired
    UsersService usersService;
    @Autowired
    SbirkaService sbirkaService;
    @FXML
    private Button createAcc;

    @FXML
    private ImageView exitBtn;

    @FXML
    private BorderPane loggedUser;

    @FXML
    private BorderPane login;

    @FXML
    private Button loginAcc;

    @FXML
    private Button loginBtn;

    @FXML
    private BorderPane menu;

    @FXML
    private Button menuBtn;

    @FXML
    private ImageView minimalizeBtn;

    @FXML
    private PasswordField passwordField;

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
    private DatePicker sbirkaCreateDate;

    @FXML
    private TextField sbirkaName;

    @FXML
    private BorderPane sbirky;

    @FXML
    private Button sbirkyBtn;

    @FXML
    private BorderPane titleColor;

    @FXML
    private Text titleTxt;

    @FXML
    private Label userLabel;

    @FXML
    private TextField usernameField;

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

    public void Exit(MouseEvent event) {
        System.exit(40);
    }

    @FXML
    private void handleClick(ActionEvent event) {
        if (event.getSource() == vytvorSbirku) {
            System.out.println("funguje!");
        }
        if (event.getSource() == loginBtn & !loggedIn ) {
            login.toFront();
            titleTxt.setText("LOGIN");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(37, 109, 133, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == loginBtn & loggedIn ) {
            loggedUser.toFront();
            titleTxt.setText("USER");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(107, 99, 123, 1), CornerRadii.EMPTY, Insets.EMPTY)));
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
    @FXML
    public void createSbirka(ActionEvent actionEvent) throws ParseException {
        if (actionEvent.getSource() == vytvorSbirku){
            Sbirka sbirka = new Sbirka();
            try{
                String name = sbirkaName.getText();
                sbirka.setPopis(name);
                sbirka.setZalozeno(Date.valueOf(sbirkaCreateDate.getValue()));
                sbirkaService.saveOrUpdate(sbirka);
            }
            catch (Exception e){
                if (sbirkaName.getText() == null){
                    System.out.println(" Upozorneni CHYBA Zadejte prosim nazev sbirky.");
                }
                if (sbirkaCreateDate.getValue() == null){
                    System.out.println(" Upozorneni CHYBA Zadejte prosim datuv vytvoreni sbirky.");
                }
            }
        }
    }
    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void createUser(ActionEvent actionEvent) throws ParseException{
        System.out.println("Spuštěno");
        if (actionEvent.getSource() == createAcc){
            Users users = new Users();
            //try{
                users.setUsername(usernameField.getText());
                System.out.println(usernameField.getText());
                users.setPassword(passwordField.getText());
                System.out.println(passwordField.getText());
                usersService.saveOrUpdate(users);
                System.out.println(asJson(usersService.getAllUsers()));
                logIn();
            //}
            //catch (Exception e){
                //if (usernameField.getText() == null){
                //    System.out.println(" Upozorneni CHYBA Vyplňte prosím pole: USERNAME.");
                //}
                //if (passwordField.getText() == null){
                //    System.out.println(" Upozorneni CHYBA Vyplňte prosím pole: PASSWORD.");
                //}
            //}
        }
    }
    @FXML
    public void loginUser(ActionEvent actionEvent) throws ParseException {
        logIn();
    }
    void logIn(){
        if (UsersID() == null){
            System.out.println("Zadali jste neplatnou přezdívku nebo heslo!");
        } else {
            System.out.println("Login was succesfull");
            System.out.println("Id usera: " + UsersID());
            loginBtn.setText(UsersUsername());
            userLabel.setText("Přezdívka: " + UsersUsername());
            loggedIn = true;
            // DODĚLAT CELKOVÝ POČET SBÍREK / MODELŮ
            loggedUser.toFront();
        }
    }

    public Integer UsersID(){
        for (Users users : usersService.getAllUsers()){
            if (usernameField.getText().equals(users.getUsername()) && passwordField.getText().equals(users.getPassword())){
                return users.getId();
            }
        }
        return null;
    }
    public String UsersUsername() {
        for (Users users : usersService.getAllUsers()){
            if (usernameField.getText().equals(users.getUsername()) && passwordField.getText().equals(users.getPassword())){
                return users.getUsername();
            }
        }
        return null;
    }
    public String UsersPassword() {
        for (Users users : usersService.getAllUsers()){
            if (usernameField.getText().equals(users.getUsername()) && passwordField.getText().equals(users.getPassword())){
                return users.getPassword();
            }
        }
        return null;
    }
    @FXML
    public void logOutUser(ActionEvent actionEvent) throws ParseException{
        login.toFront();
        logOut();
        titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(37, 109, 133, 1), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    void logOut(){
        usernameField.setText(null);
        passwordField.setText(null);
        loggedIn = false;
        titleTxt.setText("LOGIN");
        loginBtn.setText("Přihlásit se");
        System.out.println("Odhlásili jsme vás z účtu");
    }
    /*public void sbirkaCards(){
        /*TridaColumnNazev.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        TridaColumnDatumVytvoreni.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        TridaColumnObor.setCellValueFactory(new PropertyValueFactory<>("obor"));
        TridaColumnDatumZaniku.setCellValueFactory(new PropertyValueFactory<>("until"));

        for(Trida trida: trida_service.getallTrida()){
            TableViewTrida.getItems().add(trida);
        }
    }*/

}