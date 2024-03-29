package Projekt.sbirka;

import Projekt.sbirka.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class StageListener implements ApplicationListener<Projekt.sbirka.StageReadyEvent> {
    private final String applicationTitle;
    private final Resource fxml;
    private final ApplicationContext ac;

    StageListener(@Value("Sbírej s klidem") String applicationTitle,
                  @Value("classpath:/UI.fxml") Resource resource, ApplicationContext ac){
        this.applicationTitle = applicationTitle;
        this.fxml = resource;
        this.ac = ac;
    }
    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        try {
            AtomicReference<Double> x = new AtomicReference<>((double) 0);
            AtomicReference<Double> y = new AtomicReference<>((double) 0);
            Stage stage = stageReadyEvent.getStage();
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle(this.applicationTitle);
            Image image = new Image("Images/iconka1.png");
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed (evt ->{
                x.set(evt.getSceneX());
                y.set(evt.getSceneY());
            });
            root.setOnMouseDragged(evt ->{
                stage.setX(evt.getScreenX()- x.get());
                stage.setY(evt.getScreenY()- y.get());
            });
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
