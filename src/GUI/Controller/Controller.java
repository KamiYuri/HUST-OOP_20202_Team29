package GUI.Controller;

import BackEnd.Management;
import GUI.Modal.Modal;
import GUI.View.MainStage.MainStageController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class Controller {
    private Modal[] modalList, save;

    private Alert alert;
    private ObservableList<Modal> obsModalList;

    public MainStageController getMainStageController() {
        return mainStageController;
    }

    private MainStageController mainStageController;

    private String addressToFind;
    public String getAddressToFind() {
        return addressToFind;
    }

    public void setAddressToFind(String addressToFind) {
        this.addressToFind = addressToFind;
    }


    private Controller(){
        initAlert();
    }

    public double calcCost(Modal modal) {
        return Management.getInstance().calcCost(modal);
    }

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

    public void closeAlert() {
        this.alert.close();
    }

    public void findAddress(String address) {
        Modal[] result = Management.getInstance().SearchByAddress(address);
        if(result == null) {
            showAlert("Không tìm thấy đơn hàng hoặc đơn hàng không tồn tại.");
        }
        else{
            this.save = this.modalList;
            this.modalList = result;
            mainStageController.setOrderList(result);
        }
    }

    public void findName(String name) {
        Modal[] result = Management.getInstance().SearchOrder(name);
        if(result == null) {
            showAlert("Không tìm thấy đơn hàng hoặc đơn hàng không tồn tại.");
        }
        else{
            this.save = this.modalList;
            this.modalList = result;
            mainStageController.setOrderList(result);
        }
    }

    public void findCost(double cost) {
        Modal[] result = Management.getInstance().SearchOrder(cost);
        if(result == null) {
            showAlert("Không tìm thấy đơn hàng hoặc đơn hàng không tồn tại.");
        }
        else{
            this.save = this.modalList;
            this.modalList = result;
            mainStageController.setOrderList(result);
        }
    }

    public void findMonth(int month) {
        Modal[] result = Management.getInstance().OrdersInAmonth(month);
        if(result == null) {
            showAlert("Không tìm thấy đơn hàng nào trong tháng");
        }
        else{
            this.save = this.modalList;
            this.modalList = result;
            mainStageController.setOrderList(result);
        }
    }

    public String getIncomeInMonth(int month) {
        return Double.toString(Management.getInstance().incomeInAmonth(month));
    }


    private static class ControllerHelper{
        private static final Controller INSTANCE = new Controller();
    }

    public static Controller getInstance() {
        return ControllerHelper.INSTANCE;
    }

    private ObservableList<Modal> arrToObs(Modal[] modalList) {
        return FXCollections.observableArrayList(modalList);
    }

    private Modal[] obsToArr(ObservableList<Modal> modalObservableList) {
        Modal[] tmp = new Modal[obsModalList.size()];
        for(int i = 0; i < obsModalList.size(); i++){
            this.modalList[i] = obsModalList.get(i);
        }
        return tmp;
    }
    
    public void updateData(Modal[] modalList){
        setObsOrderList(arrToObs(modalList));
        setOrderList(modalList);
    }

    public void updateData(ObservableList<Modal> obsModalList) {
        setOrderList(obsToArr(obsModalList));
        setObsOrderList(obsModalList);
    }

    public Modal[] getOrderList() {
        return modalList;
    }

    public void setOrderList(Modal[] modalList) {
        this.modalList = modalList;
    }

    public ObservableList<Modal> getObsOrderList() {
        return obsModalList;
    }

    public void setObsOrderList(ObservableList<Modal> obsModalList) {
        this.obsModalList = obsModalList;
    }

    public void updateFromUI() {
        this.setOrderList(mainStageController.getOrderList());
        for(int i = 0; i < this.modalList.length; i++) {
            System.out.println(i + "\n");
            this.modalList[i].showIn4();
            System.out.println("\n");
        }
        Management.getInstance().updateFromController(this.modalList);
    }

    public void Start() {
        if(this.modalList == null){
            this.modalList = new Modal[0];
        }
        mainStageController = new MainStageController(this.modalList);
        mainStageController.showStage();
    }
}