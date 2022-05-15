package sample;

import DocType.WayBill;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;


public class WayBillController implements Initializable{
    public javafx.scene.control.Button Button;

    @FXML
    private Label numberLabel;

    @FXML
    private TextField numberField;

    @FXML
    private Label userLabel;

    @FXML
    private TextField userField;

    @FXML
    private Label priceLabel;

    @FXML
    private TextField priceField;

    @FXML
    private Label currencyLabel;

    @FXML
    private TextField currencyField;

    @FXML
    private Label currencyRateLabel;

    @FXML
    private TextField currencyRateField;

    @FXML
    private Label productLabel;

    @FXML
    private TextField productField;

    @FXML
    private Label amountLabel;

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> currencyCBox;

    @FXML
    private Button saveWayBillBtn;

    private Controller mainController;

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void SaveWayBill(ActionEvent actionEvent) {
        if(userField.getText().isEmpty() || currencyField.getText().isEmpty() || productField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не все поля заполнены", ButtonType.OK);
            alert.showAndWait();
        }
        else
        {
            WayBill wayBill = new WayBill(
                userField.getText(),
                Float.parseFloat(priceField.getText()),
                currencyField.getText(),
                Float.parseFloat(currencyRateField.getText()),
                productField.getText(),
                Float.parseFloat(amountField.getText()));
            mainController.AddDocInList(wayBill);
        }
        Stage stage = (Stage) saveWayBillBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        saveWayBillBtn.setDisable(true);
        userField.textProperty().addListener((ObservableValue<? extends String> observableValue, String s, String t1) -> {
            if (!userField.getText().isEmpty()) {
                saveWayBillBtn.setDisable(false);
            }
        });

        priceField.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                byte[] bytes = t1.getBytes(StandardCharsets.UTF_8);
                for(byte symbol : bytes){
                    boolean isNumber = symbol > 48 && symbol < 58;
                    if(!isNumber && !(symbol==46)){
                        priceField.setText(s);
                        return;
                    }
                }
            }
        });

        currencyRateField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                byte[] bytes = t1.getBytes(StandardCharsets.UTF_8);
                for(byte symbol : bytes){
                    boolean isNumber = symbol > 48 && symbol < 58;
                    if(!isNumber && !(symbol==46)){
                        priceField.setText(s);
                        return;
                    }
                }
            }
        });
        amountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                byte[] bytes = t1.getBytes(StandardCharsets.UTF_8);
                for(byte symbol : bytes){
                    boolean isNumber = symbol > 48 && symbol < 58;
                    if(!isNumber && !(symbol==46)){
                        priceField.setText(s);
                        return;
                    }
                }
            }
        });
        currencyField.textProperty().addListener((observableValue, s, t1) -> {
            if (!userField.getText().isEmpty()) {
                saveWayBillBtn.setDisable(false);
            }
        });
        productField.textProperty().addListener((observableValue, s, t1) -> {
            if (!userField.getText().isEmpty()) {
                saveWayBillBtn.setDisable(false);
            }
        });
    }
}
