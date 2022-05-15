package DocType;

import org.json.JSONObject;
import sample.DocumentParent;

import java.text.ParseException;
import java.util.Date;

public class RequestForPayment extends DocumentParent {

    String user;
    String counterparty;
    float price;
    String currency;
    float currencyRate;
    float commission;

    public void setUser(String user) {
        this.user = user;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCurrencyRate(float currencyRate) {
        this.currencyRate = currencyRate;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    public String getUser() {
        return user;
    }

    public String getCounterparty() {
        return counterparty;
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

    public float getCommission() {
        return commission;
    }

    public RequestForPayment(JSONObject jsonObject) throws ParseException {
        this.setDate((Date) formatForDateNow.parseObject(jsonObject.get("Дата").toString()));
        this.setDocNumber(jsonObject.get("Номер").toString());
        this.setPrice(Float.parseFloat(jsonObject.get("Цена").toString()));
        this.setUser(jsonObject.get("Пользователь").toString());
        this.setCurrency(jsonObject.get("Валюта").toString());
        this.setCurrencyRate(Float.parseFloat(jsonObject.get("Курс валюты").toString()));
        this.setCounterparty(jsonObject.get("Контрагент").toString());
        this.setCommission(Float.parseFloat(jsonObject.get("Комиссия").toString()));
    }

    public RequestForPayment(String user, String counterparty, float price, String currency, float currencyRate, float commission){
        this.setDate(new Date());
        this.setDocNumber(getRandomDocNumber());
        this.setPrice(price*currencyRate);
        this.setUser(user);
        this.setCurrency(currency);
        this.setCurrencyRate(currencyRate);
        this.setCounterparty(counterparty);
        this.setCommission(commission);
    }

    @Override
    public String getStringToShow(){
        return  "Номер: " + this.getDocNumber() + "\n" +
                "Дата: " + formatForDateNow.format(this.getDocDate()) + "\n" +
                "Пользователь: " + this.getUser() + "\n" +
                "Контрагент: " + this.getCounterparty() + "\n" +
                "Цена: " + this.getPrice() + "\n" +
                "Валюта: " + this.getCurrency() + "\n" +
                "Курс валюты: " + this.getCurrencyRate() + "\n" +
                "Комиссия: " + this.getCommission() ;
    }

    @Override
    public String getClassName(){
        return "Запрос на оплату";
    }

    @Override
    public String getDocMark(){
        return "ЗО-";
    }

}
