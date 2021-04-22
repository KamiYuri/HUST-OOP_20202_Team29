package sample.GUI.Order;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import sample.GUI.SubScene.Name.NameController;

import java.io.IOException;

public class OrderController {

    //Menu chọn tháng//
    ObservableList<String> monthList = FXCollections
            .observableArrayList(
                    "Tháng 1", "Tháng 2", "Tháng 3",
                    "Tháng 4", "Tháng 5", "Tháng 6",
                    "Tháng 7", "Tháng 8", "Tháng 9",
                    "Tháng 10", "Tháng 11", "Tháng 12"
            );
    @FXML
    private ChoiceBox monthSelect;


    @FXML
    private void initialize() {
        monthSelect.setValue("Chọn một tháng");
        monthSelect.setItems(monthList);
    }

    @FXML
    private MenuItem sender;

    @FXML
    public void senderClick() {
        sender.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent actionEvent) {
                   try {
                       Parent subWindow = FXMLLoader.load(NameController.class.getResource("Name.fxml"));
                       Stage subStage = new Stage();
                       subStage.setScene(new Scene(subWindow));
                       subStage.show();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
        );
    }
}


