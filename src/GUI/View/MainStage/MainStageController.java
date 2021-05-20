package GUI.View.MainStage;

import GUI.Controller.Controller;
import GUI.Modal.Model;
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
    private TableView<Model> tableView = new TableView<>();

    @FXML
    private TableColumn<Model, Number> id_col;

    @FXML
    private TableColumn<Model, String> name_col, address_col, shipping_col, date_col;
    
    @FXML
    private TableColumn<Model, Double> cost_col;

    @FXML
    private TableRow<Model> tableRow;

    ObservableList<Model> obsList = FXCollections.observableArrayList();
    //</editor-fold>

    public MainStageController(Model[] modelArr) {
        setOrderList(modelArr);
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

    @FXML
    public void initialize() {
        tableView.setStyle( "-fx-alignment: CENTER;");
        tableView.setRowFactory(tv -> {
            TableRow<Model> row = new TableRow<>();

            ContextMenu contextMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Xoá");
            MenuItem editItem = new MenuItem("Chi tiết");

            contextMenu.getItems().addAll(removeItem, editItem);

            removeItem.setOnAction(actionEvent -> {
                obsList.remove(tv.getSelectionModel().getSelectedItem());
                updateToController();
            });

            editItem.setOnAction(actionEvent -> {
                Model tmp = tv.getSelectionModel().getSelectedItem();
                obsList.set(obsList.indexOf(tmp), OrderInfHelper.showOrEditOrder(tmp));
                updateToController();
            });

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu));
            return row ;
        });

        id_col.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(column.getValue()) + 1));
        name_col.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        shipping_col.setCellValueFactory(new PropertyValueFactory<>("shippingProperty"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        cost_col.setCellValueFactory(new PropertyValueFactory<>("cost"));

        tableView.setItems(obsList);
    }

    private void updateToController() {
        Controller.getInstance().updateFromUI();
    }
    
    public void setOrderList(Model[] modelArr) {
        tableView.setItems(FXCollections.observableArrayList(modelArr));
    }

    public Model[] getOrderList() {
        Model[] modelList = new Model[obsList.size()];
        for(int i = 0; i < obsList.size(); i++) {
            modelList[i] = obsList.get(i);
        }

        return modelList;
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
        this.close.setOnMouseClicked(mouseEvent -> thisStage.close());
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
    private  Label add;

    @FXML
    public void addClick() {
        add.setOnMouseClicked(mouseEvent -> {
            Model tmp = OrderInfHelper.makeNewOrder();
            if(tmp != null){
                obsList.add(tmp);
                updateToController();
            }
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


