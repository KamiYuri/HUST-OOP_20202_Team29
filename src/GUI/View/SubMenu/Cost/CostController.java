package GUI.View.SubMenu.Cost;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CostController {
    private double cost;
    private final Stage thisStage;

    @FXML
    private TextField costInput;
    @FXML
    private Button costSubmit;

    public CostController() {
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(CostController.class.getResource("Cost.fxml")));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Tìm kiếm theo chi phí");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.initModality(Modality.APPLICATION_MODAL);
        thisStage.showAndWait();
    }

    public void submitClick() {
        this.costSubmit.setOnMouseClicked(mouseEvent -> {
            if (costInput.getText() != "") {
                this.cost = Double.parseDouble(costInput.getText());
                System.out.println(cost);
                thisStage.close();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert");
                alert.setHeaderText(null);
                alert.setContentText("Nhập chi phí!");
                alert.showAndWait();
            }
        });
    }

}
