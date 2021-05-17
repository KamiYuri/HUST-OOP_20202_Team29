package sample.GUI.MainScene;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import sample.GUI.Main;

public class DonHang_Controller {

    private Main main;

    @FXML
    private void goInput() {

    }


    //Menu chọn tháng//
    ObservableList<String> monthList = FXCollections
        .observableArrayList(
            "Tháng 1", "Tháng 2", "Tháng 3",
                "Tháng 4", "Tháng 5", "Tháng 6",
                "Tháng 7", "Tháng 8","Tháng 9",
                "Tháng 10", "Tháng 11", "Tháng 12"
    );

    @FXML
    private ChoiceBox monthSelect;

    @FXML
    private void initialize(){
        monthSelect.setValue("Chọn một tháng");
        monthSelect.setItems(monthList);
    }







}


