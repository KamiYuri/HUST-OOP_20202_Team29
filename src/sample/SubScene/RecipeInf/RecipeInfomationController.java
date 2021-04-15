package sample.SubScene.RecipeInf;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RecipeInfomationController extends Application {
    @FXML public Stage thisStage;
    @FXML public Label name;
    @FXML public TextField nameText;

    @Override
    public void start(Stage thisStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(""));



        thisStage.setScene(getClass().gets);
        thisStage.setTitle("Recipe Information");
    }
}
