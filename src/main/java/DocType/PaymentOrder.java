package DocType;

import org.json.JSONObject;
import sample.DocumentParent;

import java.text.ParseException;
import java.util.Date;

public class PaymentOrder extends DocumentParent {

    String user;
    float price;
    String employee;

    public String getUser() {
        return user;
    }

    public float getPrice() {
        return price;
    }

    public String getEmployee() {
        return employee;
    }
    @Override
    public String getClassName(){
        return "Платежка";
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public PaymentOrder(JSONObject jsonObject) throws ParseException {
        this.setDate((Date) formatForDateNow.parseObject(jsonObject.get("Дата").toString()));
        this.setDocNumber(jsonObject.get("Номер").toString());
        this.setPrice(Float.parseFloat(jsonObject.get("Цена").toString()));
        this.setUser(jsonObject.get("Пользователь").toString());
        this.setEmployee(jsonObject.get("Сотрудник").toString());
    }

    public PaymentOrder( String user, float price, String employee){
        this.setDate(new Date());
        this.setDocNumber(getRandomDocNumber());
        this.setPrice(price);
        this.setUser(user);
        this.setEmployee(employee);
    }

    @Override
    public String getStringToShow(){
        return  "Номер: " + this.getDocNumber() + "\n" +
                "Дата: " + formatForDateNow.format(this.getDocDate()) + "\n" +
                "Пользователь: " + this.getUser() + "\n" +
                "Цена: " + this.getPrice() + "\n" +
                "Сотрудник: " + this.getEmployee();
    }

    @Override
    public String getDocMark(){
        return "П-";
    }

}
