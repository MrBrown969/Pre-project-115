package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<User> allUsers = new ArrayList<>();

    public void createUsersTable() {
        query = "drop table if exists users";
        try {
            statement = Util.getConnection().createStatement();
            statement.executeUpdate(query);
            Util.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query = "CREATE TABLE users " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";
        try {
            statement = Util.getConnection().createStatement();
            statement.executeUpdate(query);
            Util.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        query = "drop table if exists users";
        try {
            statement = Util.getConnection().createStatement();
            statement.executeUpdate(query);
            Util.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        query = "insert into users (name, lastName, age) values (?,?,?)";
        try {
            preparedStatement = Util.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            Util.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        query = "delete from users where id = ?";
        try {
            preparedStatement = Util.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
            Util.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        query = "select * from users";
        //List <User> allUsers = new ArrayList<>();
        try {
            statement = Util.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(allUsers);

        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            statement = Util.getConnection().createStatement();
            statement.executeUpdate("delete from users");
            Util.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
