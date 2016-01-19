package model;

import exceptions.LoginException;

import java.util.*;

/**
 * Created by gur on 11/6/2015.
 */
public class Model {
    final private Map<String, User> users;
    final private Set<User> loggedUsers;
    final private Collection<Story> stories;

    public Model(){
        this.users = new HashMap<String, User>();
        this.loggedUsers = new HashSet<User>();
        this.stories = new HashSet<Story>();
    }

    public void addUser(User user){
        users.put(user.getUsername(), user);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public String loginUser(String username, String password) {
        User user = users.get(username);

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

    public void logoutUser(String cookie) {
        for (User user: users.values()){
            if (user.cookie != null && user.cookie.equals(cookie)){
                loggedUsers.remove(user);
            }
        }
    }

    public User getUserFromCookie(String cookie){
        for (User user: loggedUsers){
            if (user.cookie.equals(cookie)){
                return user;
            }
        }
        return null;
    }

    public void addStory(Story story) {
        stories.add(story);
    }

    public Set<User> getLoggedUsers(){
        return loggedUsers;
    }

    public boolean isUserLoggedIn(String userName){
         return getLoggedUsers().contains(users.get(userName));
    }

    public Collection<Story> getStories() {
        return stories;
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
        for (Story story : getStories()) {
            if (story.getId().equals(storyId)) {
                return story;
            }
        }
        throw new RuntimeException("story (id = " + storyId + ") not found");
    }
}