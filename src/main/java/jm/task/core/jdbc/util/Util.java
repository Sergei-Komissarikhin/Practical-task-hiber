package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        sessionFactory = getConfig().buildSessionFactory();
        System.out.println("SessionFactory is working? = " + !sessionFactory.isClosed());
        return sessionFactory;
    }

    private static Configuration getConfig() {
        Configuration config = new Configuration();
        config.addAnnotatedClass(User.class)
                .setProperties(getProperties());
        return config;
    }

    private static Properties getProperties() {
        Properties prop = new Properties();
        prop.setProperty(Environment.DIALECT,
                "org.hibernate.dialect.MySQL5Dialect");
        prop.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/my_db?autoReconnect=true&useSSL=false");
        prop.setProperty(Environment.USER, "root");
        prop.setProperty(Environment.PASS, "springcourse");
        prop.setProperty(Environment.SHOW_SQL, "true");
        prop.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        prop.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//        prop.setProperty(Environment.HBM2DDL_AUTO, "update");
        System.out.println("Properties recorded");
        return prop;
    }
}
