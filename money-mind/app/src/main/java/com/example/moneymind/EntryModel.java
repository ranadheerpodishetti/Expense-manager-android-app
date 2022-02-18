package com.example.moneymind;

public class EntryModel {

    private int entryID;
    private String COL_2_Transaction;
    private String COL_3_Date;
    private String COL_4_Time;
    private String COL_5_Payment;
    private String COL_6_Category;
    //private String COL_6_5_Category2;
    private Double COL_7_Amount;
    private String COL_8_Currency;
    private String COL_9_Notes;

    //constructors

    public EntryModel(int entryID, String COL_2_Transaction, String COL_3_Date, String COL_4_Time, String COL_5_Payment, String COL_6_Category, Double COL_7_Amount, String COL_8_Currency, String COL_9_Notes) {
        this.entryID = entryID;
        this.COL_2_Transaction = COL_2_Transaction;
        this.COL_3_Date = COL_3_Date;
        this.COL_4_Time = COL_4_Time;
        this.COL_5_Payment = COL_5_Payment;
        this.COL_6_Category = COL_6_Category;
        //this.COL_6_5_Category2 = COL_6_5_Category2;
        this.COL_7_Amount = COL_7_Amount;
        this.COL_8_Currency = COL_8_Currency;
        this.COL_9_Notes = COL_9_Notes;
    }
    //non parameterized constructor
    public EntryModel() {
    }

    // toString is necessary for printing the contents of a class object

    @Override
//    public String toString() {
//        return "EntryModel{" +
//                "entryID=" + entryID +
//                ", COL_2_transaction='" + COL_2_Transaction + '\'' +
//                ", COL_3_Date='" + COL_3_Date + '\'' +
//                ", COL_4_Time='" + COL_4_Time + '\'' +
//                ", COL_5_Payment='" + COL_5_Payment + '\'' +
//                ", COL_6_Category='" + COL_6_Category + '\'' +
//                ", COL_7_Amount=" + COL_7_Amount +
//                ", COL_8_Currency='" + COL_8_Currency + '\'' +
//                ", COL_9_Notes='" + COL_9_Notes + '\'' +
//                '}';
//    }

    public String toString() {
            return COL_2_Transaction + ' ' + COL_3_Date + ' ' +
                    COL_6_Category + ' ' +
                    COL_7_Amount + " " +
                    COL_8_Currency + ' ' ;
    }

    //getters and setters

    public int getEntryID() {
        return entryID;
    }

    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }

    public String getCOL_2_Transaction() {
        return COL_2_Transaction;
    }

    public void setCOL_2_Transaction(String COL_2_Transaction) { this.COL_2_Transaction = COL_2_Transaction; }

    public String getCOL_3_Date() {
        return COL_3_Date;
    }

    public void setCOL_3_Date(String COL_3_Date) {
        this.COL_3_Date = COL_3_Date;
    }

    public String getCOL_4_Time() {
        return COL_4_Time;
    }

    public void setCOL_4_Time(String COL_4_Time) { this.COL_4_Time = COL_4_Time; }

    public String getCOL_5_Payment() {
        return COL_5_Payment;
    }

    public void setCOL_5_Payment(String COL_5_Payment) {
        this.COL_5_Payment = COL_5_Payment;
    }

    public String getCOL_6_Category() {
        return COL_6_Category;
    }

    public void setCOL_6_Category(String COL_6_Category) {
        this.COL_6_Category = COL_6_Category;
    }

//    public String getCOL_6_5_Category2() {
//        return COL_6_5_Category2;
//    }
//
//    public void setCOL_6_5_Category2(String COL_6_5_Category2) { this.COL_6_5_Category2 = COL_6_5_Category2; }

    public Double getCOL_7_Amount() {
        return COL_7_Amount;
    }

    public void setCOL_7_Amount(Double COL_7_Amount) {
        this.COL_7_Amount = COL_7_Amount;
    }

    public String getCOL_8_Currency() {
        return COL_8_Currency;
    }

    public void setCOL_8_Currency(String COL_8_Currency) {
        this.COL_8_Currency = COL_8_Currency;
    }

    public String getCOL_9_Notes() {
        return COL_9_Notes;
    }

    public void setCOL_9_Notes(String COL_9_Notes) {
        this.COL_9_Notes = COL_9_Notes;
    }
}
