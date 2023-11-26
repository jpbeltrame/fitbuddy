package com.example.fitbuddy.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document("subscriptions")
public class Subscription {
    String id;
    String userId;
    String type;
    double price;
    LocalDate creditCardToken;
    LocalDate subscribedAt;
    int chargeDay;

    public Subscription(String userId, String type, double price) {
        this.userId = userId;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getCreditCardToken() {
        return creditCardToken;
    }

    public LocalDate getSubscribedAt() {
        return subscribedAt;
    }

    public int getChargeDay() {
        return chargeDay;
    }

    public int chargeDay(){
        return chargeDay;
    }

    public void subscribe(String userId, String type,double price,int chargeDay){

    }
    public void generateCreditCardToken(String holderName, String cardNumber,String cardCode,String expDate){

    }
}
