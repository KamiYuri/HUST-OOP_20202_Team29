package sample.GUI.SubScene.RecipeInf;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecipeInfomationController extends Application {
    @FXML public Stage thisStage;
    @FXML public Label name;
    @FXML public TextField nameText;

    @Override
    public void start(Stage thisStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RecipeInfomation.fxml"));
        Parent root = new loader.getResources();

        root.
    }
}
