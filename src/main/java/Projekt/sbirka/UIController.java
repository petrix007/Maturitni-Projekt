package Projekt.sbirka;

import Projekt.sbirka.Entity.*;
import Projekt.sbirka.Repository.*;
import Projekt.sbirka.Service.*;
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
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
    private BorderPane modelyEditPane;
    @FXML
    private ImageView pridatObrazekMenuBtn;
    @FXML
    private ImageView topLeftLogoImg;
    @FXML
    private ImageView pridatSbirkuMenuBtn;
    @FXML
    private Button editAddObr;

    @FXML
    private TextField editCenaCurrentTXTF;

    @FXML
    private Button editModelBtn;
    @FXML
    private ComboBox<String> editNewSbirkaCB;

    @FXML
    private ComboBox<String> editNewZnackaCB;

    @FXML
    private Button editModelBtn1;

    @FXML
    private Label editModelNazevCurrentL;

    @FXML
    private TextField editNameCurrentTXTF;

    @FXML
    private Button editObrBTN;

    @FXML
    private TextArea editPopisCurrentTXTA;
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
    ParamsService paramsService;
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
    PicsService picsService;
    @FXML
    private ComboBox<String> vyberSbirkuCB;
    @FXML
    private ComboBox<String> editSbirkaCBCurrent;

    @FXML
    private ComboBox<String> editZnačkaCurrentCB;
    @FXML
    private ImageView backFromDetail;
    @FXML
    private Button nextObrAdd1;
    @FXML
    private BorderPane modelyDetail;
    @FXML
    private TextField editNewCenaTXTF;

    @FXML
    private TextField editNewNazevTXTF;

    @FXML
    private TextArea editNewPopisTXTA;
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
            alerted("CHYBA, Zavolejte na podporu");
        }
        login.toFront();
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
            pathShow.setText(null);
            imageShow.setImage(new Image("Images\\chooseFile.png"));
        }
        if (event.getSource() == nextObrAdd1) {
            pathShow1.setText(null);
            SbirkaObrAddCB.setValue("Vyberte sbírku");
            ModelObrAddCB.setValue("VyberteModel");
            imageShow.setImage(new Image("Images\\chooseFile.png"));
        }
        if (event.getSource() == chooseFile  ) {
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
        if (event.getSource() == editModelBtn){
            modelyEditPane.toFront();
            titleTxt.setText("UPRAVIT MODEL");
            takeInfoFromDetailToEditP();
        }
    }

    int CurrentModelID;
    String name;
    String CurrentPopis;
    public void takeInfoFromDetailToEditP(){
        name = modelNazevDetail.getText();
        String modelPopis = modelPopisArea.getText();
        String modelCena = cenaDetailTxtB.getText();


        editModelNazevCurrentL.setText(name);
        editNameCurrentTXTF.setText(name);
        editPopisCurrentTXTA.setText(modelPopis);
        editCenaCurrentTXTF.setText(modelCena);

        SbirkyToComboBox(editNewSbirkaCB);
        ZnackyToComboBox(editNewZnackaCB);
        editNewSbirkaCB.setValue(GetModelIdByName(name).getSbirka_id().getPopis());
        editNewZnackaCB.setValue(GetModelIdByName(name).getZnacka_id().getPopis());
        editSbirkaCBCurrent.setValue(editNewSbirkaCB.getValue());
        editZnačkaCurrentCB.setValue(editNewZnackaCB.getValue());
        CurrentModelID = GetModelIdByName(name).getId();

        CurrentPopis = editPopisCurrentTXTA.getText();

        editNewNazevTXTF.setText(name);
        editNewPopisTXTA.setText(modelPopis);
        editNewCenaTXTF.setText(modelCena);
    }
    @FXML
    public void updateModelParams(){
        Modely modely = new Modely();
        try {
            modely.setId(CurrentModelID);
            modely.setSbirka_id(findSbirkaFromComboBoxValue(editNewSbirkaCB));
            modely.setZnacka_id(findZnackaFromComboBoxValue(editNewZnackaCB));
            modely.setNazev(editNewNazevTXTF.getText());
            modelyService.saveOrUpdate(modely);

        } catch (Exception e) {
            alerted("Zadejte platný název, sbírku nebo značku!");
        }
        CurrentPopis = editPopisCurrentTXTA.getText();

        Params params = new Params();

        try{
            params.setId(GetParamsIdByPopis(CurrentPopis).getId());
            params.setModel_param(modely);
            params.setPopis(editNewPopisTXTA.getText());
            params.setHodnota(editNewCenaTXTF.getText());
            paramsService.saveOrUpdate(params);

        }catch (Exception e){
            alerted("Zadejte platný popis nebo cenu!");
        }
        DrawModels();
        searchBarModels.toFront();
    }
    public void pridatObrEditMode() throws ParseException {
        pridatObrazekWithout.toFront();
        for (Modely modely : modelyService.getAllModely()) {
            if (modelNazevDetail.getText().equals(modely.getNazev())) {
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
    public void backFromEdit() throws ParseException{
        modelyDetail.toFront();
        titleTxt.setText("MODELY");
    }

    @FXML
    public void createSbirka(ActionEvent actionEvent) throws ParseException {
        if (actionEvent.getSource() == vytvorSbirku) {
            Sbirka sbirka = new Sbirka();
            if (name == null){
                alerted("ZADEJTE HODNOTY!");
            }else {
                try {
                    sbirka.setUsers_id(UsersSorted(usernameField.getText(), passwordField.getText()));
                    String name = sbirkaName.getText();
                    sbirka.setPopis(name);
                    sbirka.setZalozeno(String.valueOf(sbirkaCreateDate.getValue()));
                    sbirkaService.saveOrUpdate(sbirka);
                } catch (Exception e) {
                    alerted("Zadejte platné hodnoty!");
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
        return list;
    }

    public ArrayList<Modely> getModely() {
        ArrayList<Modely> list = new ArrayList<>();
        for (Modely modely : modelyService.getAllModely()) {
            if (modely.getUserByModel().getId() == UsersSorted(usernameField.getText(), passwordField.getText()).getId()) {
                list.add(modely);
            }
        }
        return list;
    }


    @FXML
    public void createZnacka(ActionEvent actionEvent) throws ParseException {
        if (actionEvent.getSource() == createZnacka) {
            if (znackaNameField.getText() != null){
                Znacka znacka = new Znacka();
                znacka.setPopis(znackaNameField.getText());
                znacka.setUser_id(UsersSorted(usernameField.getText(), passwordField.getText()).getId());
                znackaService.saveOrUpdate(znacka);
                alerted("Značka byla úspěšně vytvořena!");
                pridatModel.toFront();
                titleColor.setBackground(new Background(new BackgroundFill(Color.rgb(67, 43, 155, 1), CornerRadii.EMPTY, Insets.EMPTY)));
                znackaNameField.setText(null);
            }else {
                alerted("ZADEJTE SPRÁVNÉ HODNOTY!");
            }
        }
        SbirkyToComboBox(sbirkaComboBox);
        ZnackyToComboBox(znackaComboBox);
        iRememberSbirkaCBoxName(sbirkaComboBox, rememberedSbirkaName);
        iRememberSbirkaCBoxName(znackaComboBox, rememberedZnackaName);

    }

    int modelId;

    @FXML
    public void createModel(ActionEvent actionEvent) throws ParseException {
        if (nazevModelsField.getText() != null){
            Modely modely = new Modely();

            SbirkasSorted();
            ZnackasSorted();
            ModelySorted();
            modely.setNazev(nazevModelsField.getText());
            modely.setSbirka_id(findSbirkaFromComboBoxValue(sbirkaComboBox));
            modely.setZnacka_id(findZnackaFromComboBoxValue(znackaComboBox));

            modelId = modely.getId();

            modelyService.saveOrUpdate(modely);

            Params params = new Params();

            SbirkasSorted();
            ZnackasSorted();
            ModelySorted();

            params.setPopis(paramsPopisField.getText());
            params.setHodnota(paramsHodnotaField.getText());
            params.setModel_param(modely);
            paramsService.saveOrUpdate(params);

            alerted("Model byl úspěšně vytvořen");
            wannaAddObrAlert();

        }else {
            alerted("ZADEJTE HODNOTY!");
        }
    }

    public Sbirka findSbirkaFromComboBoxValue(ComboBox<String> sbirkaComboBox) {
        for (Sbirka sbirka : sbirkaService.getAllSbirka()) {
            if (sbirkaComboBox.getSelectionModel().selectedItemProperty().getValue().equals(sbirka.getPopis())) {
                return sbirka;
            }
        }
        return null;
    }

    public Znacka findZnackaFromComboBoxValue(ComboBox<String> znackaComboBox) {
        for (Znacka znacka : znackaService.getAllZnacka()) {
            if (znackaComboBox.getSelectionModel().selectedItemProperty().getValue().equals(znacka.getPopis())) {
                return znacka;
            }
        }
        return null;
    }

    public Modely findModelyFromComboBoxValue() {
        for (Modely modely : modelyService.getAllModely()) {
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
                alerted("Obrázek byl uložen do: " + fileLocation);

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
        if (obrazekPath == null || pathShow.getText() == null){
            alerted("Vyplňte pole!");
        }else{
            pics.setObr(obrazekPath);
            pics.setPopis(pathShow.getText());
            picsService.saveOrUpdate(pics);
        }
    }

    public void createObrazekAlone(ActionEvent actionEvent) throws ParseException {
        Pics pics = new Pics();
        if (obrazekPath == null || pathShow.getText() == null){
            alerted("Vyplňte pole!");
        }else{
            pics.setModel_pic(GetModelById(GetIDbyName_Obor(ModelObrAddCB.getValue())));
            pics.setObr(obrazekPath);
            pics.setPopis(pathShow1.getText());
            picsService.saveOrUpdate(pics);
        }

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
        if (actionEvent.getSource() == createAcc) {
            Users users = new Users();
            if (usernameField.getText() == null || passwordField.getText() == null){
                alerted("ZADEJTE PLATNÉ HODNOTY!");
            }else {
                try {
                    users.setUsername(usernameField.getText());
                    users.setPassword(passwordField.getText());
                    usersService.saveOrUpdate(users);
                    logIn();
                } catch (Exception e) {
                    alerted("Prosím vyplňte všechna pole!");
                }
            }
        }
    }

    public void rememberSbirkaCBox(int rememberName, ComboBox cb) {
        rememberName = cb.getSelectionModel().getSelectedIndex();
    }

    public void iRememberSbirkaCBoxName(ComboBox cb, int rememberName) {
        cb.getSelectionModel().select(rememberName);
    }

    @Autowired
    private ZnackaRepository znackaRepository;

    public void DrawSbirkas() {
        Stage stage = (Stage) sbirkyBtn.getScene().getWindow();
        double width = stage.getWidth();
        int numberOfColumns;

        numberOfColumns = (int) Math.floor((int) width / 190) - 1;

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

        numberOfColumns = (int) Math.floor((int) width / 190) - 1;

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
                    break;
                }
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
                            savedStarterPic = pics.getObr();
                            picModelNumber = pics.getModel_pic();
                            modelPicNum = pics.getIdOfPicModel();
                            idOfCurrentPic = pics.getId();
                            picFilter();
                            break;

                        }
                    }
                    for (Params params : paramsService.getAllParams()) {
                        if (params.getModel_param().getId() == modelId) {
                            modelPopisArea.setText(params.getPopis());
                            cenaDetailTxtB.setText(params.getHodnota());
                        }
                    }
                }
            });
        }
        modelySP.setContent(grid);
    }

    ArrayList<Pics> picsArrayList = new ArrayList<Pics>();
    int currentImageIndex = 0;

    public void setImageDetailPrevious() throws ParseException {
        if (picsArrayList.size() <= 1) {
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
            }
        }
    }

    public void DrawResults() {
        picsArrayList.clear();
        vyberSbirkuCB.setPromptText("Vyberte Sbírku");
        modelySP.setContent(null);

        Stage stage = (Stage) modelyMenuBtn.getScene().getWindow();
        double width = stage.getWidth();
        int numberOfColumns;

        numberOfColumns = (int) Math.floor((int) width / 190) - 1;

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
                    break;
                }
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
                            savedStarterPic = pics.getObr();
                            picModelNumber = pics.getModel_pic();
                            modelPicNum = pics.getIdOfPicModel();
                            idOfCurrentPic = pics.getId();
                            picFilter();
                            break;
                        }
                    }
                    for (Params params : paramsService.getAllParams()) {
                        if (params.getModel_param().getId() == modelId) {
                            modelPopisArea.setText(params.getPopis());
                            cenaDetailTxtB.setText(params.getHodnota());
                        }
                    }
                }
            });
        }
        modelySP.setContent(grid);
    }

    public ArrayList<Modely> searchModels() {
        String searchText = searchModelField.getText();
        ArrayList<Modely> searchResults = new ArrayList<>();
        HashSet<Integer> addedModelIds = new HashSet<>();

        String searchTextLower = searchText.toLowerCase();

        String selectedSbirka = vyberSbirkuCB.getValue();

        for (int i = 0; i < searchText.length(); i++) {
            StringBuilder sb = new StringBuilder(searchTextLower);
            sb.setCharAt(i, Character.toUpperCase(searchText.charAt(i)));
            String searchTextMixed = sb.toString();

            for (Modely modely : modelyService.getAllModely()) {
                if (modely.getSbirka_id().getPopis().equals(selectedSbirka) &&
                        modely.getNazev().toLowerCase().contains(searchTextMixed.toLowerCase()) &&
                        !addedModelIds.contains(modely.getId())) {
                    searchResults.add(modely);
                    addedModelIds.add(modely.getId());
                }
            }
        }

        if (searchResults.isEmpty()) {
        } else {
            for (Modely result : searchResults) {
            }
        }

        return searchResults;
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
            if (modely.getNazev().equals(nazev)) {
                return modely.getId();
            }
        }
        return 0;
    }
    public Modely GetModelIdByName(String name){
        for (Modely modely: modelyService.getAllModely()){
            if (modely.getNazev() == name){
                return modely;
            }
        }
        return null;
    }
    public Params GetParamsIdByPopis(String popis){
        for (Params params: paramsService.getAllParams()){
            if (params.getPopis().equals(popis)){
                return params;
            }
        }
        return null;
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
            loginBtn.setText(UsersUsername());
            titleTxt.setText("USER");
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
        modelyMenuBtn.setDisable(true);
        pridatObrazekbtn.setDisable(true);
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
        return znackyList;
    }

    public ArrayList<Params> ParamsAll() {
        ArrayList<Params> paramsList = new ArrayList<>();
        for (Sbirka sbirka : sbirkaService.getAllSbirka()) {
            if (SbirkaObrAddCB.getValue() == sbirka.getPopis()) {
                for (Modely modely : modelyService.getAllModely()) {
                    if (modely.getSbirka_id() == sbirka) {
                        for (Params params : paramsService.getAllParams()) {
                            if (params.getModel_param() == modely) {
                                paramsList.add(params);
                            }
                        }
                    }
                }
            }
        }
        return paramsList;
    }

    public ArrayList<Modely> ModelyChoose() {
        picsArrayList.clear();
        modelySP.setContent(sbirkas11);

        Stage stage = (Stage) modelyMenuBtn.getScene().getWindow();
        double width = stage.getWidth();
        int numberOfColumns;

        numberOfColumns = (int) Math.floor((int) width / 190) - 1;


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
                        break;
                    }
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
                                savedStarterPic = pics.getObr();
                                picModelNumber = pics.getModel_pic();
                                modelPicNum = pics.getIdOfPicModel();
                                idOfCurrentPic = pics.getId();
                                picFilter();
                                break;

                            }
                        }
                        for (Params params : paramsService.getAllParams()) {
                            if (params.getModel_param().getId() == modelId) {
                                modelPopisArea.setText(params.getPopis());
                                cenaDetailTxtB.setText(params.getHodnota());
                            }
                        }
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
        return sbirkyList;
    }

    public ArrayList<Modely> ModelyAll() {
        ArrayList<Modely> modelyList = new ArrayList<>();
        for (Modely modely : modelyService.getAllModely()) {
            if (modely.getPopisSbirka().equals(SbirkaObrAddCB.getValue())) {
                modelyList.add(modely);
            }
        }
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
        for (Sbirka sbirka : getSbirka()) {
            sbirkaComboBox.getItems().add(sbirka.getPopis());
        }
    }
}