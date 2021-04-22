package sample.GUI.SubScene.Month;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class MonthController extends Application {
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
        monthSelect.setItems(monthList);
    }

    @FXML
    private Button submit;

    @FXML
    public void submitClick() {
        submit.setOnMouseClicked(mouseEvent -> {
            if (monthSelect.getValue() != null) {
                System.out.println(getInput());
                Node source = (Node) mouseEvent.getSource();
                Stage thisStage = (Stage) source.getScene().getWindow();
                thisStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert");
                alert.setHeaderText(null);
                alert.setContentText("Chọn một tháng!");
                alert.showAndWait();
            }
        });
    }

    public int getInput() {
        return monthList.indexOf(monthSelect.getValue());
    }

    @Override
    public void start(Stage thisStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Month.fxml"));
            thisStage.setTitle("Thống kê theo tháng");
            thisStage.setScene(new Scene(root));
            thisStage.show();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
