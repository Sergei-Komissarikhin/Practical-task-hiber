package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory factory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS users(" +
                        "id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                        "name VARCHAR(45)," +
                        "lastName VARCHAR(45)," +
                        "age TINYINT)";
        try {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(CREATE_TABLE).executeUpdate();
        session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        String DROP_TABLE =
                "DROP TABLE IF EXISTS users";
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {

            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users WHERE id = " + id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            userList = session.createQuery("FROM User").list();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return userList;

    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }
}
