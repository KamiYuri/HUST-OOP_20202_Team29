//Hoàng Việt Dũng

//Class quản lý việc trao đổi dữ liệu giữa UI và Back-end

package GUI.Controller;

import BackEnd.Management;

import GUI.Modal.Model;
import GUI.View.MainStage.MainStageController;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

@SuppressWarnings("ALL")
public class Controller {
    private Model[] modelList, save;

    private Alert alert;
    private ObservableList<Model> obsModelList;

    public MainStageController getMainStageController() {
        return mainStageController;
    }

    private MainStageController mainStageController;

    //<editor-fold desc="Singleton pattern">
    private Controller(){
        initAlert();
    }

    private static class ControllerHelper{
        private static final Controller INSTANCE = new Controller();
    }

    public static Controller getInstance() {
        return ControllerHelper.INSTANCE;
    }
    //</editor-fold>

    public void initAlert() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.initModality(Modality.WINDOW_MODAL);
    }

    public void showAlert(String string){
        alert.setContentText(string);
        this.alert.showAndWait();
    }

    public double calcCost(Model model) {
        return Management.getInstance().calcCost(model);
    }

    public void findAddress(String address) {
        Model[] result = Management.getInstance().SearchByAddress(address);
        if(result == null) {
            showAlert("Không tìm thấy đơn hàng hoặc đơn hàng không tồn tại.");
        }
        else{
            this.save = this.modelList;
            this.modelList = result;
            mainStageController.setOrderList(result);
        }
    }

    public void findName(String name) {
        Model[] result = Management.getInstance().SearchOrder(name);
        if(result == null) {
            showAlert("Không tìm thấy đơn hàng hoặc đơn hàng không tồn tại.");
        }
        else{
            this.save = this.modelList;
            this.modelList = result;
            mainStageController.setOrderList(result);
        }
    }

    public void findCost(double cost) {
        Model[] result = Management.getInstance().SearchOrder(cost);
        if(result == null) {
            showAlert("Không tìm thấy đơn hàng hoặc đơn hàng không tồn tại.");
        }
        else{
            this.save = this.modelList;
            this.modelList = result;
            mainStageController.setOrderList(result);
        }
    }

    public void findMonth(int month) {
        Model[] result = Management.getInstance().OrdersInAmonth(month);
        if(result == null) {
            showAlert("Không tìm thấy đơn hàng nào trong tháng");
        }
        else{
            this.save = this.modelList;
            this.modelList = result;
            mainStageController.setOrderList(result);
        }
    }

    public String getIncomeInMonth(int month) {
        return Double.toString(Management.getInstance().incomeInAmonth(month));
    }

    public Model[] getSave() {
        return save;
    }

    public void setSave(Model[] save) {
        this.save = save;
    }

    public Model[] getOrderList() {
        return modelList;
    }

    public void setOrderList(Model[] modelList) {
        this.modelList = modelList;
    }

    public void updateFromUI() {
        this.setOrderList(mainStageController.getOrderList());
        Management.getInstance().updateFromController(this.modelList);
    }

    public void Start() {
        if(this.modelList == null){
            this.modelList = new Model[0];
        }
        mainStageController = new MainStageController(this.modelList);
        mainStageController.showStage();
    }
}