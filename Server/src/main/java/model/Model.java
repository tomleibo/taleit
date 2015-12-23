package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gur on 11/6/2015.
 */
public class Model {
    final private List<String> users;

    public Model(){
        this.users = new ArrayList<String>();
    }

    public List<String> getUsers() {
        return users;
    }
}
