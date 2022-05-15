package sample;

import DocType.PaymentOrder;
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

public class PaymentOrderController implements Initializable {

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
    private Label employeeLabel;

    @FXML
    private TextField employeeField;

    @FXML
    private Button savePaymentOrderBtn;

    private Controller mainController;

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    @FXML
    void savePaymentOrder(ActionEvent event) {
        if(userField.getText().isEmpty() || userField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не все поля заполнены", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            PaymentOrder paymentOrder = new PaymentOrder(
                    userField.getText(),
                    Float.parseFloat(priceField.getText()),
                    employeeField.getText());
                    mainController.AddDocInList(paymentOrder);

            Stage stage = (Stage) savePaymentOrderBtn.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        savePaymentOrderBtn.setDisable(true);
        userField.textProperty().addListener((observableValue, s, t1) -> {
            if (!userField.getText().isEmpty()) {
                savePaymentOrderBtn.setDisable(false);
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

        employeeField.textProperty().addListener((observableValue, s, t1) -> {
            if (!userField.getText().isEmpty()) {
                savePaymentOrderBtn.setDisable(false);
            }
        });
    }
}
