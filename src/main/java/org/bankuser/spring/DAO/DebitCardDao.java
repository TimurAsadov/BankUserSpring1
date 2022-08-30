package org.bankuser.spring.DAO;

import org.bankuser.spring.entity.DebitCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DebitCardDao {
    /*private static JdbcTemplate jdbcTemplate = new JdbcTemplate();
    @Autowired
    public DebitCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<DebitCard> showAll() {
        return jdbcTemplate.query("SELECT * FROM card", new BeanPropertyRowMapper<>(DebitCard.class));
    }
    public static void delete(String cardnumber) {
        jdbcTemplate.update("DELETE FROM card WHERE cardnumber=?, cardnumber");
    }
    public static void save(DebitCard debitCard) {
        jdbcTemplate.update("INSERT INTO card VALUES(?, ?, ?, ?)", debitCard.generateCardNumber(), debitCard.getAvialiableBalance(), debitCard.generateExpireDate(), debitCard.generateCVV());
    }
    public void update(String cardnumber, DebitCard updatedDebitCard) {
        jdbcTemplate.update("UPDATE card SET cardnumber=?, avialiablebalance=?, expiredate=?, cvv=? WHERE cardnumber=?", updatedDebitCard.getCardNumber(), updatedDebitCard.getAvialiableBalance(), updatedDebitCard.getExpireDate(), updatedDebitCard.getCvv(), cardnumber);
    }*/
    private SessionFactory sessionFactory;

    public DebitCardDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public void save(DebitCard debitCard) {
        Session session = sessionFactory.openSession();
        session.save(debitCard);
        session.close();
    }
    @Transactional
    public List<DebitCard> showAll(){
        Session session = sessionFactory.openSession();
        List<DebitCard> debitCards = (List<DebitCard>) session.createNativeQuery("SELECT * FROM card").addEntity(DebitCard.class).list();
        session.close();
        return debitCards;
    }
    @Transactional
    public void update(int id, DebitCard updatedCard) {
        Session session = sessionFactory.openSession();
        DebitCard currentCard = session.get(DebitCard.class,id);
        currentCard.setAvialiableBalance(updatedCard.getAvialiableBalance());
        currentCard.setExpireDate(updatedCard.getExpireDate());
        session.close();
    }
    @Transactional
    public void delete(DebitCard deletedCard){
        Session session = sessionFactory.openSession();
        session.delete(deletedCard);
        session.close();
    }
}
