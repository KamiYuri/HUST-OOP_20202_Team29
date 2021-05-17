package GUI.View.OrderInformation;

import GUI.Modal.Order;
import javafx.beans.binding.Bindings;
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
    private TextField senderNameInput, senderPhoneInput, receiverNameInput, receiverPhoneInput, addressInput, weightInput, distanceInput;
    @FXML
    private ChoiceBox wayInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private final ObservableList<String> wayList = FXCollections.observableArrayList("Đường bộ", "Đường hàng không");

    private Order order;

    private Label[] getLabels() {
        Label[] arr = new Label[10];
        arr[0] = senderPhone;
        arr[1] = receiverName;
        arr[2] = receiverPhone;
        arr[3] = address;
        arr[4] = weight;
        arr[5] = distance;
        arr[6] = cost;
        arr[7] = date;
        arr[8] = way;
        arr[9] = senderName;
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
        this.order = new Order();
        setStage();
    }

    //Contructor dùng khi muốn hiện thị và chỉnh sửa 1 đơn hàng đã tồn tại
    public InfController(Order order) {
        NEW_ORDER = false;
        this.order = order;
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
            cost.setText("0");
            dateInput.setValue(LocalDate.now());
        }
        else {
            senderNameInput.setText(this.order.getSenderName());
            senderPhoneInput.setText(this.order.getSenderPhone());
            receiverNameInput.setText(this.order.getReceiverName());
            receiverPhoneInput.setText(this.order.getReceiverPhone());
            addressInput.setText(this.order.getAddress());
            weightInput.setText(Integer.toString(this.order.getWeight()));
            distanceInput.setText(Integer.toString(this.order.getDistance()));
            wayInput.setValue(wayList.get(this.order.getWay()));
            cost.setText(Integer.toString(this.order.getCost()));
            dateInput.setValue(convertStringToDate(this.order.getDate()));
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
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
                swapScene(SCENE.SHOW);
            }
        });
    }

    public void update() {
        order.setSenderName(senderNameInput.getText());
        order.setSenderPhone(senderPhoneInput.getText());
        order.setReceiverName(receiverNameInput.getText());
        order.setReceiverPhone(receiverPhoneInput.getText());
        order.setAddress(addressInput.getText());
        order.setWeight(Integer.parseInt(weightInput.getText()));
        order.setDistance(Integer.parseInt(distanceInput.getText()));
        order.setCost(Integer.parseInt(cost.getText()));
        order.setWay(wayList.indexOf(wayInput.getValue()));
        order.setDate(convertDateToString(dateInput.getValue()));
    }

    @FXML
    public void doneClick() {
        this.done.setOnMouseClicked(mouseEvent -> {
            System.out.println("done clicked");
            update();
//            this.order.showIn4();
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