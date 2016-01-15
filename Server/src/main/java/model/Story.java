package model;

/**
 * Created by Shai on 23/12/2015.
 */
public class Story {
    static int storyCounter = 0;

    private int id;
    private String username; // will be showed as author? maybe add another field
    private String title;
    private Paragraph root;
    int paragraphCounter;

    public Story(String username, String title, String text){
        this.username = username;
        this.title = title;
        this.id = storyCounter++;
        this.paragraphCounter = 0;
        root = new Paragraph(paragraphCounter, null, text, username);
    }

    public int getId() {
        return id;
    }
}
