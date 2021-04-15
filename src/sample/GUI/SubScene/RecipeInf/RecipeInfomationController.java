package sample.GUI.SubScene.RecipeInf;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecipeInfomationController extends Application {
    @FXML public Stage thisStage;
    @FXML public Label name;
    @FXML public TextField nameText;

    @Override
    public void start(Stage thisStage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("RecipeInfomation.fxml"));
            thisStage.setTitle("Recipe Information");
            thisStage.setScene(new Scene(root));
            thisStage.show();
        }
        catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
