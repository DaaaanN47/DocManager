package DocType;


import org.json.JSONObject;
import sample.DocumentParent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WayBill extends DocumentParent {

    String user;
    String currency;
    float currencyRate;
    String product;
    float amount;
    float price;
    public String getUser(){
        return this.user;
    }

    public float getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public float getCurrencyRate() {
        return currencyRate;
    }

    public String getProduct() {
        return product;
    }

    public float getAmount() {
        return amount;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCurrencyRate(float currencyRate) {
        this.currencyRate = currencyRate;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public WayBill(String user, float price, String currency, float currencyRate, String product, float amount){
        this.setDate(new Date());
        this.setDocNumber(getRandomDocNumber());
        this.setPrice(price*currencyRate);
        this.setUser(user);
        this.setCurrency(currency);
        this.setCurrencyRate(currencyRate);
        this.setProduct(product);
        this.setAmount(amount);
    }

    public WayBill(JSONObject jsonObject) throws ParseException {
        this.setDate((Date) formatForDateNow.parseObject(jsonObject.get("Дата").toString()));
        this.setDocNumber(jsonObject.get("Номер").toString());
        this.setPrice(Float.parseFloat(jsonObject.get("Цена").toString()));
        this.setUser(jsonObject.get("Пользователь").toString());
        this.setCurrency(jsonObject.get("Валюта").toString());
        this.setCurrencyRate(Float.parseFloat(jsonObject.get("Курс валюты").toString()));
        this.setProduct(jsonObject.get("Товар").toString());
        this.setAmount(Float.parseFloat(jsonObject.get("Количество").toString()));
    }


    @Override
    public String getStringToShow(){
        return  "Номер: " + this.getDocNumber() + "\n" +
                "Дата: " + formatForDateNow.format(this.getDocDate()) + "\n" +
                "Пользователь: " + this.getUser() + "\n" +
                "Цена: " + this.getPrice() + "\n" +
                "Валюта: " + this.getCurrency() + "\n" +
                "Курс валюты: " + this.getCurrencyRate() + "\n" +
                "Товар: " + this.getProduct() + "\n" +
                "Колличество: " + this.getAmount();
    }

    @Override
    public String getDocMark(){
        return "Н-";
    }

    public String getClassName(){
        return "Накладная";
    }


}
