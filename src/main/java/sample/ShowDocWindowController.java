package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class ShowDocWindowController {

    @FXML
    private TextArea docInfoTAres;

    @FXML
    private Button okBtn;

    Controller mainController;

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }

    public void setTextInTxtField(String s){
        docInfoTAres.setText(s);
    }

}
