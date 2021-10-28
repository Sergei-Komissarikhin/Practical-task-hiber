package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
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

        Session session = factory.openSession();
        session.beginTransaction();
        session.createSQLQuery(CREATE_TABLE).executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        String DROP_TABLE =
                "DROP TABLE IF EXISTS users";

        Session session = factory.openSession();
        session.beginTransaction();
        session.createSQLQuery(DROP_TABLE).executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DELETE FROM users WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        Session session = factory.openSession();
        session.beginTransaction();
        userList = session.createSQLQuery("SELECT * FROM my_db.users")
                .addEntity(User.class).list();
        session.getTransaction().commit();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DELETE FROM users").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
