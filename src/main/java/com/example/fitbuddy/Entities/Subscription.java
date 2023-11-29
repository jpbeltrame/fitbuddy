package com.example.fitbuddy.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Document("subscriptions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subscription {
    @Id
    private String id;
    private String userId;
    private String type;
    private double price;
    private Date subscribedAt;
    private int chargeDay;

    @Setter(AccessLevel.NONE)
    private String creditCardToken;

    public Subscription(String userId, String type, double price) {
        this.userId = userId;
        this.type = type;
        this.price = price;
    }


    public void subscribe(String userId, String type, double price, int chargeDay) {

    }

    public void generateCreditCardToken(String holderName, String cardNumber, String cardCode, String expDate) {

    }
}
