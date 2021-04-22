package sample.GUI.Order;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.GUI.SubScene.Address.AddressController;
import sample.GUI.SubScene.Cost.CostController;
import sample.GUI.SubScene.Month.MonthController;
import sample.GUI.SubScene.Name.NameController;

import java.io.IOException;

public class OrderController {

    //Menu người gửi//
    @FXML
    private MenuItem sender;

    @FXML
    public void senderClick() {
        sender.setOnAction(actionEvent -> {
            try {
                Parent subWindow = FXMLLoader.load(NameController.class.getResource("Name.fxml"));
                Stage subStage = new Stage();
                subStage.initModality(Modality.WINDOW_MODAL);
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

    //Menu thống kê//
    @FXML
    private MenuItem month;

    @FXML
    public void monthClick() {
        month.setOnAction(actionEvent -> {
            try {
                Parent subWindow = FXMLLoader.load(MonthController.class.getResource("Month.fxml"));
                Stage subStage = new Stage();
                subStage.setScene(new Scene(subWindow));
                subStage.show();
            } catch (Exception e){

            }
        });
    }
}


