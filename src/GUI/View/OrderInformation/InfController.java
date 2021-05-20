//Hoàng Việt Dũng
//Nguyễn Việt Tùng

//Lớp quản lý cửa sở tạo mới hoặc đọc đơn hàng

package GUI.View.OrderInformation;

import GUI.Controller.Controller;
import GUI.Modal.Model;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
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
    private ChoiceBox<String> wayInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private final ObservableList<String> wayList = FXCollections.observableArrayList("Đường bộ", "Đường hàng không");

    private Model model;
    private static boolean submitFlag = false;

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
        this.model = new Model();
        setStage();
    }

    //Contructor dùng khi muốn hiện thị và chỉnh sửa 1 đơn hàng đã tồn tại
    public InfController(Model model) {
        NEW_ORDER = false;
        this.model = model;
        setStage();
    }

    private void setStage() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(InfController.class.getResource("Inf.fxml")));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.initModality(Modality.APPLICATION_MODAL);
            thisStage.setResizable(false);
            thisStage.setTitle("Thông tin chi tiết đơn hàng");
            thisStage.getIcons().add(new Image("icons/inf.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        thisStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, windowEvent -> {
            if(!submitFlag){
                this.model = null;
            }
        });

        senderNameInput.setTextFormatter(getFomatter("^[\\p{L} ]{0,20}+(\\s[\\p{L} ]+)?$"));
        receiverNameInput.setTextFormatter(getFomatter("^[\\p{L} ]{0,20}+(\\s[\\p{L} ]+)?$"));
        senderPhoneInput.setTextFormatter(getFomatter("\\d{0,11}"));
        receiverPhoneInput.setTextFormatter(getFomatter("\\d{0,11}"));

        weightInput.setTextFormatter(getFomatter("(^100$)|(^\\d{0,2}$)|(^[0-9]?[0-9]\\.(\\d{0,3}+)$)"));
        distanceInput.setTextFormatter(getFomatter("(^1000$)|(^\\d{0,3}$)|(^[0-9]?[0-9]?[0-9]\\.(\\d{0,3}+)$)"));

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
            senderNameInput.setText(this.model.getSenderName());
            senderPhoneInput.setText(this.model.getSenderPhone());
            receiverNameInput.setText(this.model.getReceiverName());
            receiverPhoneInput.setText(this.model.getReceiverPhone());
            addressInput.setText(this.model.getAddress());
            weightInput.setText(Double.toString(this.model.getWeight()));
            distanceInput.setText(Double.toString(this.model.getDistance()));
            wayInput.setValue(wayList.get(this.model.getShipping()));
            cost.setText(Double.toString(this.model.getCost()));
            dateInput.setValue(convertStringToDate(this.model.getDate()));
        }
    }

    @SuppressWarnings("unchecked")
    private TextFormatter getFomatter(String string) {
        Pattern pattern = Pattern.compile(string);

        return new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> pattern
                .matcher(change.getControlNewText()).matches() ? change : null);
    }

    public Model getOrder() {
        return model;
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
        this.dateInput.setVisible(visible);
        this.wayInput.setVisible(visible);
    }

    @FXML
    public void editClick() {
        this.edit.setOnMouseClicked(mouseEvent -> swapScene(SCENE.EDIT));
    }

    @FXML
    public void submitClick() {
        this.submit.setOnMouseClicked(mouseEvent -> {
            if(isInputNull()){
                submitFlag = true;
                update();
                swapScene(SCENE.SHOW);
            }
        });
    }

    public void update() {
        model.setSenderName(senderNameInput.getText());
        model.setSenderPhone(senderPhoneInput.getText());
        model.setReceiverName(receiverNameInput.getText());
        model.setReceiverPhone(receiverPhoneInput.getText());
        model.setAddress(addressInput.getText());
        model.setWeight(Double.parseDouble(weightInput.getText()));
        model.setDistance(Double.parseDouble(distanceInput.getText()));
        model.setShipping(wayList.indexOf(wayInput.getValue()));
        model.setDate(convertDateToString(dateInput.getValue()));
        model.setCost(Controller.getInstance().calcCost(this.model));
        model.setShippingProperty(model.getShipping());
        costInput.setText(Double.toString(model.getCost()));
    }

    @FXML
    public void doneClick() {
        this.done.setOnMouseClicked(mouseEvent -> {
            submitFlag = false;
            update();
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
        boolean flag = true;
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