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
import org.springframework.ui.Model;

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
    @FXML
    private ComboBox<String> ModelObrAddCB;

    @FXML
    private ComboBox<String> SbirkaObrAddCB;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ModelyRepository modelyRepository;
    @Autowired
    ParamsService paramsService;
    @Autowired
    ParamsRepository paramsRepository;
    @Autowired
    ModelyService modelyService;
    public int pocetModelu;
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
    private Button nextObrAdd1;
    @FXML
    private Button createAcc;
    @FXML
    private Button createZnacka;
    @FXML
    private Button chooseFile1;
    @FXML
    private ComboBox<String> znackaComboBox;
    @FXML
    private TextField pathShow1;
    @FXML
    private TextArea paramsPopisField;
    @FXML
    private TextField nazevModelsField;
    @FXML
    private TextField paramsHodnotaField;
    @FXML
    private ComboBox<String> sbirkaComboBox;
    @FXML
    private Button pridatObrazekbtn;
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
    private BorderPane pridatObrazekWithout;
    @FXML
    private Button createModelBtn;
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
    private ImageView imageShow1;
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
            SbirkyToComboBox(sbirkaComboBox);
            ZnackyToComboBox(znackaComboBox);
            iRememberSbirkaCBoxName(znackaComboBox, rememberedZnackaName);
            iRememberSbirkaCBoxName(sbirkaComboBox,rememberedSbirkaName );
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
            rememberSbirkaCBox(rememberedSbirkaName, sbirkaComboBox);
            createZnackaPane.toFront();
            titleTxt.setText("VYTVOŘTE ZNAČKU");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 13, 135, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(event.getSource() == modelyMenuBtn){
            modelySP.toFront();
            SbirkyToComboBox(vyberSbirkuCB);
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
        if (event.getSource() == nextObrAdd1){;
            pathShow1.setText(null);
            SbirkaObrAddCB.setValue("Vyberte sbírku");
            ModelObrAddCB.setValue("VyberteModel");
            imageShow.setImage(new Image("Images\\nahled.jpg"));
        }
        if (event.getSource() == chooseFile){
            try {
                chooseFileFromPc(imageShow, pathShow);
            }catch (Exception e){

            }
        }
        if (event.getSource() == chooseFile1){
            try {
                chooseFileFromPc(imageShow1, pathShow1);
            }catch (Exception e){

            }
        }
        if (event.getSource() == pridatObrazekbtn){
            pridatObrazekWithout.toFront();
            SbirkyToComboBox(SbirkaObrAddCB);
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
    public ArrayList<Modely> getModely(){
        ArrayList<Modely> list = new ArrayList<>();
        for (Modely modely : modelyService.getAllModely()){
            if (modely.getSbirka_id().equals(UsersSorted(usernameField.getText(), passwordField.getText()).getId())){
                System.out.println(asJson(modely.getSbirka_id().getId()));
                System.out.println(UsersSorted(usernameField.getText(), passwordField.getText()).getId());
                System.out.println(asJson(modely));
                list.add(modely);
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
        SbirkyToComboBox(sbirkaComboBox);
        ZnackyToComboBox(znackaComboBox);
        iRememberSbirkaCBoxName(sbirkaComboBox, rememberedSbirkaName);
        iRememberSbirkaCBoxName(znackaComboBox, rememberedZnackaName);

    }
    int modelId;
    @FXML
    public void createModel(ActionEvent actionEvent) throws ParseException{
        Modely modely = new Modely();


        SbirkasSorted();
        ZnackasSorted();
        ModelySorted();
        modely.setNazev(nazevModelsField.getText());
        modely.setSbirka_id(findSbirkaFromComboBoxValue());
        modely.setZnacka_id(findZnackaFromComboBoxValue());


        modelId = modely.getId();

        modelyService.saveOrUpdate(modely);

        System.out.println("Model byl úspěšně vytvořen");

        Params params = new Params();

        SbirkasSorted();
        ZnackasSorted();
        ModelySorted();

        params.setPopis(paramsPopisField.getText());
        params.setHodnota(paramsHodnotaField.getText());
        params.setModel_param(findModelyFromComboBoxValue());
        paramsService.saveOrUpdate(params);
        System.out.println(asJson(params));

        //nazevParamField.setText(null);

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
            if (znackaComboBox.getSelectionModel().selectedItemProperty().getValue().equals(znacka.getPopis())) {
                return znacka;
            }
        }
        return null;
    }
    public Modely findModelyFromComboBoxValue(){
        for (Modely modely : modelyService.getAllModely()){
            System.out.println(asJson(modely));
            System.out.println(modely.getNazev());
            System.out.println(asJson(modelyService.getAllModely()));
            if (nazevModelsField.getText().equals(modely.getNazev()) /*&& znackaComboBox.getValue().equals(modely.getZnacka_id()) && sbirkaComboBox.getValue().equals(modely.getSbirka_id())*/) {

                return modely;
            }
        }
        return null;
    }
    String obrazekPath = null;
    @FXML
    public void chooseFileFromPc(ImageView imageHolder, TextField pathShow) throws  ParseException{
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

                imageHolder.setImage(new Image(fileLocation));
                pathShow.setText(piecePath);
                obrazekPath = fileLocation;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void createObrazek(ActionEvent actionEvent) throws ParseException{
        Pics pics = new Pics();
        pics.setModel_pic(GetModelById(GetIDbyName_Obor(nazevModelsField.getText())));
        pics.setObr(obrazekPath);
        pics.setPopis(pathShow.getText());
        picsService.saveOrUpdate(pics);
        System.out.println(asJson(pics));
    }
    public void createObrazekAlone(ActionEvent actionEvent) throws ParseException{
        Pics pics = new Pics();
        pics.setModel_pic(GetModelById(GetIDbyName_Obor(ModelObrAddCB.getValue())));
        System.out.println(ModelObrAddCB.getValue());
        pics.setObr(obrazekPath);
        System.out.println(obrazekPath);
        pics.setPopis(pathShow1.getText());
        System.out.println(pathShow1.getText());
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
    public void rememberSbirkaCBox(int rememberName, ComboBox cb){
        rememberName =  cb.getSelectionModel().getSelectedIndex();
        System.out.println(asJson(rememberName));
    }

    public void iRememberSbirkaCBoxName(ComboBox cb, int rememberName){
        cb.getSelectionModel().select(rememberName);
        System.out.println(asJson(rememberName));
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
                    SbirkyToComboBox(vyberSbirkuCB);
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
            System.out.println(modely.getNazev());
            if (modely.getNazev().equals(nazev)) {
                System.out.println("Mám to!");
                return modely.getId();
            }
        }
        return 0;
    }

    public Modely GetModelById(int id) {
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
    public Modely ModelySorted(){
        for (Modely modely : modelyService.getAllModely()){
            if (paramsPopisField.getText().equals(modely.getNazev()) && znackaComboBox.getValue().equals(modely.getZnacka_id()) && sbirkaComboBox.getValue().equals(modely.getSbirka_id())){
                return modely;
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
            modelCountLabel.setText("Počet modelů: " + getModely().size());
            sbirkaCountLabel.setText("Počet sbírek: " + getSbirka().size());
            loggedIn = true;
            loggedUser.toFront();
            sbirkyBtn.setDisable(false);
            menuBtn.setDisable(false);
            pridatModelbtn.setDisable(false);
            pridatSbirkuBtn.setDisable(false);
            modelyMenuBtn.setDisable(false);
            pridatObrazekbtn.setDisable(false);
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
    public ArrayList<Params> ParamsAll(){
        ArrayList<Params> paramsList = new ArrayList<>();
        for (Sbirka sbirka : sbirkaService.getAllSbirka()){
            if(SbirkaObrAddCB.getValue() == sbirka.getPopis()){
                for(Modely modely : modelyService.getAllModely()){
                    if(modely.getSbirka_id() == sbirka){
                        System.out.println(asJson(modely.getSbirka_id()));
                        System.out.println(asJson(sbirka));
                        for(Params params : paramsService.getAllParams()){
                            if(params.getModel_param() == modely){
                                paramsList.add(params);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(asJson(paramsList));
        return paramsList;
    }
    public void ZnackyToComboBox(ComboBox znackaComboBox){
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
    public ArrayList<Modely> ModelyAll(){
        ArrayList<Modely> modelyList = new ArrayList<>();
        for (Modely modely : modelyService.getAllModely()){
            System.out.println(asJson(modelyService.getAllModely()));
            System.out.println(modely.getPopisSbirka());
            System.out.println(SbirkaObrAddCB.getValue());
            if (modely.getPopisSbirka().equals(SbirkaObrAddCB.getValue())){
                modelyList.add(modely);
            }
        }
        System.out.println(asJson(modelyList));
        return modelyList;
    }
    public void ModelyToComboBox(){
        ModelObrAddCB.getItems().clear();
        for (Modely modely : ModelyAll()){
            ModelObrAddCB.getItems().add(modely.getNazev());
        }
    }
    public void ParamsToComboBox(){
        ModelObrAddCB.getItems().clear();
        for (Params params : ParamsAll()){
            ModelObrAddCB.getItems().add(params.getPopis());
        }
    }
    public void SbirkyToComboBox(ComboBox sbirkaComboBox){
        sbirkaComboBox.getItems().clear();
        for (Sbirka sbirka : getSbirka()){
            sbirkaComboBox.getItems().add(sbirka.getPopis());
        }
    }
}