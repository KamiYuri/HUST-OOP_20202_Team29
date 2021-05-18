package GUI.View.SubMenu.Month;

import GUI.Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MonthController {
    private final Stage thisStage;

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
    private Button submit;

    public MonthController() {
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(MonthController.class.getResource("Month.fxml")));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Thống kê theo tháng");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.initModality(Modality.APPLICATION_MODAL);
        thisStage.showAndWait();
    }

    @FXML
    private void initialize() {
        monthSelect.setItems(monthList);
    }

    @FXML
    public void submitClick() {
        submit.setOnMouseClicked(mouseEvent -> {
            if (monthSelect.getValue() != null) {
                System.out.println(getInput());
                Node source = (Node) mouseEvent.getSource();
                Stage thisStage = (Stage) source.getScene().getWindow();
                thisStage.close();
                showResult();
                Controller.getInstance().getMainStageController().showUndo(true);
                Controller.getInstance().getMainStageController().showIncome(getInput());
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
        return monthList.indexOf(monthSelect.getValue()) + 1;
    }
    public void showResult() {
        Controller.getInstance().findMonth(getInput());
    }

}
