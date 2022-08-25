package org.bankuser.spring.entity;

import java.util.Date;
import java.util.Objects;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "card")
public class DebitCard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;




    private double avialiableBalance;

    @Column(name = "CardNumber")
    private String CardNumber;

    @Column(name = "expireDate")
    private Date expireDate;


    private int cvv;

    public double getAvialiableBalance() {
        return avialiableBalance;
    }

    public void setAvialiableBalance(double avialiableBalance) {
        this.avialiableBalance = avialiableBalance;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public Date getExpireDate() {
        return expireDate;
    }


    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebitCard debitCard = (DebitCard) o;
        return Double.compare(debitCard.avialiableBalance, avialiableBalance) == 0 && cvv == debitCard.cvv && Objects.equals(CardNumber, debitCard.CardNumber) && Objects.equals(expireDate, debitCard.expireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avialiableBalance, CardNumber, expireDate, cvv);
    }
    public String generateCardNumber(){
        long a = 1000000000000000L;
        long b = 8999999999999999L;

        long cardNumber0 = a + (long) (Math.random() * b);
        String cardNumber = Long.toString(cardNumber0);
        return cardNumber;
    }
    public int generateCVV(){
        int a = 100; // Начальное значение диапазона - "от"
        int b = 899; // Конечное значение диапазона - "до"

        int cvv = a + (int) (Math.random() * b);
        return cvv;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "avialiableBalance=" + avialiableBalance +
                ", CardNumber='" + CardNumber + '\'' +
                ", expireDate=" + expireDate +
                ", cvv=" + cvv +
                '}';
    }
    public Date generateExpireDate(){;
        Date currDate = new Date();
        int yearsToAdd = 3;
        Date expireDate = DateUtils.addYears(currDate, yearsToAdd);

        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
