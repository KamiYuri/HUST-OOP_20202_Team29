package GUI.View.SubMenu.SenderName;

import GUI.Controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class SenderNameController {

    private String name;

    private final Stage thisStage;
    @FXML
    private TextField textInput;
    @FXML
    private Button submit;

    public SenderNameController() {
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(SenderNameController.class.getResource("SenderName.FXML")));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Tìm kiếm theo tên người gửi");
            thisStage.getIcons().add(new Image("icons/name1.png"));
            thisStage.setResizable(false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.initModality(Modality.APPLICATION_MODAL);
        thisStage.showAndWait();
    }

    public void initialize() {
        textInput.setText("");
        textInput.setTextFormatter(new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            Pattern pattern = Pattern.compile("^[\\p{L} ]{0,20}+(\\s[\\p{L} ]+)?$");
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        }));
    }

    @FXML
    public void submitClick() {
        this.submit.setOnMouseClicked(mouseEvent -> {
            if (textInput.getText() != "") {
                this.name = textInput.getText();
                System.out.println(name);
                thisStage.close();
                showResult();
                Controller.getInstance().getMainStageController().showUndo(true);
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert");
                alert.setHeaderText(null);
                alert.setContentText("Nhập tên khách hàng!");
                alert.showAndWait();
            }
        });
    }

    public String getInput() {
        return name;
    }
    public void showResult() {
        Controller.getInstance().findName(this.name);
    }
}
