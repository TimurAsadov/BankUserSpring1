package org.bankuser.spring.dao;

import org.bankuser.spring.entity.DebitCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class DebitCardDao {

    private SessionFactory sessionFactory;

    public DebitCardDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public void save(DebitCard debitCard) {
        Session session = sessionFactory.openSession();
        session.save(debitCard);
        session.close();
    }
    public List<DebitCard> showAll(){
        Session session = sessionFactory.openSession();
        List<DebitCard> debitCards = (List<DebitCard>) session.createNativeQuery("SELECT * FROM card").addEntity(DebitCard.class).list();
        session.close();
        return debitCards;
    }
    public void update(int id, DebitCard updatedCard) {
        Session session = sessionFactory.openSession();
        DebitCard currentCard = session.get(DebitCard.class,id);
        currentCard.setBalance(updatedCard.getBalance());
        currentCard.setExpireDate(updatedCard.getExpire());
        session.close();
    }
    public void delete(DebitCard deletedCard){
        Session session = sessionFactory.openSession();
        session.delete(deletedCard);
        session.close();
    }
}
