package model;

/**
 * Created by Shai on 23/12/2015.
 */
public class Story {
    String username;
    String title;
    String text;
    int id;
    static int storyCounter = 0;

    public Story(String username, String title, String text){
        this.username = username;
        this.title = title;
        this.text = text;
        this.id = storyCounter++;
    }
}
