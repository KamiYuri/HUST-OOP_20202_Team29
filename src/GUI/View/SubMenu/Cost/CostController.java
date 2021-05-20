package GUI.View.SubMenu.Cost;

import GUI.Controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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
            thisStage.getIcons().add(new Image("icons/cost1.png"));
            thisStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        costInput.setTextFormatter(new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            Pattern pattern = Pattern.compile("\\d{0,11}");
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        }));
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
                showResult();
                Controller.getInstance().getMainStageController().showUndo(true);
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert");
                alert.setHeaderText(null);
                alert.setContentText("Nhập chi phí!");
                alert.showAndWait();
            }
        });
    }
    public void showResult() {
        Controller.getInstance().findCost(this.cost);
    }

}
