package sample.GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.GUI.Order.OrderController;

public class Main extends Application{
    @FXML
    private Stage thisStage;

    @Override
    public void start(Stage thisStage) {
        try {
            Parent root = FXMLLoader.load(OrderController.class.getResource("Order.fxml"));
            thisStage.setTitle("Don Hang");
            thisStage.setScene(new Scene(root));
            thisStage.show();
        } catch (Exception e) {

        }
    }
    public static void main(String[] args) {launch(args);}
}