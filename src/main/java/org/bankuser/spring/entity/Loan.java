package org.bankuser.spring.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "application")
    private Date application;
    @NotEmpty(message = "interest rate should not be empty")
    @Size( min = 1, max=100, message = "interest rate should not be empty")
    @Column(name = "interest")
    private int interest;
    @Column(name = "month")
    @NotEmpty(message = "Loan data should not be empty")
    private int month;
    @Column(name = "monthlypayments")
    @NotEmpty(message = "Loan data should not be empty")
    private int monthlyPayments;
    @Column(name = "amount")
    @NotEmpty(message = "Loan data should not be empty")
    @Size( min = 1, max=100000, message = "amount should not be empty")
    private int amount;
    @ManyToOne
    @JoinColumn(name = "userid")
    public User loanUser;
    public void applicationDate(){
        Date curr = new Date();
        this.application = curr;
    }
    public User getLoanUser() {
        return loanUser;
    }
    public void setLoanUser(User loanUser) {
        this.loanUser = loanUser;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Date getApplication() {
        return application;
    }
    public void setApplication(Date application) {
        this.application = application;
    }
    public int getInterest() {
        return interest;
    }
    public void setInterest(int interest) {
        this.interest = interest;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getMonthlyPayments() {
        return monthlyPayments;
    }
    public void setMonthlyPayments(int monthlyPayments) {
        this.monthlyPayments = monthlyPayments;
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
        Loan loan = (Loan) o;
        return interest == loan.interest && month == loan.month && monthlyPayments == loan.monthlyPayments && Objects.equals(application, loan.application);
    }
    @Override
    public int hashCode() {
        return Objects.hash(application, interest, month, monthlyPayments);
    }
    @Override
    public String toString() {
        return "Loan{" +
                "application=" + application +
                ", interest=" + interest +
                ", month=" + month +
                ", monthlyPayments=" + monthlyPayments +
                ", amount=" + amount +
                '}';
    }
}
