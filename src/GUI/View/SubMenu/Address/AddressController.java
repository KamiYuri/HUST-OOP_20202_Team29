package GUI.View.SubMenu.Address;

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

public class AddressController {
    private String address;

    private final Stage thisStage;
    @FXML
    private TextField addressInput;
    @FXML
    private Button submit;

    public AddressController() {
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(AddressController.class.getResource("Address.fxml")));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Tìm kiếm theo địa chỉ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.initModality(Modality.APPLICATION_MODAL);
        thisStage.showAndWait();
    }

    public void initialize() {
        addressInput.setText("");
    }

    @FXML
    public void submitClick() {
        this.submit.setOnMouseClicked(mouseEvent -> {
            if (addressInput.getText() != "") {
                this.address = addressInput.getText();
                System.out.println(address);
                thisStage.close();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert");
                alert.setHeaderText(null);
                alert.setContentText("Nhập một địa chỉ!");
                alert.showAndWait();
            }
        });
    }

    public String getInput() {
        return address;
    }
}
