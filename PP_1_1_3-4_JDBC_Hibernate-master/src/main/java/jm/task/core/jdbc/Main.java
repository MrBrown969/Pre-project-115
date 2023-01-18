package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {// реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
      userService.createUsersTable();
       userService.saveUser("General Grievous", "Sith", (byte) 70);
       userService.saveUser("General Kenobi", "Jedi", (byte) 70);
        userService.saveUser("Darth Vader", "Sith", (byte) 48);
       userService.saveUser("Han Solo", "Jedi", (byte) 70);
       userService.getAllUsers();
       userService.removeUserById(2);
        userService.cleanUsersTable();
       userService.dropUsersTable();
        Util.closeSessionFactory();

    }
}
