package sample.GUI.SubScene.Name;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Objects;

public class NameController extends Application {

    private String name;

    @FXML
    private TextField textInput;
    @FXML
    private Button submit;

    @FXML
    public void submitClick() {
        this.submit.setOnMouseClicked(mouseEvent -> {
            if (textInput.getText() != null)
                this.name = textInput.getText();
        });
    }

    public String getInput() {
        return name;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage thisStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(NameController.class.getResource("Name.fxml")));
            thisStage.setScene(new Scene(root));
            thisStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
