package GUI.View.MainStage;


import GUI.Modal.Order;
import GUI.View.OrderInformation.OrderInfHelper;
import GUI.View.SubMenu.Address.AddressController;
import GUI.View.SubMenu.Cost.CostController;
import GUI.View.SubMenu.Month.MonthController;
import GUI.View.SubMenu.SenderName.SenderNameController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStageController {

    //<editor-fold desc="Var">
    private final Stage thisStage;

    @FXML
    private TableView<Order> tableView;

    @FXML
    private TableColumn<Order, Number> id_col;

    @FXML
    private TableColumn<Order, String> senderName_col;

    @FXML
    private TableColumn<Order, String> senderPhone_col;
    @FXML
    private TableColumn<Order, String> receiverName_col;
    @FXML
    private TableColumn<Order, String> receiverPhone_col;
    @FXML
    private TableColumn<Order, String> address_col;
    @FXML
    private TableColumn<Order, Integer> cost_col;

    @FXML
    private TableRow<Order> tableRow;

    ObservableList<Order> obsList = FXCollections.observableArrayList();
    //</editor-fold>


    public MainStageController() {
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(MainStageController.class.getResource("MainStage.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Quản lý đơn hàng");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    public void initialize() {
        Order[] orderList = new Order[3];

        orderList[0] = new Order("senderName 1", "senderPhone 1", "receiverName 1",
                "receiverPhone 1","address 1",1,100, 1, 1, "01/11/2021");

        orderList[1] = new Order("senderName 2", "senderPhone 2", "receiverName 2",
                "receiverPhone 2","address 2",2,200, 2, 0, "02/11/2021");

        orderList[2] = new Order("senderName 3", "senderPhone 3", "receiverName 3",
                "receiverPhone 3","address 3",3,300, 3, 1, "03/11/2021");

        for(int i = 0; i < 3; i++) {
            obsList.add(orderList[i]);
        }

        tableView.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();

            ContextMenu contextMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Xoá");
            MenuItem editItem = new MenuItem("Chi tiết");

            contextMenu.getItems().addAll(removeItem, editItem);

            removeItem.setOnAction(actionEvent -> {
                obsList.remove(tv.getSelectionModel().getSelectedItem());
            });

            editItem.setOnAction(actionEvent -> {
                Order tmp = tv.getSelectionModel().getSelectedItem();
                obsList.set(obsList.indexOf(tmp), OrderInfHelper.showOrEditOrder(tmp));
            });

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu));
            return row ;
        });

        id_col.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(tableView.getItems().indexOf(column.getValue()) + 1));
        senderName_col.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        senderPhone_col.setCellValueFactory(new PropertyValueFactory<>("senderPhone"));

        receiverName_col.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        receiverPhone_col.setCellValueFactory(new PropertyValueFactory<>("receiverPhone"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        cost_col.setCellValueFactory(new PropertyValueFactory<>("cost"));

        tableView.setItems(obsList);
    }

    //<editor-fold desc="Phần các menu">
    //Menu thêm đơn hàng

    @FXML
    private  MenuItem add;

    @FXML
    public void addClick() {
        add.setOnAction(actionEvent -> {
            obsList.add(OrderInfHelper.makeNewOrder());
        });
    }

    //Menu người gửi//
    @FXML
    private MenuItem sender;

    @FXML
    public void senderClick() {
        sender.setOnAction(actionEvent -> {
                SenderNameController senderNameController = new SenderNameController();
                senderNameController.showStage();
            }
        );
    }

    //Menu địa chỉ//
    @FXML
    private MenuItem address;

    @FXML
    public void addressClick() {
        address.setOnAction(actionEvent -> {
                AddressController addressController = new AddressController();
                addressController.showStage();
            }
        );
    }

    //Menu giá//
    @FXML
    private MenuItem cost;

    @FXML
    public void costClick() {
        cost.setOnAction(actionEvent -> {
                CostController costController = new CostController();
                costController.showStage();
            }
        );
    }

    //Menu thống kê//
    @FXML
    private MenuItem month;

    @FXML
    public void monthClick() {
        month.setOnAction(actionEvent -> {
                MonthController monthController = new MonthController();
                monthController.showStage();
            }
        );
    }
    //</editor-fold>
}


