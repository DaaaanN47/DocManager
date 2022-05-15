package sample;

import DocType.RequestForPayment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class RequestForPaymentController implements Initializable {

    @FXML
    private VBox docInfoGroup;

    @FXML
    private Label numberLabel;

    @FXML
    private TextField numberField;

    @FXML
    private Label userLabel;

    @FXML
    private TextField userField;

    @FXML
    private Label counterpartyLabel;

    @FXML
    private TextField counterpartyField;

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
    private Label comissionLabel;

    @FXML
    private TextField comissionField;

    @FXML
    private Button saveRequestForPaymentBtn;

    private Controller mainController;

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }
    @FXML
    void saveRequestForPayment(ActionEvent event) {
        if(userField.getText().isEmpty() || currencyField.getText().isEmpty() || counterpartyField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не все поля заполнены", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            RequestForPayment requestForPayment = new RequestForPayment(
                    userField.getText(),
                    counterpartyField.getText(),
                    Float.parseFloat(priceField.getText()),
                    currencyField.getText(),
                    Float.parseFloat(currencyRateField.getText()),
                    Float.parseFloat(comissionField.getText()));
                    mainController.AddDocInList(requestForPayment);
            Stage stage = (Stage) saveRequestForPaymentBtn.getScene().getWindow();
            stage.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveRequestForPaymentBtn.setDisable(true);
        userField.textProperty().addListener((observableValue, s, t1) -> {
            if (!userField.getText().isEmpty()) {
                saveRequestForPaymentBtn.setDisable(false);
            }
        });

        counterpartyField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!userField.getText().isEmpty()) {
                    saveRequestForPaymentBtn.setDisable(false);
                }
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
                        currencyRateField.setText(s);
                        return;
                    }
                }
            }
        });

        currencyField.textProperty().addListener((observableValue, s, t1) -> {
            if (!userField.getText().isEmpty()) {
                saveRequestForPaymentBtn.setDisable(false);
            }
        });

        comissionField.textProperty().addListener((observableValue, s, t1) -> {
            byte[] bytes = t1.getBytes(StandardCharsets.UTF_8);
            for(byte symbol : bytes){
                boolean isNumber = symbol > 48 && symbol < 58;
                if(!isNumber && !(symbol==46)){
                    comissionField.setText(s);
                    return;
                }
            }
        });
    }
}
