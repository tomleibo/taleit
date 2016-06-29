package taleit.model;

import taleit.db.DbHandler;
import taleit.exceptions.LoginException;

import java.util.*;

/**
 * Created by gur on 11/6/2015.
 */
public class Model {
    final private Set<User> loggedUsers;
    
    public Model(){
        this.loggedUsers = new HashSet<User>();
    }

    public void addUser(User user){
        DbHandler.getInstance().InsertUser(user);
    }

    public boolean userExists(String username) {
        User user = DbHandler.getInstance().queryUser("USERNAME", username);
        return (user != null);
    }

    public String loginUser(String username, String password) {
        User user = DbHandler.getInstance().queryUser("USERNAME", username);

        if (user.getPasswordHash(password).equals(user.passwordHash)){
            if (!loggedUsers.contains(user)){
                user.cookie = UUID.randomUUID().toString();
                loggedUsers.add(user);
            }
            return user.cookie;
        }
        else {
            throw new LoginException("Passwords do not match");
        }
    }

    /**
     * facebook login uses this
     * @param username
     * @return
     */
    public String loginUser(String username) {
        User user = DbHandler.getInstance().queryUser("USERNAME", username);
        if (!loggedUsers.contains(user)){
            user.cookie = UUID.randomUUID().toString();
            loggedUsers.add(user);
        }
        return user.cookie;
    }


    public void logoutUser(String cookie) {
        User found = null;
        for (User user: loggedUsers){
            if (user.cookie != null && user.cookie.equals(cookie)){
                found = user;
            }
        }

        if (found != null){
            loggedUsers.remove(found);
        }
    }

    public User getUserFromCookie(String cookie){
        for (User user: loggedUsers){
            if (user.cookie.equals(cookie) || user.getUsername().equals(cookie)){
                return user;
            }
        }
        return null;
    }

    public void addStory(Story story) {
        DbHandler.getInstance().InsertStory(story);
    }

    public Set<User> getLoggedUsers(){
        return loggedUsers;
    }

    public boolean isUserLoggedIn(String userName){
        return getLoggedUsers().contains( DbHandler.getInstance().queryUser("USERNAME", userName));
    }

    public Collection<Story> getStories(String category) {
        if (category == null){
            return DbHandler.getInstance().queryStory("1", "1");
        }

       category = Categories.getCategoryByString(category).getValue();

       return DbHandler.getInstance().queryStory("CATEGORY", category);
    }

    public Paragraph concactinateParagraph(Story story, Paragraph father, String title, String text, User user) {
        return story.addParagraph(father, title, text, user);
    }

    public Paragraph getRootFromStory(Story story) {
        return story.getRoot();
    }

    public Paragraph getParagraph(Story story, String paragraphId) {
        return story.getParagraphById(paragraphId);
    }

    public Story getStory(String storyId) {
        Set<Story> stories = DbHandler.getInstance().queryStory("ID", storyId);

        if (stories.size() > 0){
            return stories.iterator().next();
        }

       throw new RuntimeException("story (id = " + storyId + ") not found");
    }

    public void init() {
        this.loggedUsers.clear();
        DbHandler.getInstance().truncateTables();
    }

    public void UserUpdate(User user) {
        DbHandler.getInstance().updateUser(user);
    }
}