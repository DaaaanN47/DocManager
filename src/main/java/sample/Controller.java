package sample;

import DocType.PaymentOrder;
import DocType.RequestForPayment;
import DocType.WayBill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;



public class Controller {

    public Button waybillBtn;
    public Button paymentOrderBtn;
    public Button RequestForPaymentBtn;
    public Button saveFileBtn;
    public Button loadFileBtn;
    public Button displayFileBtn;
    public Button exitBtn;
    public ListView documentList = new ListView();
    public AnchorPane MainPanel;

    public JsonConverter jsonConverter = new JsonConverter();
    public ArrayList<DocumentParent> docList= new ArrayList<>() {};

    public void AddDocInList(DocumentParent documentParent){
        docList.add(documentParent);
        System.out.println(documentParent.getShortDocInfo());
        documentList.getItems().add(documentParent);
        documentList.refresh();
    }

    @FXML
    public void createWayBill(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Waybill.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            WayBillController wayBillController = fxmlLoader.getController();
            wayBillController.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Накладная");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(Exception e) {
            System.out.println("Не могу открыть страницу");
        }
    }

    public void createPaymentOrder(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PaymentOrder.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            PaymentOrderController paymentOrderController = fxmlLoader.getController();
            paymentOrderController.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Платежка");
            stage.setScene(new Scene(root1));
            stage.show();;
        }
        catch(Exception e) {
            System.out.println("Не могу открыть страницу");
        }
    }

    public void createRequestForPayment(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/RequestForPayment.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            RequestForPaymentController requestForPaymentController = fxmlLoader.getController();
            requestForPaymentController.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Заявка на оплату");
            stage.setScene(new Scene(root1));
            stage.show();;
        }
        catch(Exception e) {
            System.out.println("Не могу открыть страницу");
        }
    }
    //методы возвращающие объект из списков
    public WayBill getWayBill(DocumentParent documentParent){
        String docNumber = documentParent.getDocNumber();
        for(int i = 0; i < docList.size(); i++){
            if(docList.get(i).getDocNumber() == docNumber){
                WayBill wayBill = (WayBill) docList.get(i);
                return wayBill;
            }
       }
        return null;
    }

    public PaymentOrder getPaymentOrder(DocumentParent documentParent){
        String docNumber = documentParent.getDocNumber();
        for(int i = 0; i < docList.size(); i++){
            if(docList.get(i).getDocNumber() == docNumber){
                PaymentOrder paymentOrder = (PaymentOrder) docList.get(i);
                return paymentOrder;
            }
        }
        return null;
    }

    public RequestForPayment getRequestForPayment(DocumentParent documentParent){
        String docNumber = documentParent.getDocNumber();
        for(int i = 0; i < docList.size(); i++){
            if(docList.get(i).getDocNumber() == docNumber){
                RequestForPayment requestForPayment = (RequestForPayment) docList.get(i);
                return requestForPayment;
            }
        }
        return null;
    }

    //Достает из листа нужный документ, проводит его к потомку и превращает в Json
    public String getJsonString(DocumentParent documentParent) throws IOException, ParseException {

        if(documentParent.getClass() == WayBill.class){
            WayBill wayBill = getWayBill(documentParent);
            return jsonConverter.ConvertWayBill(wayBill);
        }
        else if (documentParent.getClass() == PaymentOrder.class){
            PaymentOrder paymentOrder = getPaymentOrder(documentParent);
            return  jsonConverter.ConvertPaymentOrder(paymentOrder);
        }
        else if(documentParent.getClass() == RequestForPayment.class){
            RequestForPayment requestForPayment = getRequestForPayment(documentParent);
            return jsonConverter.ConvertRequestForPayment(requestForPayment);
        }
        else{return null;}
    }

    // Сохранение дока, для сохранения по дефолту открывается окно с адресом папки в проекте
    public void saveDocument(ActionEvent actionEvent) {
        String dir = System.getProperty("user.dir") + "\\" + "Documents";
        FileChooser fileChooser = new FileChooser();
        DocumentParent documentParent = (DocumentParent) documentList.getSelectionModel().getSelectedItem();
        fileChooser.setInitialDirectory(new File(dir));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        Window window = documentList.getScene().getWindow();
        fileChooser.setTitle("Сохарнене файла");
        fileChooser.setInitialFileName(documentParent.getShortDocInfo() + ".txt");
        try{
            File file = fileChooser.showSaveDialog(window);
            if(file!=null){
                Files.createFile(file.toPath());
                String docInfo  = getJsonString((DocumentParent) documentList.getSelectionModel().getSelectedItem());
                Files.write(file.toPath(), docInfo.getBytes());
            }
        }
        catch (IOException | ParseException e){
            System.out.println("error");
        }
    }
    // Загрузка файла и пмоещение  его в список документов и в окно интерфейса
    public void LoadFile(ActionEvent actionEvent) throws IOException, ParseException {
        String dir = System.getProperty("user.dir") + "\\" + "Documents";
        FileChooser fileChooser = new FileChooser();
        DocumentParent documentParent = (DocumentParent) documentList.getSelectionModel().getSelectedItem();

        fileChooser.setInitialDirectory(new File(dir));
        fileChooser.setTitle("Выбор файла");
        Window window = documentList.getScene().getWindow();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(window);
        if(file!=null){
            String s = Files.readString(Paths.get(String.valueOf(file.toPath())));
            if(s.startsWith("Н-",10)){
                WayBill wayBill =  jsonConverter.getWaybillJson(s);
                AddDocInList(wayBill);
            }
            else if(s.startsWith("П-",10)){
                PaymentOrder paymentOrder =  jsonConverter.getPaymentOrderJson(s);
                AddDocInList(paymentOrder);
            }
            else if(s.startsWith("ЗО-",10)){
                RequestForPayment requestForPayment =  jsonConverter.getRequestForPaymentJson(s);
                AddDocInList(requestForPayment);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Формат не поддерживается", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }
    // Достает из спика нужный док и создает строку для отображения при нажатии кнопки просмотра
    public String FindDocStrFromLists(DocumentParent documentParent){
        String s = documentParent.getDocNumber();
        if(s.startsWith("Н-")){
            return getWayBill(documentParent).getStringToShow();
        }
        if(s.startsWith("П-")){
           return getPaymentOrder(documentParent).getStringToShow();
        }
        if(s.startsWith("ЗО-")){
            return getRequestForPayment(documentParent).getStringToShow();
        }
        return null;
    }

    public void ShowDocument(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ShowDocumentWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ShowDocWindowController showDocWindowController = fxmlLoader.getController();
            showDocWindowController.setMainController(this);
            DocumentParent documentParent = (DocumentParent)documentList.getSelectionModel().getSelectedItem();
            showDocWindowController.setTextInTxtField(FindDocStrFromLists(documentParent));
            Stage stage = new Stage();
            stage.setTitle(documentParent.getClassName());
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch(Exception e) {
            System.out.println("Не могу открыть страницу");
        }
    }

    public void clickCloseWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) loadFileBtn.getScene().getWindow();
        stage.close();
    }
}
