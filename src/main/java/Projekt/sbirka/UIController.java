package Projekt.sbirka;
import Projekt.sbirka.Entity.Sbirka;
import Projekt.sbirka.Entity.Users;
import Projekt.sbirka.Entity.Znacka;
import Projekt.sbirka.Repository.SbirkaRepository;
import Projekt.sbirka.Repository.UsersRepository;
import Projekt.sbirka.Repository.ZnackaRepository;
import Projekt.sbirka.Service.SbirkaService;
import Projekt.sbirka.Service.UsersService;

import Projekt.sbirka.Service.ZnackaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

@Component
public class UIController implements Initializable {
    boolean loggedIn = false;
    @Autowired
    UsersService usersService;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    SbirkaService sbirkaService;
    @Autowired
    ZnackaService znackaService;
    @Autowired
    SbirkaRepository sbirkaRepository;
    @FXML
    private Button createAcc;

    @FXML
    private Button createZnacka;
    @FXML
    private ComboBox<String> znackaComboBox;
    @FXML
    private ComboBox<String> sbirkaComboBox;
    @FXML
    private BorderPane createZnackaPane;
    @FXML
    private Button createZnackaToPaneBtn;
    @FXML
    private BorderPane menuColumn;
    @FXML
    private ImageView exitBtn;

    @FXML
    private Button logOutBtn;

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
    private Label modelCountLabel;

    @FXML
    private BorderPane modely;

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
    private Label sbirkaCountLabel;

    @FXML
    private DatePicker sbirkaCreateDate;

    @FXML
    private TextField sbirkaName;

    @FXML
    private Pane sbirkas;

    @FXML
    private ScrollPane sbirkasSP;

    @FXML
    private Button sbirkyBtn;

    @FXML
    private Button searchModelBtn;

    @FXML
    private TextField searchModelsField;

    @FXML
    private Button searchSbirkaBtn;

    @FXML
    private TextField searchSbirkaField;

    @FXML
    private BorderPane titleColor;

    @FXML
    private Text titleTxt;

    @FXML
    private TextField usernameField;

    @FXML
    private ImageView backFromCreateZnackaPane;
    @FXML
    private ImageView backFromPridatObrazek;
    @FXML
    private Label usernameLabel;

    @FXML
    private Button vybratObrBtn;

    @FXML
    private Button vytvorSbirku;

    @FXML
    private TextField znackaNameField;
    @FXML
    private TextField pathShow;

