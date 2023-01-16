package Projekt.sbirka;

import Projekt.sbirka.Entity.*;
import Projekt.sbirka.Repository.*;
import Projekt.sbirka.Service.*;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
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
    ModelyRepository modelyRepository;
    @Autowired
    ModelyService modelyService;
    @Autowired
    SbirkaService sbirkaService;
    @Autowired
    ZnackaService znackaService;
    @Autowired
    SbirkaRepository sbirkaRepository;
    @Autowired
    PicsRepository picsRepository;
    @Autowired
    PicsService picsService;
    @FXML
    private ComboBox<String> vyberSbirkuCB;
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
    private Button nextObrAdd;
    @FXML
    private Button createModelBtn;
    @FXML
    private TextField nazevModelField;
    @FXML
    private TextArea paramsPopisField;
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
    private ScrollPane modelySP;
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
    private TextField searchModelField;
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
    private Button modelyMenuBtn;
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
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(30, 0, 255, 1), CornerRadii.EMPTY, Insets.EMPTY)));
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
        if(event.getSource() == modelyMenuBtn){
            modelySP.toFront();
            SbirkyToComboBox2();
            titleTxt.setText("MODELY");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(0, 112, 150, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == potvrditBtn){
            pridatModel.toFront();
        }
        if (event.getSource() == nextObrAdd){;
            pathShow.setText(null);
            imageShow.setImage(new Image("Images\\nahled.jpg"));
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
                sbirka.setUsers_id(UsersSorted(usernameField.getText(), passwordField.getText()));
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
            if (sbirka.getUsers_id().getId() == (UsersSorted(usernameField.getText(), passwordField.getText()).getId())){
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
            znacka.setUser_id(UsersSorted(usernameField.getText(), passwordField.getText()).getId());
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
    int modelId;
    @FXML
    public void createModel(ActionEvent actionEvent) throws ParseException{
        Modely modely = new Modely();

        SbirkasSorted();
        ZnackasSorted();

        modely.setPopis(nazevModelField.getText());
        modely.setSbirka_id(findSbirkaFromComboBoxValue());
        modely.setZnacka_id(findZnackaFromComboBoxValue());

        modelId = modely.getId();

        modelyService.saveOrUpdate(modely);

        System.out.println("Model byl úspěšně vytvořen");

        //nazevModelField.setText(null);

        System.out.println(asJson(sbirkaComboBox.getSelectionModel().selectedItemProperty().getValue()));
        System.out.println(asJson(znackaComboBox.getSelectionModel().selectedItemProperty().getValue()));

        wannaAddObrAlert();
    }
    public Sbirka findSbirkaFromComboBoxValue(){
        for (Sbirka sbirka : sbirkaService.getAllSbirka()){
            if (sbirkaComboBox.getSelectionModel().selectedItemProperty().getValue().equals(sbirka.getPopis())){
                return sbirka;
            }
        }
        return null;
    }
    public Znacka findZnackaFromComboBoxValue(){
        for (Znacka znacka : znackaService.getAllZnacka()){
            if (znackaComboBox.getSelectionModel().selectedItemProperty().getValue().equals(znacka.getPopis())){
                return znacka;
            }
        }
        return null;
    }
    String obrazekPath = null;
    @FXML
    public void chooseFileFromPc() throws  ParseException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vyberte obrázek: ");
        Window window = new Stage();
        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null) {
            try {
                String appData = System.getenv("APPDATA");
                File appFolder = new File(appData);
                File copiedFile = new File(appFolder, selectedFile.getName());
                FileUtils.copyFile(selectedFile, copiedFile);
                String fileLocation = copiedFile.getAbsolutePath();
                String piecePath = fileLocation.replace(fileLocation, "");
                System.out.println("Soubor byl uložen do: " + fileLocation);

                imageShow.setImage(new Image(fileLocation));
                pathShow.setText(piecePath);
                obrazekPath = fileLocation;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void createObrazek(ActionEvent actionEvent) throws ParseException{
        Pics pics = new Pics();

        pics.setModel_pic(GetOborByID(GetIDbyName_Obor(nazevModelField.getText())));
        pics.setObr(obrazekPath);
        //System.out.println(obrazekPath);
        pics.setPopis(pathShow.getText());
        //System.out.println(pathShow.getText());
        picsService.saveOrUpdate(pics);
        System.out.println(asJson(pics));
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
    public void wannaAddObrAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("OKNO");
        alert.setHeaderText("Chcete přidat obrázek k tomuto modelu?");
        alert.setContentText("Prosím, vyberte odpověď.");

        ButtonType buttonYes = new ButtonType("ANO");
        ButtonType buttonNo = new ButtonType("NE");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonYes){
            pridatObrazek.toFront();
        }else{
            pridatModel.toFront();
            //nazevModelField.setText(null);
        }
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
        int numberOfColumns = 3;
        GridPane grid = new GridPane();
        grid.setTranslateX(50);
        grid.setTranslateY(50);
        grid.setLayoutY(50);
        grid.setLayoutX(50);
        grid.setHgap(20);
        grid.setVgap(20);


        int counter = 0;
        for (Sbirka sbirka: getSbirka()) {
            ImageView imageView = new ImageView(new Image("Images/sbirkaFolderIcon.png"));
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);

            Label label = new Label(sbirka.getPopis());
            label.setTextAlignment(TextAlignment.CENTER);
            VBox vbox = new VBox(imageView, label);
            vbox.setAlignment(Pos.CENTER);
            grid.add(vbox, counter % numberOfColumns, counter / numberOfColumns);
            counter++;

            imageView.setId(label.getText());
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    modelySP.toFront();
                    SbirkyToComboBox2();
                    vyberSbirkuCB.setValue(imageView.getId());
                    System.out.println(imageView.getId());
                }
            });
        }

        sbirkasSP.setContent(grid);
        }
    public Users UsersSorted(String username, String password){
        for (Users users : usersService.getAllUsers()){
            if (username.equals(users.getUsername()) && password.equals(users.getPassword())){
                return users;
            }
        }
        return null;
    }
    public int GetIDbyName_Obor(String nazev) {
        for (Modely modely : modelyService.getAllModely()) {
            System.out.println(asJson(modelyService.getAllModely()));
            System.out.println(nazev);
            System.out.println(modely.getPopis());
            if (modely.getPopis().equals(nazev)) {
                System.out.println("Mám to!");
                return modely.getId();
            }
        }
        return 0;
    }

    public Modely GetOborByID(int id) {
        for (Modely modely : modelyService.getAllModely()) {
            if (modely.getId() == id) {
                return modely;
            }
        }
        return null;
    }
    public Sbirka SbirkasSorted(){
        for (Sbirka sbirka : sbirkaService.getAllSbirka()){
            if (sbirkaComboBox.getSelectionModel().equals(sbirka.getPopis())){
                return sbirka;
            }
        }
        return null;
    }
    public Znacka ZnackasSorted(){
        for (Znacka znacka : znackaService.getAllZnacka()){
            if (znackaComboBox.getSelectionModel().equals(znacka.getPopis())){
                return znacka;
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
        if (UsersSorted(usernameField.getText(), passwordField.getText()) == null){
            headerText = "Zadali jste špatnou přezdívku nebo heslo!";
            alerted();
        } else {
            System.out.println("Login was succesfull");
            System.out.println("Id usera: " + asJson(UsersSorted(usernameField.getText(), passwordField.getText()).getId()));
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
            modelyMenuBtn.setDisable(false);
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
            if (znacka.getUser_id() == UsersSorted(usernameField.getText(), passwordField.getText()).getId()){
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
            if (getSbirka().equals(UsersSorted(usernameField.getText(), passwordField.getText()).getId())){
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
    public void SbirkyToComboBox2(){
        vyberSbirkuCB.getItems().clear();
        for (Sbirka sbirka : getSbirka()){
            vyberSbirkuCB.getItems().add(sbirka.getPopis());
        }
    }
}