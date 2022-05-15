package sample;

import DocType.PaymentOrder;
import DocType.RequestForPayment;
import DocType.WayBill;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.*;


public class JsonConverter {
    //Конвертит объект в json строку
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy.");
    public String ConvertWayBill(WayBill wayBill) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Номер", wayBill.docNumber);
        jsonObject.put("Дата", formatForDateNow.format(wayBill.getDocDate()).toString());
        jsonObject.put("Пользователь", wayBill.getUser());
        jsonObject.put("Цена", wayBill.getPrice());
        jsonObject.put("Валюта",wayBill.getCurrency());
        jsonObject.put("Курс валюты", wayBill.getCurrencyRate());
        jsonObject.put("Товар", wayBill.getProduct());
        jsonObject.put("Количество",wayBill.getAmount());
        String s = "{\"Номер\":" + "\"" + jsonObject.get("Номер") + "\"" + "," + "\n" +
                "\"Дата\":" + "\"" + jsonObject.get("Дата") + "\"" + ","  + "\n" +
                "\"Пользователь\":" + "\"" + jsonObject.get("Пользователь") +  "\"" + "," + "\n" +
                "\"Цена\":" + jsonObject.get("Цена") + "," + "\n" +
                "\"Валюта\":" + "\"" + jsonObject.get("Валюта") + "\"" + "," + "\n" +
                "\"Курс валюты\":" + jsonObject.get("Курс валюты") + "," + "\n" +
                "\"Товар\":" + "\"" + jsonObject.get("Товар") + "\"" + "," + "\n" +
                "\"Количество\":" + jsonObject.get("Количество") + "}";
        return s;
    }

    public String ConvertPaymentOrder(PaymentOrder paymentOrder) throws IOException, ParseException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Номер", paymentOrder.docNumber);
        jsonObject.put("Дата", formatForDateNow.format(paymentOrder.getDocDate()).toString());
        jsonObject.put("Пользователь", paymentOrder.getUser());
        jsonObject.put("Цена", paymentOrder.getPrice());
        jsonObject.put("Сотрудник",paymentOrder.getEmployee());

        String s = "{\"Номер\":" + "\"" + jsonObject.get("Номер") + "\""  + "," + "\n" +
                "\"Дата\":" + "\"" + jsonObject.get("Дата") + "\""  + ","  + "\n" +
                "\"Пользователь\":" + "\"" + jsonObject.get("Пользователь") + "\""  + "," + "\n" +
                "\"Цена\":" + jsonObject.get("Цена")  + "," + "\n" +
                "\"Сотрудник\":" + "\"" + jsonObject.get("Сотрудник") + "\"" + "}";
        return s;
    }

    public String ConvertRequestForPayment(RequestForPayment requestForPayment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Номер", requestForPayment.docNumber);
        jsonObject.put("Дата", formatForDateNow.format(requestForPayment.getDocDate()).toString());
        jsonObject.put("Пользователь", requestForPayment.getUser());
        jsonObject.put("Контрагент", requestForPayment.getCounterparty());
        jsonObject.put("Цена", requestForPayment.getPrice());
        jsonObject.put("Валюта",requestForPayment.getCurrency());
        jsonObject.put("Курс валюты", requestForPayment.getCurrencyRate());
        jsonObject.put("Комиссия", requestForPayment.getCommission());
        String s = "{\"Номер\":" + "\"" + jsonObject.get("Номер") + "\""  + "," + "\n" +
                "\"Дата\":" + "\"" + jsonObject.get("Дата") + "\""  + ","  + "\n" +
                "\"Пользователь\":" + "\"" + jsonObject.get("Пользователь") + "\""  + "," + "\n" +
                "\"Контрагент\":" + "\"" + jsonObject.get("Контрагент") + "\""  + "," + "\n" +
                "\"Цена\":" + jsonObject.get("Цена")  + "," + "\n" +
                "\"Валюта\":" + "\"" + jsonObject.get("Валюта") + "\""  + "," + "\n" +
                "\"Курс валюты\":" + jsonObject.get("Курс валюты") + "," + "\n" +
                "\"Комиссия\":" + jsonObject.get("Комиссия") + "}";
        return s;
    }

    //делает из строки json сам объект
    public WayBill getWaybillJson(String s) throws IOException, ParseException {
        JSONObject jsonObject = new JSONObject(s);
        WayBill wayBill = new WayBill(jsonObject);
        return wayBill;
    }

    public PaymentOrder getPaymentOrderJson(String s) throws IOException, ParseException {
        JSONObject jsonObject = new JSONObject(s);
        PaymentOrder paymentOrder = new PaymentOrder(jsonObject);
        return paymentOrder;
    }

    public RequestForPayment getRequestForPaymentJson(String s) throws IOException, ParseException { ;
        JSONObject jsonObject = new JSONObject(s);
        RequestForPayment requestForPayment = new RequestForPayment(jsonObject);
        return requestForPayment;
    }
}
