package GUI.View.MainStage;

import GUI.Controller.Controller;
import GUI.Modal.Modal;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStageController {

    //<editor-fold desc="Var">
    private final Stage thisStage;

    @FXML
    private Pane infIncome;

    @FXML
    private Label monthIncome, income;

    @FXML
    private Button undo, close;

    @FXML
    private TableView<Modal> tableView = new TableView<>();

    @FXML
    private TableColumn<Modal, Number> id_col;

    @FXML
    private TableColumn<Modal, String> senderName_col;

    @FXML
    private TableColumn<Modal, String> senderPhone_col;
    @FXML
    private TableColumn<Modal, String> receiverName_col;
    @FXML
    private TableColumn<Modal, String> receiverPhone_col;
    @FXML
    private TableColumn<Modal, String> address_col;
    @FXML
    private TableColumn<Modal, Integer> cost_col;

    @FXML
    private TableRow<Modal> tableRow;

    ObservableList<Modal> obsList = FXCollections.observableArrayList();
    //</editor-fold>

    public MainStageController(Modal[] modalArr) {
        setOrderList(modalArr);
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(MainStageController.class.getResource("MainStage.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Quản lý đơn hàng");
            thisStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        tableView.setStyle( "-fx-alignment: CENTER;");
        tableView.setRowFactory(tv -> {
            TableRow<Modal> row = new TableRow<>();

            ContextMenu contextMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Xoá");
            MenuItem editItem = new MenuItem("Chi tiết");

            contextMenu.getItems().addAll(removeItem, editItem);

            removeItem.setOnAction(actionEvent -> {
                obsList.remove(tv.getSelectionModel().getSelectedItem());
                updateToController();
            });

            editItem.setOnAction(actionEvent -> {
                Modal tmp = tv.getSelectionModel().getSelectedItem();
                obsList.set(obsList.indexOf(tmp), OrderInfHelper.showOrEditOrder(tmp));
                updateToController();
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



    private void updateToController() {
        Controller.getInstance().updateFromUI();
    }
    
    public void setOrderList(Modal[] modalArr) {
        tableView.setItems(FXCollections.observableArrayList(modalArr));
    }

    public Modal[] getOrderList() {
        Modal[] modalList = new Modal[obsList.size()];
        for(int i = 0; i < obsList.size(); i++) {
            modalList[i] = obsList.get(i);
        }

        return modalList;
    }

    public void updateFromController(){
        this.setOrderList(Controller.getInstance().getOrderList());
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    //<editor-fold desc="Phần các menu">

    public void showUndo(boolean visible) {
        this.undo.setVisible(visible);
    }

    @FXML
    public void closeClick() {
        this.close.setOnMouseClicked(mouseEvent -> {
            thisStage.close();
        });
    }
    
    @FXML
    public void undoClick() {
        this.undo.setOnMouseClicked(mouseEvent -> {
            tableView.setItems(this.obsList);
            this.infIncome.setVisible(false);
            this.undo.setVisible(false);
        });
    }

    //Menu thêm đơn hàng

    @FXML
    private  MenuItem add;

    @FXML
    public void addClick() {
        add.setOnAction(actionEvent -> {
            obsList.add(OrderInfHelper.makeNewOrder());
            updateToController();
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

    public void showIncome(int month) {
        this.infIncome.setVisible(true);
        this.monthIncome.setText(Integer.toString(month));
        this.income.setText(Controller.getInstance().getIncomeInMonth(month));
    }
    //</editor-fold>
}


