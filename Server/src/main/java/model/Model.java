package model;

import exceptions.LoginException;

import java.util.*;

/**
 * Created by gur on 11/6/2015.
 */
public class Model {
    final private Map<String, User> users;
    final private Set<User> loggedUsers;

    public Model(){
        this.users = new HashMap<String, User>();
        this.loggedUsers = new HashSet<User>();
    }

    public void addUser(User user){
        users.put(user.username, user);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public String loginUser(String username, String password) {
        User user = users.get(username);

        if (user.getPasswordHash(password).equals(user.passwordHash)){
            if (!loggedUsers.contains(username)){
                user.cookie = UUID.randomUUID().toString();
                loggedUsers.add(user);
            }
            return user.cookie;
        }
        else {
            throw new LoginException("Passwords do not match");
        }
    }

    public void logoutUser(String cookie) {
        for (User user: users.values()){
            if (user.cookie != null && user.cookie.equals(cookie)){
                loggedUsers.remove(user);
            }
        }
    }
}