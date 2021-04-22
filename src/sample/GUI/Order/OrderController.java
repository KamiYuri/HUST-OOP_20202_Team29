package sample.GUI.Order;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import sample.GUI.SubScene.Address.AddressController;
import sample.GUI.SubScene.Cost.CostController;
import sample.GUI.SubScene.Name.NameController;

import java.io.IOException;

public class OrderController {

//    //Menu chọn tháng//
//    ObservableList<String> monthList = FXCollections
//            .observableArrayList(
//                    "Tháng 1", "Tháng 2", "Tháng 3",
//                    "Tháng 4", "Tháng 5", "Tháng 6",
//                    "Tháng 7", "Tháng 8", "Tháng 9",
//                    "Tháng 10", "Tháng 11", "Tháng 12"
//            );
//    @FXML
//    private ChoiceBox monthSelect;
//
//
//    @FXML
//    private void initialize() {
//        monthSelect.setValue("Chọn một tháng");
//        monthSelect.setItems(monthList);
//    }

    //Menu người gửi//
    @FXML
    private MenuItem sender;

    @FXML
    public void senderClick() {
        sender.setOnAction(actionEvent -> {
            try {
                Parent subWindow = FXMLLoader.load(NameController.class.getResource("sample/GUI/SubScene/Name/Name.fxml"));
                Stage subStage = new Stage();
                subStage.setScene(new Scene(subWindow));
                subStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        );
    }

    //Menu địa chỉ//
    @FXML
    private MenuItem address;

    @FXML
    public void addressClick() {
        address.setOnAction(actionEvent -> {
            try {
                Parent subWindow = FXMLLoader.load(AddressController.class.getResource("Address.fxml"));
                Stage subStage = new Stage();
                subStage.setScene(new Scene(subWindow));
                subStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        );
    }

    //Menu giá//
    @FXML
    private MenuItem cost;

    @FXML
    public void costClick() {
        cost.setOnAction(actionEvent -> {
            try {
                Parent subWindow = FXMLLoader.load(CostController.class.getResource("Cost.fxml"));
                Stage subStage = new Stage();
                subStage.setScene(new Scene(subWindow));
                subStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        );
    }


}


