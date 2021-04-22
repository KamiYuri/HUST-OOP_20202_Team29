package sample.GUI.SubScene.monthlist;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.skin.ChoiceBoxSkin;
import javafx.stage.Stage;

public class MonthListController extends Application {
    //Menu chọn tháng//
    ObservableList<String> monthList = FXCollections
            .observableArrayList(
                    "Tháng 1", "Tháng 2", "Tháng 3",
                    "Tháng 4", "Tháng 5", "Tháng 6",
                    "Tháng 7", "Tháng 8", "Tháng 9",
                    "Tháng 10", "Tháng 11", "Tháng 12"
            );

    @FXML private ChoiceBox monthSelect;
    @FXML private void initialize() {
        monthSelect.setValue("Chọn một tháng");
        monthSelect.setItems(monthList);
    }

    @FXML public Stage thisStage;

    @Override public void start(Stage thisStage) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("monthlist.fxml"));
            thisStage.setScene(new Scene(root));
            thisStage.show();
        }catch (Exception e) {
        }
    }
    public static void main(String[] args) {launch(args);  }
}
