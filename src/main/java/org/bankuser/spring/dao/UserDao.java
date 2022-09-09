package org.bankuser.spring.dao;

import org.bankuser.spring.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

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
        user.getRegistrationDate();
        session.save(user);
        session.close();
    }

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
    public User login(String email, String password){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User where email LIKE :email AND password LIKE :password")
                .setParameter("email", "%"+email+"%")
                .setParameter("password","%"+password+"%");
        User user = (User) query.list().stream().findAny().orElse(null);
        session.close();
        return user;
    }
}
