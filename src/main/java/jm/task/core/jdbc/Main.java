package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    private final static UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("aa", "aa", (byte) 15);
        System.out.println("User с именем – aa добавлен в базу данных");
        userService.saveUser("bb", "bb", (byte) 16);
        System.out.println("User с именем – bb добавлен в базу данных");
        userService.saveUser("cc", "cc", (byte) 17);
        System.out.println("User с именем – cc добавлен в базу данных");
        userService.saveUser("dd", "dd", (byte) 18);
        System.out.println("User с именем – dd добавлен в базу данных");
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
