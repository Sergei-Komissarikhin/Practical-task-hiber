package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static String DROP_TABLE =
                                "DROP TABLE IF EXISTS my_first_table";
    private static String CREATE_TABLE =
                                "CREATE TABLE my_first_table(" +
                                        "id INT PRIMARY KEY AUTO_INCREMENT," +
                                        "name VARCHAR(45)," +
                                        "last_name VARCHAR(45)," +
                                        "age TINYINT(3))";
    private static String SAVE_USER =
            "INSERT INTO my_db.my_first_table (name, last_name, age) VALUES (?, ?, ?)";
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
            System.out.println("I do this!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE);
            System.out.println("Good drop!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
