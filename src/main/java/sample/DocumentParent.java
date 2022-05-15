package sample;

import DocType.PaymentOrder;
import DocType.RequestForPayment;
import DocType.WayBill;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DocumentParent {


    protected Date date;
    protected String docNumber;
    public static SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy.");

    @Override
    public String toString(){
       return getShortDocInfo();
    }

    public  String getClassName(){

        return "Документ не определен";
    }

    public String getDocNumber(){return docNumber;}

    public Date getDocDate(){ return date; }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getShortDocInfo(){
        String shortInfo = this.getClassName() + " " + this.getDocNumber() + " от " + formatForDateNow.format(this.getDocDate()).toString();
        return shortInfo;
    }

    public String getDocMark(){
        return null;
    }

    public String getStringToShow(){
        return null;
    }

    public String getRandomDocNumber(){
        String digits = "1234567890АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЭЮЯ";
        int allDigLength = digits.length();
        Random rnd = new Random();
        StringBuilder number =new StringBuilder();
        number.append(this.getDocMark());
                for(int i = 0; i < 3; i++){

                   number.append(Character.toString(digits.charAt(rnd.nextInt(allDigLength))));
                }
                return number.toString();
    }
}
