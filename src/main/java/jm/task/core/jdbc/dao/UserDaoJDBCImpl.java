package jm.task.core.jdbc.dao;

import com.mysql.cj.protocol.Resultset;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String statementToExec ="CREATE TABLE IF NOT EXISTS USERS(" +
                "Id INT PRIMARY KEY AUTO_INCREMENT," +
                "Name varchar(20) NOT NULL," +
                "LastName varchar(20) NOT NULL," +
                "Age INT NOT NULL)";
        try {
            Statement statement = connection.createStatement();
            statement.execute(statementToExec);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String statementToExec ="DROP TABLE IF EXISTS USERS";
        try {
            Statement statement = connection.createStatement();
            statement.execute(statementToExec);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String statementToExec ="INSERT INTO USERS  (Name, LastName, Age) VALUES(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(statementToExec);
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String statementToExec ="DELETE FROM USERS WHERE Id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(statementToExec);
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String statementToExec ="SELECT * FROM USERS";
        List<User> userList =new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(statementToExec);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                userList.add(new User(resultset.getString("name"),resultset.getString("lastname"),resultset.getByte("age")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE USERS");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
