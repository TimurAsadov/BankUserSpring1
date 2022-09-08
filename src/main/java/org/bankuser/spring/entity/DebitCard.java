package org.bankuser.spring.entity;

import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "card")
public class DebitCard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private double balance;


    private String number;


    private Date expire;

    @ManyToOne
    @JoinColumn(name = "userid")
    public User cardUser;

    public User getCardUser() {
        return cardUser;
    }

    public void setCardUser(User cardUser) {
        this.cardUser = cardUser;
    }

    private int cvv;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
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
        return Double.compare(debitCard.balance, balance) == 0 && cvv == debitCard.cvv && Objects.equals(number, debitCard.number) && Objects.equals(expire, debitCard.expire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, number, expire, cvv);
    }
    public void generateCardNumber(){
        long a = 1000000000000000L;
        long b = 8999999999999999L;

        long cardNumber0 = a + (long) (Math.random() * b);
        String cardNumber = Long.toString(cardNumber0);
        this.number = cardNumber;
    }
    public void generateCVV(){
        int a = 100; // Начальное значение диапазона - "от"
        int b = 899; // Конечное значение диапазона - "до"

        int cvv = a + (int) (Math.random() * b);
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "avialiableBalance=" + balance +
                ", CardNumber='" + number + '\'' +
                ", expireDate=" + expire +
                ", cvv=" + cvv +
                '}';
    }
    public void generateExpireDate(){;
        Date currDate = new Date();
        int yearsToAdd = 3;
        Date expireDate = DateUtils.addYears(currDate, yearsToAdd);
        this.expire = expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expire = expireDate;
    }


}
