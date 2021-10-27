package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Vova", "Ivanov", (byte) 37);
        userService.saveUser("Ivan", "Sergeev", (byte) 37);
        userService.saveUser("Anna", "Petrova", (byte) 10);
        userService.saveUser("Ksenia", "Popova", (byte) 6);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
