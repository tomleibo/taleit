package model;

import exceptions.LoginException;

import java.util.*;

/**
 * Created by gur on 11/6/2015.
 */
public class Model {
    final private Map<String, User> users;
    final private Set<String> loggedUsers;

    public Model(){
        this.users = new HashMap<String, User>();
        this.loggedUsers = new HashSet<String>();
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
                loggedUsers.add(username);
            }
            return user.cookie;
        }
        else {
            throw new LoginException("Passwords do not match");
        }
    }

    public void logoutUser(String username) {
        users.get(username).cookie = null;

        if (loggedUsers.contains(username)){
            loggedUsers.remove(username);
        }
    }
}