    @FXML
    private Button potvrditBtn;
    @FXML
    private ImageView imageShow;
    @FXML
    private Button chooseFile;
    @FXML
    private CheckBox zustanPrihlasenCheckBox;
    public int rememberedZnackaName;
    public int rememberedSbirkaName;
    public int startX = 80;
    public int startY = 60;
    public int Width = 120;
    public int Height = 100;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Preferences prefs = Preferences.userNodeForPackage(UIController.class);
        String username = prefs.get("username", "");
        String password = prefs.get("password", "");
        usernameField.setText(username);
        passwordField.setText(password);
    }

    @FXML
    public void Minimalize(MouseEvent event) {
        Stage mini;
        mini = (Stage) minimalizeBtn.getScene().getWindow();
        mini.setIconified(true);
    }

    public void Exit(javafx.scene.input.MouseEvent event) {
        if (zustanPrihlasenCheckBox.isSelected() == true){
            Preferences prefs = Preferences.userNodeForPackage(UIController.class);
            prefs.put("username", usernameField.getText());
            prefs.put("password", passwordField.getText());
        }else {
            Preferences prefs = Preferences.userNodeForPackage(UIController.class);
            prefs.remove("username");
            prefs.remove("password");
        }
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
            SbirkyToComboBox();
            ZnackyToComboBox();
            iRememberZnackaCBoxName();
            iRememberSbirkaCBoxName();
            titleTxt.setText("PŘIDAT MODEL");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(49, 85, 143, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == sbirkyBtn) {
            sbirkasSP.toFront();
            titleTxt.setText("SBÍRKY");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(17, 43, 115, 1), CornerRadii.EMPTY, Insets.EMPTY)));
            DrawSbirkas();
        }
        if (event.getSource() == vybratObrBtn) {
            pridatObrazek.toFront();
            titleTxt.setText("VYBERTE OBRÁZKY");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 43, 155, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == createZnackaToPaneBtn){
            rememberSbirkaCBox();
            rememberZnackaCBox();
            createZnackaPane.toFront();
            titleTxt.setText("VYTVOŘTE ZNAČKU");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 13, 135, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }
    @FXML
    public void ibackFromCreateZnackaPane() throws  ParseException{
        pridatModel.toFront();
        titleTxt.setText("VYTVOŘTE ZNAČKU");
        titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 13, 135, 1), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML
    public void ibackFromPridatObrazek() throws  ParseException{
        pridatModel.toFront();
        titleTxt.setText("VYBERTE OBRÁZKY");
        titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 43, 155, 1), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML
    public void createSbirka(ActionEvent actionEvent) throws ParseException {
        if (actionEvent.getSource() == vytvorSbirku){
            Sbirka sbirka = new Sbirka();
            //{try{
                sbirka.setUsers_id(UsersSorted());
                String name = sbirkaName.getText();
                sbirka.setPopis(name);
                sbirka.setZalozeno(String.valueOf(sbirkaCreateDate.getValue()));
                System.out.println(asJson(sbirka));
                sbirkaService.saveOrUpdate(sbirka);
                System.out.println(asJson(sbirkaService.getAllSbirka()));
            }
            //catch (Exception e){
                if (sbirkaName.getText() == null){
                    System.out.println(" Upozorneni CHYBA Zadejte prosim nazev sbirky.");
                }
                if (sbirkaCreateDate.getValue() == null){
                    System.out.println(" Upozorneni CHYBA Zadejte prosim datuv vytvoreni sbirky.");
              //  }
           // }
        }
    }
    public ArrayList<Sbirka> getSbirka(){
        ArrayList<Sbirka> list = new ArrayList<>();
        for (Sbirka sbirka : sbirkaService.getAllSbirka()){
            if (sbirka.getUsers_id().getId() == (UsersSorted().getId())){
                list.add(sbirka);
            }
        }
        System.out.println(asJson(list));
        return list;
    }
    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void createZnacka(ActionEvent actionEvent) throws ParseException{
        if (actionEvent.getSource() == createZnacka){
            Znacka znacka = new Znacka();

            znacka.setPopis(znackaNameField.getText());
            znacka.setUser_id(UsersSorted().getId());
            znackaService.saveOrUpdate(znacka);
            System.out.println("Značka byla úspěšně vytvořena");
            pridatModel.toFront();
            znackaNameField.setText(null);

        }
        SbirkyToComboBox();
        ZnackyToComboBox();
        iRememberSbirkaCBoxName();
        iRememberZnackaCBoxName();

    }
    @FXML
    public void chooseFileFromPc() throws  ParseException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vyberte obrázek: ");
        Window window = new Stage();
        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null) {
            try {
                File appFolder = new File("C:\\Users\\petrm\\Downloads\\sbirka\\sbirka\\src\\main\\resources\\pics");
                File copiedFile = new File(appFolder, selectedFile.getName());
                FileUtils.copyFile(selectedFile, copiedFile);
                String fileLocation = copiedFile.getAbsolutePath();
                System.out.println("Soubor byl uložen do: " + fileLocation);
                String pieceFile = fileLocation.replace("C:\\Users\\petrm\\Downloads\\sbirka\\sbirka\\src\\main\\resources\\pics\\", "");

                pathShow.setText(pieceFile);
                imageShow.setImage(new Image(fileLocation));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public String headerText = "header";
    public  void alerted(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chyba!");
        alert.setHeaderText(headerText);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
    @FXML
    public void createUser(ActionEvent actionEvent) throws ParseException{
        System.out.println("Spuštěno");
        if (actionEvent.getSource() == createAcc){
            Users users = new Users();
            try{
            users.setUsername(usernameField.getText());
            System.out.println(usernameField.getText());
            users.setPassword(passwordField.getText());
            System.out.println(passwordField.getText());
            usersService.saveOrUpdate(users);
            System.out.println(asJson(usersService.getAllUsers()));
            logIn();
            }
            catch (Exception e){
                headerText = "Prosím vyplňte všechna pole!";
                alerted();
                headerText = "";
            }
        }
    }

    public void rememberSbirkaCBox(){
        rememberedSbirkaName =  sbirkaComboBox.getSelectionModel().getSelectedIndex();
        System.out.println(asJson(rememberedSbirkaName));
    }
    public void iRememberSbirkaCBoxName(){
        sbirkaComboBox.getSelectionModel().select(rememberedSbirkaName);
        System.out.println(asJson(rememberedSbirkaName));
    }

    public void rememberZnackaCBox(){
        rememberedZnackaName = znackaComboBox.getSelectionModel().getSelectedIndex();
        System.out.println(asJson(rememberedZnackaName));
    }
    public void iRememberZnackaCBoxName(){
        znackaComboBox.getSelectionModel().select(rememberedZnackaName);
        System.out.println(asJson(rememberedZnackaName));
    }
    @Autowired
    private ZnackaRepository znackaRepository;

    public void  DrawSbirkas(){
        sbirkas.getChildren().clear();
        startX = 80;
        startY = 60;
        for (Sbirka sbirka: getSbirka()){
            if (startX >= 530){
                startX = 80;
                startY += 150;
            }
            TitledPane titledPane = new TitledPane();
            titledPane.setText("Název: " + sbirka.getPopis());
            titledPane.setContent(new Label("Založení: " + sbirka.getZalozeno()));
            titledPane.setTranslateX(startX);
            titledPane.setTranslateY(startY);
            titledPane.setPrefHeight(Height);
            titledPane.setPrefWidth(Width);
            titledPane.setCollapsible(false);
            sbirkas.getChildren().add(titledPane);
            startX += 150;
            sbirkas.setPrefHeight(startY + 150);
        }
        System.out.println(startX);
    }
    public Users UsersSorted(){
        for (Users users : usersService.getAllUsers()){
            if (usernameField.getText().equals(users.getUsername()) && passwordField.getText().equals(users.getPassword())){
                return users;
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
    public void loginUser(ActionEvent actionEvent) throws ParseException {
        logIn();
    }
    void logIn(){
        if (UsersSorted() == null){
            headerText = "Zadali jste špatnou přezdívku nebo heslo!";
            alerted();
        } else {
            System.out.println("Login was succesfull");
            System.out.println("Id usera: " + asJson(UsersSorted().getId()));
            loginBtn.setText(UsersUsername());
            usernameLabel.setText("Přezdívka: " + UsersUsername());
            modelCountLabel.setText("Počet modelů: getModel().size()");
            sbirkaCountLabel.setText("Počet sbírek: " + getSbirka().size());
            loggedIn = true;
            loggedUser.toFront();
            sbirkyBtn.setDisable(false);
            menuBtn.setDisable(false);
            pridatModelbtn.setDisable(false);
            pridatSbirkuBtn.setDisable(false);
        }
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
        sbirkyBtn.setDisable(true);
        menuBtn.setDisable(true);
        pridatModelbtn.setDisable(true);
        pridatSbirkuBtn.setDisable(true);
        titleTxt.setText("LOGIN");
        loginBtn.setText("Přihlásit se");
        headerText = "Odhlásili jsme vás z účtu!";
        alerted();
    }

    public ArrayList<Znacka> ZnackyAll(){
        ArrayList<Znacka> znackyList = new ArrayList<>();
        for (Znacka znacka : znackaService.getAllZnacka()){
            if (znacka.getUser_id() == UsersSorted().getId()){
                znackyList.add(znacka);
            }
        }
        System.out.println(asJson(znackyList));
        return znackyList;
    }
    public void ZnackyToComboBox(){
        znackaComboBox.getItems().clear();
        for (Znacka znacka : ZnackyAll()){
            znackaComboBox.getItems().add(znacka.getPopis());
        }
    }
    public ArrayList<Sbirka> SbirkyAll(){
        ArrayList<Sbirka> sbirkyList = new ArrayList<>();
        for (Sbirka sbirka : sbirkaService.getAllSbirka()){
            if (getSbirka().equals(UsersSorted().getId())){
                sbirkyList.add(sbirka);
            }
        }
        System.out.println(asJson(sbirkyList));
        return sbirkyList;
    }
    public void SbirkyToComboBox(){
        sbirkaComboBox.getItems().clear();
        for (Sbirka sbirka : getSbirka()){
            sbirkaComboBox.getItems().add(sbirka.getPopis());
        }
    }
}