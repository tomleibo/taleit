package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gur on 11/6/2015.
 */
public class Model {
    final private Map<String, User> users;

    public Model(){
        this.users = new HashMap<String, User>();
    }

    public void addUser(User user){
        users.put(user.username, user);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }
}
