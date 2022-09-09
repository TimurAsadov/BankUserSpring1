package org.bankuser.spring.dao;

import org.bankuser.spring.entity.Loan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class LoanDao {
    private SessionFactory sessionFactory;
    public LoanDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public void save(Loan loan) {
        Session session = sessionFactory.openSession();
        session.save(loan);
        session.close();
    }
    @Transactional
    public List<Loan> showAll(){
        Session session = sessionFactory.openSession();
        List<Loan> loans = (List<Loan>) session.createNativeQuery("SELECT * FROM loan").addEntity(Loan.class).list();
        session.close();
        return loans;
    }
    public void update(int id, Loan updatedLoan) {
        Session session = sessionFactory.openSession();
        Loan currentLoan = session.get(Loan.class,id);
        currentLoan.setMonthlyPayments(updatedLoan.getMonthlyPayments());
        currentLoan.setMonth(updatedLoan.getMonth());
        currentLoan.setInterest(updatedLoan.getInterest());
        currentLoan.setAmount(updatedLoan.getAmount());
        session.close();
    }
    public void delete(Loan loan){
        Session session = sessionFactory.openSession();
        session.delete(loan);
        session.close();
    }
}
