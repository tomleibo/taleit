package model;

import java.util.UUID;

/**
 * Created by Shai on 23/12/2015.
 */
public class Story {
    private String id;
    private String title;
    private Paragraph root;

    public Story(String title, Paragraph root){
        this.title = title;
        this.id = UUID.randomUUID().toString();
        this. root = root;
    }

    public String getId() {
        return id;
    }

    public Paragraph getRoot() {
        return root;
    }
}
