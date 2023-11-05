package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static UserServiceImpl userService = new UserServiceImpl();
    private final static UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("aa","aa",(byte) 15);
        System.out.println("User с именем – aa добавлен в базу данных");
        userService.saveUser("bb","bb",(byte) 16);
        System.out.println("User с именем – bb добавлен в базу данных");
        userService.saveUser("cc","cc",(byte) 17);
        System.out.println("User с именем – cc добавлен в базу данных");
        userService.saveUser("dd","dd",(byte) 18);
        System.out.println("User с именем – dd добавлен в базу данных");
        List<User> userList = userService.getAllUsers();
        for(User user : userList) {
            System.out.println(user);
        }
        userDaoHibernate.cleanUsersTable();
        userService.dropUsersTable();
    }
}
