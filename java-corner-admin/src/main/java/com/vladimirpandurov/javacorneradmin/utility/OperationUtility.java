package com.vladimirpandurov.javacorneradmin.utility;

import com.vladimirpandurov.javacorneradmin.dao.UserDao;
import com.vladimirpandurov.javacorneradmin.entity.User;

public class OperationUtility {

    public static void usersOperations(UserDao userDao){
        createUsers(userDao);
        updateUsers(userDao);
    }

    private static void createUsers(UserDao userDao){
        User user1 = new User("user1@gmail.com", "pass1");
        userDao.save(user1);
        User user2 = new User("user2@gmail.com", "pass2");
        userDao.save(user2);
        User user3 = new User("user3@gmail.com", "pass3");
        userDao.save(user3);
        User user4 = new User("user4@gmail.com", "pass4");
        userDao.save(user4);
    }

    private static void updateUsers(UserDao userDao){

    }

}
