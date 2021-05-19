package GUI.View.SubMenu.SenderName;

import GUI.Controller.Controller;
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

public class SenderNameController {

    private String name;

    private final Stage thisStage;
    @FXML
    private TextField textInput;
    @FXML
    private Button submit;

    public SenderNameController() {
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(SenderNameController.class.getResource("SenderName.FXML")));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Tìm kiếm theo tên khách hàng");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.initModality(Modality.APPLICATION_MODAL);
        thisStage.showAndWait();
    }

    public void initialize() {
        textInput.setText("");
    }

    @FXML
    public void submitClick() {
        this.submit.setOnMouseClicked(mouseEvent -> {
            if (textInput.getText() != "") {
                this.name = textInput.getText();
                System.out.println(name);
                thisStage.close();
                showResult();
                Controller.getInstance().getMainStageController().showUndo(true);
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert");
                alert.setHeaderText(null);
                alert.setContentText("Nhập tên khách hàng!");
                alert.showAndWait();
            }
        });
    }

    public String getInput() {
        return name;
    }
    public void showResult() {
        Controller.getInstance().findName(this.name);
    }
}
