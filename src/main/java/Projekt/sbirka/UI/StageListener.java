package Projekt.sbirka.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class StageListener implements ApplicationListener<Projekt.sbirka.UI.StageReadyEvent> {
    private final String applicationTitle;
    private final Resource fxml;
    private final ApplicationContext ac;

    StageListener(@Value("{$spring.application.ui.title}") String applicationTitle,
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
            stage.setResizable(false);
            stage.setTitle(this.applicationTitle);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
