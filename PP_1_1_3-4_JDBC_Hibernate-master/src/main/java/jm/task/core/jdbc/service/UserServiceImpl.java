package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    //might wanna add another userDao for some reason
    UserDao userDaoHibernate = new UserDaoHibernateImpl(); //changed

    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDaoHibernate.saveUser(name, lastName, age);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User с именем – " + name + " добавлен в базу данных");

    }

    public void removeUserById(long id) {
        try {
            userDaoHibernate.removeUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        try {
            userDaoHibernate.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
