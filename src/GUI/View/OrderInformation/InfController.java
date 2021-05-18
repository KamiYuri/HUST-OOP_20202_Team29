package GUI.View.OrderInformation;

import GUI.Controller.Controller;
import GUI.Modal.Modal;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class InfController {
    private boolean NEW_ORDER = true;
    private final Stage thisStage = new Stage();

    @FXML
    private Label senderPhone, receiverName, receiverPhone, address, weight, distance, cost, date, way, senderName, warningDateLabel;
    @FXML
    private Button edit, submit, done;
    @FXML
    private TextField senderNameInput, senderPhoneInput, receiverNameInput, receiverPhoneInput, addressInput, weightInput, distanceInput, costInput;
    @FXML
    private ChoiceBox wayInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private final ObservableList<String> wayList = FXCollections.observableArrayList("Đường bộ", "Đường hàng không");

    private Modal modal;

    private Label[] getLabels() {
        Label[] arr = new Label[9];
        arr[0] = senderPhone;
        arr[1] = receiverName;
        arr[2] = receiverPhone;
        arr[3] = address;
        arr[4] = weight;
        arr[5] = distance;
        arr[6] = date;
        arr[7] = way;
        arr[8] = senderName;
        return arr;
    }

    private TextField[] getTextfields() {
        TextField[] arr = new TextField[7];
        arr[0] = this.senderNameInput;
        arr[1] = this.senderPhoneInput;
        arr[2] = this.receiverNameInput;
        arr[3] = this.receiverPhoneInput;
        arr[4] = this.addressInput;
        arr[5] = this.weightInput;
        arr[6] = this.distanceInput;
        return arr;
    }

    public enum SCENE {
        EDIT,
        SHOW
    }


    //Contructor dùng khi muốn tạo mới đơn hàng
    public InfController() {
        this.modal = new Modal();
        setStage();
    }

    //Contructor dùng khi muốn hiện thị và chỉnh sửa 1 đơn hàng đã tồn tại
    public InfController(Modal modal) {
        NEW_ORDER = false;
        this.modal = modal;
        setStage();
    }

    private void setStage() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(InfController.class.getResource("Inf.fxml")));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.initModality(Modality.APPLICATION_MODAL);
            thisStage.setResizable(false);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        bindData();
        wayInput.setItems(this.wayList);

        if(NEW_ORDER){
            swapScene(SCENE.EDIT);
            senderNameInput.setText("");
            senderPhoneInput.setText("");
            receiverNameInput.setText("");
            receiverPhoneInput.setText("");
            addressInput.setText("");
            weightInput.setText("");
            distanceInput.setText("");
            wayInput.setValue(wayList.get(0));
            costInput.setText("");
            dateInput.setValue(LocalDate.now());
        }
        else {
            senderNameInput.setText(this.modal.getSenderName());
            senderPhoneInput.setText(this.modal.getSenderPhone());
            receiverNameInput.setText(this.modal.getReceiverName());
            receiverPhoneInput.setText(this.modal.getReceiverPhone());
            addressInput.setText(this.modal.getAddress());
            weightInput.setText(Double.toString(this.modal.getWeight()));
            distanceInput.setText(Double.toString(this.modal.getDistance()));
            wayInput.setValue(wayList.get(this.modal.getShipping()));
            cost.setText(Double.toString(this.modal.getCost()));
            dateInput.setValue(convertStringToDate(this.modal.getDate()));
        }
    }

    public void setOrder(Modal modal) {
        this.modal = modal;
    }

    public Modal getOrder() {
        return modal;
    }

    public void showScene() {
        thisStage.showAndWait();
    }

    public void swapScene(SCENE scene) {
        showInfScene((scene == SCENE.SHOW));
        editInfScene((scene == SCENE.EDIT));
    }

    private void showInfScene(boolean visible) {
        for (Label label : getLabels()) {
            label.setVisible(visible);
        }

        this.done.setVisible(visible);
        this.edit.setVisible(visible);
    }

    private void editInfScene(boolean visible) {

        this.submit.setVisible(visible);

        this.senderNameInput.setText(this.senderName.getText());
        this.senderNameInput.setVisible(visible);
        this.senderPhoneInput.setText(this.senderPhone.getText());
        this.senderPhoneInput.setVisible(visible);
        this.receiverNameInput.setText(this.receiverName.getText());
        this.receiverNameInput.setVisible(visible);
        this.receiverPhoneInput.setText(this.receiverPhone.getText());
        this.receiverPhoneInput.setVisible(visible);
        this.addressInput.setText(this.address.getText());
        this.addressInput.setVisible(visible);
        this.weightInput.setText(this.weight.getText());
        this.weightInput.setVisible(visible);
        this.distanceInput.setText(this.distance.getText());
        this.distanceInput.setVisible(visible);

//        this.dateInput.setValue(this.convertStringToDate());
        this.dateInput.setVisible(visible);
        this.wayInput.setVisible(visible);
    }

    @FXML
    public void editClick() {
        this.edit.setOnMouseClicked(mouseEvent -> {
            System.out.println("edit clicked");
            swapScene(SCENE.EDIT);
        });
    }

    @FXML
    public void submitClick() {
        this.submit.setOnMouseClicked(mouseEvent -> {
            System.out.println("show clicked");
            if(isInputNull()){
                update();
//                cost.setText(Double.toString(this.modal.getCost()));
                swapScene(SCENE.SHOW);
            }
        });
    }

    public void update() {
        modal.setSenderName(senderNameInput.getText());
        modal.setSenderPhone(senderPhoneInput.getText());
        modal.setReceiverName(receiverNameInput.getText());
        modal.setReceiverPhone(receiverPhoneInput.getText());
        modal.setAddress(addressInput.getText());
        modal.setWeight(Double.parseDouble(weightInput.getText()));
        modal.setDistance(Double.parseDouble(distanceInput.getText()));
        modal.setShipping(wayList.indexOf(wayInput.getValue()));
        modal.setDate(convertDateToString(dateInput.getValue()));
        modal.setCost(Controller.getInstance().calcCost(this.modal));
        this.costInput.setText(Double.toString(modal.getCost()));


    }

    @FXML
    public void doneClick() {
        this.done.setOnMouseClicked(mouseEvent -> {
            System.out.println("done clicked");
            update();
//            this.modal.showIn4();
            this.thisStage.close();
        });
    }

    private void bindData(){
        senderNameInput.textProperty().bindBidirectional(senderName.textProperty());
        senderPhoneInput.textProperty().bindBidirectional(senderPhone.textProperty());
        receiverNameInput.textProperty().bindBidirectional(receiverName.textProperty());
        receiverPhoneInput.textProperty().bindBidirectional(receiverPhone.textProperty());
        addressInput.textProperty().bindBidirectional(address.textProperty());
        weightInput.textProperty().bindBidirectional(weight.textProperty());
        distanceInput.textProperty().bindBidirectional(distance.textProperty());
        costInput.textProperty().bindBidirectional(cost.textProperty());

        Bindings.bindBidirectional(date.textProperty(), dateInput.valueProperty(), new LocalDateStringConverter(DateTimeFormatter.ofPattern("dd/MM/yyyy"), null));

        way.textProperty().bind(wayInput.valueProperty());

    }

    private <T> void changeColor(T item, Color color) {
        if(color != null) {
            int depth = 20;
            DropShadow borderGlow = new DropShadow();
            borderGlow.setOffsetY(0f);
            borderGlow.setOffsetX(0f);
            borderGlow.setColor(color);
            borderGlow.setWidth(depth);
            borderGlow.setHeight(depth);
            ((Control)item).setEffect( borderGlow);
        }
        else
            ((Control)item).setEffect(null);
    }

    private boolean isInputNull() {
        Boolean flag = true;
        if(dateInput.getValue().equals(LocalDate.now())) {
            changeColor(dateInput, Color.YELLOW);
            warningDateLabel.setVisible(true);
        }
        else {
            changeColor(dateInput, null);
            warningDateLabel.setVisible(false);
        }
        TextField[] arr = getTextfields();
        for(int i = 0; i < 7; i++) {
            if(arr[i].getText().isEmpty()) {
                changeColor(arr[i], Color.RED);
                flag = false;
            }
            else {
                changeColor(arr[i], null);
            }
        }
        return flag;
    }


    private LocalDate convertStringToDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(string, formatter);
    }

    private String convertDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter); // "23/01/2021"
    }
}