package Projekt.sbirka;

import Projekt.sbirka.Entity.*;
import Projekt.sbirka.Repository.*;
import Projekt.sbirka.Service.*;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import org.apache.catalina.User;
import org.apache.commons.io.FileUtils;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private ImageView pridatModelMenuBtn;
    @FXML
    private ImageView pridatObrazekMenuBtn;
    @FXML
    private ImageView topLeftLogoImg;
    @FXML
    private ImageView pridatSbirkuMenuBtn;
    @FXML
    private BorderPane progressBP;
    @FXML
    private ProgressBar progressIndicator;
    @FXML
    private ImageView profilMenuBtn;
    @FXML
    private ImageView sbirkyMenuBtn;
    @FXML
    private ImageView modelyShowMenuBtn;
    @FXML
    private ComboBox<String> SbirkaObrAddCB;
    @FXML
    private ImageView maximalizeBtn;
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
    @FXML
    private Pane sbirkas1;
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
    private ImageView backFromDetail;
    @FXML
    private Button nextObrAdd1;
    @FXML
    private BorderPane modelyDetail;
    @FXML
    private ImageView modelDetailImage;
    @FXML
    private Label modelNazevDetail;
    @FXML
    private TextField cenaDetailTxtB;
    @FXML
    private TextArea modelPopisArea;
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
    private Pane sbirkas11 = new Pane();
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
    private BorderPane searchBarModels;
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
        try {
            Preferences prefs = Preferences.userNodeForPackage(UIController.class);
            String username = prefs.get("username", "");
            String password = prefs.get("password", "");
            usernameField.setText(username);
            passwordField.setText(password);
            zustanPrihlasenCheckBox.setSelected(true);
        } catch (Exception e) {
        }
    }

    @FXML
    public void Minimalize(MouseEvent event) {
        Stage mini;
        mini = (Stage) minimalizeBtn.getScene().getWindow();
        mini.setIconified(true);
    }

    public boolean max = false;

    @FXML
    public void LogoClick(MouseEvent event) {
        titleTxt.setText("MENU");
        titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(6, 40, 61, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        menu.toFront();
    }

    @FXML
    public void Maximalize(MouseEvent event) {
        if (max) {
            maximalizeBtn.setImage(new Image("Images/fullscreen1.png"));
            Scale scale = new Scale(1, 1);
            scale.setPivotX(0);
            scale.setPivotY(0);
            sbirkyMenuBtn.getTransforms().setAll(scale);
            modelyShowMenuBtn.getTransforms().setAll(scale);
            pridatObrazekMenuBtn.getTransforms().setAll(scale);
            profilMenuBtn.getTransforms().setAll(scale);
            pridatModelMenuBtn.getTransforms().setAll(scale);
            pridatSbirkuMenuBtn.getTransforms().setAll(scale);
        } else {
            maximalizeBtn.setImage(new Image("Images/fullscreenoff.png"));
            Scale scale = new Scale(1.6, 1.6);
            scale.setPivotX(170);
            scale.setPivotY(120);
            sbirkyMenuBtn.getTransforms().setAll(scale);
            modelyShowMenuBtn.getTransforms().setAll(scale);
            pridatObrazekMenuBtn.getTransforms().setAll(scale);
            profilMenuBtn.getTransforms().setAll(scale);
            pridatModelMenuBtn.getTransforms().setAll(scale);
            pridatSbirkuMenuBtn.getTransforms().setAll(scale);
        }
        max = !max;
        Stage maxi;
        maxi = (Stage) maximalizeBtn.getScene().getWindow();
        maxi.setFullScreen(max);
        try {
            DrawSbirkas();
            DrawModels();
            ModelyChoose();

        } catch (Exception e) {

        }
    }

    public void Exit(javafx.scene.input.MouseEvent event) {
        if (zustanPrihlasenCheckBox.isSelected() == true) {
            try {
                Preferences prefs = Preferences.userNodeForPackage(UIController.class);
                prefs.put("username", usernameField.getText());
                prefs.put("password", passwordField.getText());
            } catch (Exception e) {
            }
        } else {
            try {
                Preferences prefs = Preferences.userNodeForPackage(UIController.class);
                prefs.remove("username");
                prefs.remove("password");
            } catch (Exception e) {
            }
        }
        System.exit(40);
    }

    @FXML
    private void handleClick(ActionEvent event) {
        if (event.getSource() == loginBtn & !loggedIn) {
            login.toFront();
            titleTxt.setText("LOGIN");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(37, 109, 133, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == loginBtn & loggedIn) {
            loggedUser.toFront();
            titleTxt.setText("USER");
            modelCountLabel.setText("Počet modelů: " + getModely().size());
            sbirkaCountLabel.setText("Počet sbírek: " + getSbirka().size());
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(30, 0, 255, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == menuBtn) {
            titleTxt.setText("MENU");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(6, 40, 61, 1), CornerRadii.EMPTY, Insets.EMPTY)));
            menu.toFront();

        }
        if (event.getSource() == pridatSbirkuBtn) {
            pridatSbirku.toFront();
            LocalDate currentDate = LocalDate.now();
            sbirkaCreateDate.setValue(currentDate);
            titleTxt.setText("VYTVOŘTE SBÍRKU");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(48, 71, 94, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == pridatModelbtn) {
            pridatModel.toFront();
            SbirkyToComboBox(sbirkaComboBox);
            ZnackyToComboBox(znackaComboBox);
            iRememberSbirkaCBoxName(znackaComboBox, rememberedZnackaName);
            iRememberSbirkaCBoxName(sbirkaComboBox, rememberedSbirkaName);
            titleTxt.setText("VYTVOŘTE MODEL");
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
        if (event.getSource() == createZnackaToPaneBtn) {
            rememberSbirkaCBox(rememberedSbirkaName, sbirkaComboBox);
            createZnackaPane.toFront();
            titleTxt.setText("VYTVOŘTE ZNAČKU");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 13, 135, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == modelyMenuBtn) {
            searchBarModels.toFront();
            //ProgressIndicator();
            DrawModels();

            SbirkyToComboBox(vyberSbirkuCB);
            titleTxt.setText("MODELY");
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(0, 112, 150, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == potvrditBtn) {
            pridatModel.toFront();
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 43, 155, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (event.getSource() == nextObrAdd) {
            ;
            pathShow.setText(null);
            imageShow.setImage(new Image("Images\\nahled.jpg"));
        }
        if (event.getSource() == nextObrAdd1) {
            ;
            pathShow1.setText(null);
            SbirkaObrAddCB.setValue("Vyberte sbírku");
            ModelObrAddCB.setValue("VyberteModel");
            imageShow.setImage(new Image("Images\\nahled.jpg"));
        }
        if (event.getSource() == chooseFile) {
            try {
                chooseFileFromPc(imageShow, pathShow);
            } catch (Exception e) {

            }
        }
        if (event.getSource() == chooseFile1) {
            try {
                chooseFileFromPc(imageShow1, pathShow1);
            } catch (Exception e) {

            }
        }
        if (event.getSource() == pridatObrazekbtn) {
            pridatObrazekWithout.toFront();
            SbirkyToComboBox(SbirkaObrAddCB);
        }
    }

    public void pridatObrEditMode() throws ParseException {
        pridatObrazekWithout.toFront();
        for (Modely modely : modelyService.getAllModely()) {
            System.out.println(modely.getNazev());
            System.out.println(modelNazevDetail.getText());
            if (modelNazevDetail.getText().equals(modely.getNazev())) {
                System.out.println(modely.getNazev());
                ModelObrAddCB.setValue(modelNazevDetail.getText());
                SbirkaObrAddCB.setValue(modely.getSbirka_id().getPopis());
            }
        }
    }

    public void profilToFront() throws ParseException {
        loggedUser.toFront();
        titleTxt.setText("PROFIL");
    }

    public void modelyShowToFront() throws ParseException {
        searchBarModels.toFront();
        SbirkyToComboBox(vyberSbirkuCB);
        titleTxt.setText("MODELY");
    }

    public void sbirkySPToFront() throws ParseException {
        sbirkasSP.toFront();
        DrawSbirkas();
        titleTxt.setText("SBÍRKY");
    }

    public void obrCreateToFront() throws ParseException {
        pridatObrazekWithout.toFront();
        titleTxt.setText("VYTVOŘTE OBRÁZEK");
    }

    public void modelyToFront() throws ParseException {
        pridatModel.toFront();
        titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 43, 155, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        titleTxt.setText("VYTVOŘTE MODEL");
    }

    public void sbirkasToFront() throws ParseException {
        pridatSbirku.toFront();
        titleTxt.setText("VYTVOŘTE SBÍRKU");
    }

    @FXML
    public void ibackFromCreateZnackaPane() throws ParseException {
        pridatModel.toFront();
        titleTxt.setText("VYTVOŘTE ZNAČKU");
        titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 13, 135, 1), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    public void backFromDetail() throws ParseException {
        searchBarModels.toFront();
        modelDetailImage.setImage(new Image("Images/sbirkaFolderIcon.png"));
        titleTxt.setText("MODELY");
    }

    @FXML
    public void ibackFromPridatObrazek() throws ParseException {
        pridatModel.toFront();
        titleTxt.setText("VYBERTE OBRÁZKY");
        titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 43, 155, 1), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    public void createSbirka(ActionEvent actionEvent) throws ParseException {
        if (actionEvent.getSource() == vytvorSbirku) {
            Sbirka sbirka = new Sbirka();
            try {
                sbirka.setUsers_id(UsersSorted(usernameField.getText(), passwordField.getText()));
                String name = sbirkaName.getText();
                sbirka.setPopis(name);
                sbirka.setZalozeno(String.valueOf(sbirkaCreateDate.getValue()));
                System.out.println(asJson(sbirka));
                sbirkaService.saveOrUpdate(sbirka);
                System.out.println(asJson(sbirkaService.getAllSbirka()));
            } catch (Exception e) {
                if (sbirkaName.getText() == null) {
                    System.out.println(" Upozorneni CHYBA Zadejte prosim nazev sbirky.");
                }
                if (sbirkaCreateDate.getValue() == null) {
                    System.out.println(" Upozorneni CHYBA Zadejte prosim datuv vytvoreni sbirky.");
                }
            }
        }
    }

    public ArrayList<Sbirka> getSbirka() {
        ArrayList<Sbirka> list = new ArrayList<>();
        for (Sbirka sbirka : sbirkaService.getAllSbirka()) {
            if (sbirka.getUsers_id().getId() == (UsersSorted(usernameField.getText(), passwordField.getText()).getId())) {
                list.add(sbirka);
            }
        }
        System.out.println(asJson(list));
        return list;
    }

    public ArrayList<Modely> getModely() {
        ArrayList<Modely> list = new ArrayList<>();
        for (Modely modely : modelyService.getAllModely()) {
            if (modely.getUserByModel().getId() == UsersSorted(usernameField.getText(), passwordField.getText()).getId()) {
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
    public void createZnacka(ActionEvent actionEvent) throws ParseException {
        if (actionEvent.getSource() == createZnacka) {
            Znacka znacka = new Znacka();

            znacka.setPopis(znackaNameField.getText());
            znacka.setUser_id(UsersSorted(usernameField.getText(), passwordField.getText()).getId());
            znackaService.saveOrUpdate(znacka);
            System.out.println("Značka byla úspěšně vytvořena");
            pridatModel.toFront();
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 43, 155, 1), CornerRadii.EMPTY, Insets.EMPTY)));
            znackaNameField.setText(null);

        }
        SbirkyToComboBox(sbirkaComboBox);
        ZnackyToComboBox(znackaComboBox);
        iRememberSbirkaCBoxName(sbirkaComboBox, rememberedSbirkaName);
        iRememberSbirkaCBoxName(znackaComboBox, rememberedZnackaName);

    }

    int modelId;

    @FXML
    public void createModel(ActionEvent actionEvent) throws ParseException {
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
        params.setModel_param(modely);
        System.out.println(asJson(modely));
        paramsService.saveOrUpdate(params);
        System.out.println(asJson(params));

        System.out.println(asJson(sbirkaComboBox.getSelectionModel().selectedItemProperty().getValue()));
        System.out.println(asJson(znackaComboBox.getSelectionModel().selectedItemProperty().getValue()));

        wannaAddObrAlert();
    }

    public Sbirka findSbirkaFromComboBoxValue() {
        for (Sbirka sbirka : sbirkaService.getAllSbirka()) {
            if (sbirkaComboBox.getSelectionModel().selectedItemProperty().getValue().equals(sbirka.getPopis())) {
                return sbirka;
            }
        }
        return null;
    }

    public Znacka findZnackaFromComboBoxValue() {
        for (Znacka znacka : znackaService.getAllZnacka()) {
            if (znackaComboBox.getSelectionModel().selectedItemProperty().getValue().equals(znacka.getPopis())) {
                return znacka;
            }
        }
        return null;
    }

    public Modely findModelyFromComboBoxValue() {
        for (Modely modely : modelyService.getAllModely()) {
            System.out.println(asJson(modely));
            System.out.println(modely.getNazev());
            System.out.println(asJson(modelyService.getAllModely()));
            if (nazevModelsField.getText().equals(modely.getNazev()) && znackaComboBox.getValue().equals(modely.getZnacka_id()) && sbirkaComboBox.getValue().equals(modely.getSbirka_id())) {

                return modely;
            }
        }
        return null;
    }

    String obrazekPath = null;

    @FXML
    public void chooseFileFromPc(ImageView imageHolder, TextField pathShow) throws ParseException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPEG files (*.jpg)", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"),
                new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg")
        );
        fileChooser.setTitle("Vyberte obrázek: ");
        Window window = new Stage();
        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null && selectedFile.length() <= 10_000_000) {
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
        } else {
            alerted("Obrázek je příliš velký!");
        }
    }

    public void createObrazek(ActionEvent actionEvent) throws ParseException {
        Pics pics = new Pics();
        for (Modely modely : modelyService.getAllModely()) {
            if (modely.getNazev().equals(nazevModelsField.getText())) {
                pics.setModel_pic(modely);
            }
        }
        pics.setObr(obrazekPath);
        pics.setPopis(pathShow.getText());
        picsService.saveOrUpdate(pics);
        System.out.println(asJson(pics));
    }

    public void createObrazekAlone(ActionEvent actionEvent) throws ParseException {
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

    public void alerted(String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chyba!");
        alert.setHeaderText(headerText);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

    public void wannaAddObrAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("OKNO");
        alert.setHeaderText("Chcete přidat obrázek k tomuto modelu?");
        alert.setContentText("Prosím, vyberte odpověď.");

        ButtonType buttonYes = new ButtonType("ANO");
        ButtonType buttonNo = new ButtonType("NE");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonYes) {
            pridatObrazek.toFront();
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 43, 155, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            pridatModel.toFront();
            titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 43, 155, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    public void createUser(ActionEvent actionEvent) throws ParseException {
        System.out.println("Spuštěno");
        if (actionEvent.getSource() == createAcc) {
            Users users = new Users();
            try {
                users.setUsername(usernameField.getText());
                System.out.println(usernameField.getText());
                users.setPassword(passwordField.getText());
                System.out.println(passwordField.getText());
                usersService.saveOrUpdate(users);
                System.out.println(asJson(usersService.getAllUsers()));
                logIn();
            } catch (Exception e) {
                alerted("Prosím vyplňte všechna pole!");
            }
        }
    }

    public void rememberSbirkaCBox(int rememberName, ComboBox cb) {
        rememberName = cb.getSelectionModel().getSelectedIndex();
        System.out.println(asJson(rememberName));
    }

    public void iRememberSbirkaCBoxName(ComboBox cb, int rememberName) {
        cb.getSelectionModel().select(rememberName);
        System.out.println(asJson(rememberName));
    }

    @Autowired
    private ZnackaRepository znackaRepository;

    public void DrawSbirkas() {
        Stage stage = (Stage) sbirkyBtn.getScene().getWindow();
        double width = stage.getWidth();
        int numberOfColumns;
        System.out.println(width);

        numberOfColumns = (int) Math.floor((int) width / 190) - 1;

        System.out.println(numberOfColumns);

        GridPane grid = new GridPane();
        grid.setTranslateX(50);
        grid.setTranslateY(50);
        grid.setLayoutY(50);
        grid.setLayoutX(50);
        grid.setHgap(20);
        grid.setVgap(20);


        int counter = 0;
        for (Sbirka sbirka : getSbirka()) {
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
                    searchBarModels.toFront();
                    SbirkyToComboBox(vyberSbirkuCB);
                    vyberSbirkuCB.setValue(imageView.getId());
                    System.out.println(imageView.getId());
                }
            });
        }

        sbirkasSP.setContent(grid);

    }

    Modely picModelNumber;
    int idOfCurrentPic;
    int modelPicNum;
    String savedStarterPic = "";

    public void DrawModels() {
        picsArrayList.clear();
        modelySP.setContent(null);

        Stage stage = (Stage) modelyMenuBtn.getScene().getWindow();
        double width = stage.getWidth();
        int numberOfColumns;
        System.out.println(width);

        numberOfColumns = (int) Math.floor((int) width / 190) - 1;

        System.out.println(numberOfColumns);

        GridPane grid = new GridPane();

        grid.setTranslateX(50);
        grid.setTranslateY(20);
        grid.setLayoutY(20);
        grid.setLayoutX(30);
        grid.setHgap(20);
        grid.setVgap(20);


        int counter = 0;
        String picPath = "Images/sbirkaFolderIcon.png";
        for (Modely modely : getModely()) {
            for (Pics pics : picsService.getAllPics())
                if (pics.getModel_pic().getId() == modely.getId()) {
                    picPath = pics.getObr();
                    System.out.println(pics.getObr());
                    break;
                }
            System.out.println(asJson(modely));
            System.out.println(picPath);
            ImageView imageView = new ImageView(new Image(picPath));
            picPath = "Images/sbirkaFolderIcon.png";
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);

            String modelyNazev = null;
            if (modely.getNazev().length() >= 40) {
                modelyNazev = modely.getNazev().substring(0, 30);
            } else {
                modelyNazev = modely.getNazev();
            }
            Label label = new Label(modelyNazev);
            System.out.println(modely.getNazev());
            label.setTextAlignment(TextAlignment.CENTER);
            VBox vbox = new VBox(imageView, label);
            vbox.setAlignment(Pos.CENTER);
            grid.add(vbox, counter % numberOfColumns, counter / numberOfColumns);
            counter++;

            imageView.setId(label.getText());
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    modelPopisArea.setText(null);
                    cenaDetailTxtB.setText(null);
                    modelNazevDetail.setText(null);
                    int modelId;
                    modelyDetail.toFront();
                    modelNazevDetail.setText(imageView.getId());
                    modelId = modely.getId();
                    for (Pics pics : picsService.getAllPics()) {
                        if (pics.getModel_pic().getId() == modelId) {
                            modelDetailImage.setImage(new Image(pics.getObr()));
                            System.out.println(pics.getObr());
                            savedStarterPic = pics.getObr();
                            picModelNumber = pics.getModel_pic();
                            modelPicNum = pics.getIdOfPicModel();
                            System.out.println(modelPicNum);
                            idOfCurrentPic = pics.getId();
                            System.out.println(asJson(pics.getModel_pic()));
                            System.out.println("ID current obr: " + idOfCurrentPic);
                            System.out.println(asJson(picModelNumber));
                            picFilter();
                            break;

                        }
                    }
                    for (Params params : paramsService.getAllParams()) {
                        if (params.getModel_param().getId() == modelId) {
                            modelPopisArea.setText(params.getPopis());
                            cenaDetailTxtB.setText(params.getHodnota());
                            System.out.println(params.getPopis());
                        }
                    }
                    System.out.println("Klikl jsi na model: " + imageView.getId());
                }
            });
        }
        modelySP.setContent(grid);
    }

    ArrayList<Pics> picsArrayList = new ArrayList<Pics>();
    int currentImageIndex = 0;

    public void setImageDetailPrevious() throws ParseException {
        if (picsArrayList.size() <= 1) {
            System.out.println("NOTHING HERE");
            return;
        }

        currentImageIndex--;

        if (currentImageIndex < 0) {
            currentImageIndex = picsArrayList.size() - 1;
        }

        Pics currentPic = picsArrayList.get(currentImageIndex);

        modelDetailImage.setImage(new Image(currentPic.getObr()));
    }

    public void setImageDetailNext() throws ParseException {
        if (picsArrayList.size() <= 1) {
            System.out.println("NOTHING HERE");
            return;
        }

        currentImageIndex++;

        if (currentImageIndex >= picsArrayList.size()) {
            currentImageIndex = 0;
        }

        Pics currentPic = picsArrayList.get(currentImageIndex);

        modelDetailImage.setImage(new Image(currentPic.getObr()));
    }

    public void picFilter() {
        picsArrayList.clear();
        for (Pics pic : picsService.getAllPics()) {
            if (pic.getIdOfPicModel() == modelPicNum) {
                picsArrayList.add(pic);
                System.out.println("THIS IS CORRECT");
                System.out.println(asJson(pic));
            }
        }
        System.out.println(asJson(picsArrayList));
    }

    public void DrawResults() {
        picsArrayList.clear();
        vyberSbirkuCB.setPromptText("Vyberte Sbírku");
        modelySP.setContent(null);

        Stage stage = (Stage) modelyMenuBtn.getScene().getWindow();
        double width = stage.getWidth();
        int numberOfColumns;
        System.out.println(width);

        numberOfColumns = (int) Math.floor((int) width / 190) - 1;

        System.out.println(numberOfColumns);

        GridPane grid = new GridPane();

        grid.setTranslateX(50);
        grid.setTranslateY(20);
        grid.setLayoutY(20);
        grid.setLayoutX(30);
        grid.setHgap(20);
        grid.setVgap(20);

        ArrayList<Modely> modelyList = new ArrayList<>();
        int counter = 0;
        String picPath = "Images/sbirkaFolderIcon.png";
        for (Modely modely : searchModels()) {
            for (Pics pics : picsService.getAllPics())
                if (pics.getModel_pic().getId() == modely.getId()) {
                    picPath = pics.getObr();
                    System.out.println(pics.getObr());
                    break;
                }
            System.out.println(asJson(modely));
            System.out.println(picPath);
            ImageView imageView = new ImageView(new Image(picPath));
            picPath = "Images/sbirkaFolderIcon.png";
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);

            String modelyNazev = null;
            if (modely.getNazev().length() >= 40) {
                modelyNazev = modely.getNazev().substring(0, 30);
            } else {
                modelyNazev = modely.getNazev();
            }
            Label label = new Label(modelyNazev);
            System.out.println(modely.getNazev());
            label.setTextAlignment(TextAlignment.CENTER);
            VBox vbox = new VBox(imageView, label);
            vbox.setAlignment(Pos.CENTER);
            grid.add(vbox, counter % numberOfColumns, counter / numberOfColumns);
            counter++;

            imageView.setId(label.getText());
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    modelPopisArea.setText(null);
                    cenaDetailTxtB.setText(null);
                    modelNazevDetail.setText(null);
                    int modelId;
                    modelyDetail.toFront();
                    modelNazevDetail.setText(imageView.getId());
                    modelId = modely.getId();
                    for (Pics pics : picsService.getAllPics()) {
                        if (pics.getModel_pic().getId() == modelId) {
                            modelDetailImage.setImage(new Image(pics.getObr()));
                            System.out.println(pics.getObr());
                            savedStarterPic = pics.getObr();
                            picModelNumber = pics.getModel_pic();
                            modelPicNum = pics.getIdOfPicModel();
                            System.out.println(modelPicNum);
                            idOfCurrentPic = pics.getId();
                            System.out.println(asJson(pics.getModel_pic()));
                            System.out.println("ID current obr: " + idOfCurrentPic);
                            System.out.println(asJson(picModelNumber));
                            picFilter();
                            break;

                        }
                    }
                    for (Params params : paramsService.getAllParams()) {
                        if (params.getModel_param().getId() == modelId) {
                            modelPopisArea.setText(params.getPopis());
                            cenaDetailTxtB.setText(params.getHodnota());
                            System.out.println(params.getPopis());
                        }
                    }
                    System.out.println("Klikl jsi na model: " + imageView.getId());
                }
            });
        }
        modelySP.setContent(grid);
    }

    public ArrayList<Modely> searchModels() {
        String searchText = searchModelField.getText();
        ArrayList<Modely> searchResults = new ArrayList<>();

        for (Modely modely : modelyService.getAllModely()) {
            if (modely.getNazev().contains(searchText)) {
                searchResults.add(modely);
            }
        }
        for (Modely result : searchResults) {
            System.out.println(result.getNazev());
        }
        return searchResults;
    }

    public void ProgressIndicator() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            double currentProgress = progressIndicator.getProgress();
            if (currentProgress < 1.0) {
                progressIndicator.setProgress(currentProgress + 0.2);
            } else {
                //timeline.stop();
                Platform.runLater(() -> DrawModels());
                progressIndicator.setProgress(0);
            }
        }));
        timeline.setCycleCount(5);
        timeline.play();
    }

    public Users UsersSorted(String username, String password) {
        for (Users users : usersService.getAllUsers()) {
            if (username.equals(users.getUsername()) && password.equals(users.getPassword())) {
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

    public Sbirka SbirkasSorted() {
        for (Sbirka sbirka : sbirkaService.getAllSbirka()) {
            if (sbirkaComboBox.getSelectionModel().equals(sbirka.getPopis())) {
                return sbirka;
            }
        }
        return null;
    }

    public Pics PicsSorted() {
        for (Pics pics : picsService.getAllPics()) {

        }
        return null;
    }

    public Znacka ZnackasSorted() {
        for (Znacka znacka : znackaService.getAllZnacka()) {
            if (znackaComboBox.getSelectionModel().equals(znacka.getPopis())) {
                return znacka;
            }
        }
        return null;
    }

    public Modely ModelySorted() {
        for (Modely modely : modelyService.getAllModely()) {
            if (paramsPopisField.getText().equals(modely.getNazev()) && znackaComboBox.getValue().equals(modely.getZnacka_id()) && sbirkaComboBox.getValue().equals(modely.getSbirka_id())) {
                return modely;
            }
        }
        return null;
    }

    public String UsersUsername() {
        for (Users users : usersService.getAllUsers()) {
            if (usernameField.getText().equals(users.getUsername()) && passwordField.getText().equals(users.getPassword())) {
                return users.getUsername();
            }
        }
        return null;
    }

    public String UsersPassword() {
        for (Users users : usersService.getAllUsers()) {
            if (usernameField.getText().equals(users.getUsername()) && passwordField.getText().equals(users.getPassword())) {
                return users.getPassword();
            }
        }
        return null;
    }

    @FXML
    public void loginUser(ActionEvent actionEvent) throws ParseException {
        logIn();
    }

    void logIn() {
        if (UsersSorted(usernameField.getText(), passwordField.getText()) == null) {
            alerted("Zadali jste špatnou přezdívku nebo heslo!");
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
    public void logOutUser(ActionEvent actionEvent) throws ParseException {
        login.toFront();
        logOut();
        titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(37, 109, 133, 1), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    void logOut() {
        usernameField.setText(null);
        passwordField.setText(null);
        loggedIn = false;
        sbirkyBtn.setDisable(true);
        menuBtn.setDisable(true);
        pridatModelbtn.setDisable(true);
        pridatSbirkuBtn.setDisable(true);
        titleTxt.setText("LOGIN");
        loginBtn.setText("Přihlásit se");
        alerted("Odhlásili jsme vás z účtu!");
    }

    public ArrayList<Znacka> ZnackyAll() {
        ArrayList<Znacka> znackyList = new ArrayList<>();
        for (Znacka znacka : znackaService.getAllZnacka()) {
            if (znacka.getUser_id() == UsersSorted(usernameField.getText(), passwordField.getText()).getId()) {
                znackyList.add(znacka);
            }
        }
        System.out.println(asJson(znackyList));
        return znackyList;
    }

    public ArrayList<Params> ParamsAll() {
        ArrayList<Params> paramsList = new ArrayList<>();
        for (Sbirka sbirka : sbirkaService.getAllSbirka()) {
            if (SbirkaObrAddCB.getValue() == sbirka.getPopis()) {
                for (Modely modely : modelyService.getAllModely()) {
                    if (modely.getSbirka_id() == sbirka) {
                        System.out.println(asJson(modely.getSbirka_id()));
                        System.out.println(asJson(sbirka));
                        for (Params params : paramsService.getAllParams()) {
                            if (params.getModel_param() == modely) {
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

    public ArrayList<Modely> ModelyChoose() {
        picsArrayList.clear();
        modelySP.setContent(sbirkas11);

        Stage stage = (Stage) modelyMenuBtn.getScene().getWindow();
        double width = stage.getWidth();
        int numberOfColumns;
        System.out.println(width);

        numberOfColumns = (int) Math.floor((int) width / 190) - 1;

        System.out.println(numberOfColumns);

        GridPane grid = new GridPane();

        grid.setTranslateX(50);
        grid.setTranslateY(20);
        grid.setLayoutY(20);
        grid.setLayoutX(30);
        grid.setHgap(20);
        grid.setVgap(20);

        ArrayList<Modely> modelyList = new ArrayList<>();
        int counter = 0;
        String picPath = "Images/sbirkaFolderIcon.png";
        for (Modely modely : getModely()) {
            if (modely.getSbirka_id().getPopis() == vyberSbirkuCB.getValue()) {
                for (Pics pics : picsService.getAllPics())
                    if (pics.getModel_pic().getId() == modely.getId()) {
                        picPath = pics.getObr();
                        System.out.println(pics.getObr());
                        break;
                    }
                System.out.println(asJson(modely));
                System.out.println(picPath);
                ImageView imageView = new ImageView(new Image(picPath));
                picPath = "Images/sbirkaFolderIcon.png";
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);

                String modelyNazev = null;
                if (modely.getNazev().length() >= 40) {
                    modelyNazev = modely.getNazev().substring(0, 30);
                } else {
                    modelyNazev = modely.getNazev();
                }
                Label label = new Label(modelyNazev);
                System.out.println(modely.getNazev());
                label.setTextAlignment(TextAlignment.CENTER);
                VBox vbox = new VBox(imageView, label);
                vbox.setAlignment(Pos.CENTER);
                grid.add(vbox, counter % numberOfColumns, counter / numberOfColumns);
                counter++;

                imageView.setId(label.getText());
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        modelPopisArea.setText(null);
                        cenaDetailTxtB.setText(null);
                        modelNazevDetail.setText(null);
                        int modelId;
                        modelyDetail.toFront();
                        modelNazevDetail.setText(imageView.getId());
                        modelId = modely.getId();
                        for (Pics pics : picsService.getAllPics()) {
                            if (pics.getModel_pic().getId() == modelId) {
                                modelDetailImage.setImage(new Image(pics.getObr()));
                                System.out.println(pics.getObr());
                                savedStarterPic = pics.getObr();
                                picModelNumber = pics.getModel_pic();
                                modelPicNum = pics.getIdOfPicModel();
                                System.out.println(modelPicNum);
                                idOfCurrentPic = pics.getId();
                                System.out.println(asJson(pics.getModel_pic()));
                                System.out.println("ID current obr: " + idOfCurrentPic);
                                System.out.println(asJson(picModelNumber));
                                picFilter();
                                break;

                            }
                        }
                        for (Params params : paramsService.getAllParams()) {
                            if (params.getModel_param().getId() == modelId) {
                                modelPopisArea.setText(params.getPopis());
                                cenaDetailTxtB.setText(params.getHodnota());
                                System.out.println(params.getPopis());
                            }
                        }
                        System.out.println("Klikl jsi na model: " + imageView.getId());
                    }
                });
            }
        }
        modelySP.setContent(grid);
        return modelyList;
    }

    public void ZnackyToComboBox(ComboBox znackaComboBox) {
        znackaComboBox.getItems().clear();
        for (Znacka znacka : ZnackyAll()) {
            znackaComboBox.getItems().add(znacka.getPopis());
        }
    }

    public ArrayList<Sbirka> SbirkyAll() {
        ArrayList<Sbirka> sbirkyList = new ArrayList<>();
        for (Sbirka sbirka : sbirkaService.getAllSbirka()) {
            if (getSbirka().equals(UsersSorted(usernameField.getText(), passwordField.getText()).getId())) {
                sbirkyList.add(sbirka);
            }
        }
        System.out.println(asJson(sbirkyList));
        return sbirkyList;
    }

    public ArrayList<Modely> ModelyAll() {
        ArrayList<Modely> modelyList = new ArrayList<>();
        for (Modely modely : modelyService.getAllModely()) {
            System.out.println(asJson(modelyService.getAllModely()));
            System.out.println(modely.getPopisSbirka());
            System.out.println(SbirkaObrAddCB.getValue());
            if (modely.getPopisSbirka().equals(SbirkaObrAddCB.getValue())) {
                modelyList.add(modely);
            }
        }
        System.out.println(asJson(modelyList));
        return modelyList;
    }

    public void ModelyToComboBox() {
        ModelObrAddCB.getItems().clear();
        for (Modely modely : ModelyAll()) {
            ModelObrAddCB.getItems().add(modely.getNazev());
        }
    }

    public void ParamsToComboBox() {
        ModelObrAddCB.getItems().clear();
        for (Params params : ParamsAll()) {
            ModelObrAddCB.getItems().add(params.getPopis());
        }
    }

    public void SbirkyToComboBox(ComboBox sbirkaComboBox) {
        sbirkaComboBox.getItems().clear();
        //sbirkaComboBox.getItems().add("Všechny modely");
        for (Sbirka sbirka : getSbirka()) {
            sbirkaComboBox.getItems().add(sbirka.getPopis());
        }
    }
}