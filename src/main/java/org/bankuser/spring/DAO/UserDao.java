package org.bankuser.spring.DAO;

import org.bankuser.spring.controller.AuthRegisterController;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.bankuser.spring.entity.DebitCard;
import org.bankuser.spring.entity.Loan;
import org.bankuser.spring.entity.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.List;
@Component
public class UserDao {




    private SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    /*private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/
   /* public void delete(String email) {
        jdbcTemplate.update("DELETE FROM UserData WHERE email=?, email");
    }*/
    /*public void save(User user) {
        jdbcTemplate.update("INSERT INTO UserData VALUES(?, ?, ?, ?, ?)", user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),user.getRegistrationDate());
    }*/
    /*public void update(String email, User updatedUser) {
        jdbcTemplate.update("UPDATE UserData SET firstname=?, lasttname=?, email=?, password=?, registrationdate=? WHERE email=?", updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getEmail(), updatedUser.getPassword(), updatedUser.getRegistrationDate(), email);
    }*/
    /*public List<User> showAll() {
        return jdbcTemplate.query("SELECT * FROM UserData", new BeanPropertyRowMapper<>(User.class));
    }*/

    @Transactional
    public List<User> showAll(){
        Session session = sessionFactory.openSession();
        List<User> users = (List<User>) session.createNativeQuery("SELECT * FROM UserData").addEntity(User.class).list();
        session.close();
        return users;
    }
    @Transactional
    public void update(int id, User updatedUser) {
        Session session = sessionFactory.openSession();
        User currentUser = session.get(User.class,id);
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setPassword(updatedUser.getPassword());
        session.close();
    }
    public void delete(User user){
        Session session = sessionFactory.openSession();
        session.delete(user);
        session.close();
    }
  /*  public User checkEmail(String email, String password){
        Session session = sessionFactory.openSession();
        Criteria cr = session.createCriteria(User.class);
        cr.add(Restrictions.eq("email", email));
        cr.add(Restrictions.eq("password", password));
        
    }*/

    @Transactional
    public User checkEmail(String email, String password){
        Session session = sessionFactory.openSession();
        User user  = (User) session.createNativeQuery("SELECT * FROM UserData WHERE email=? AND password=?").addEntity(User.class).list();
        session.close();
        return user;
    }



}
