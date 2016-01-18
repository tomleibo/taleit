package model;

import java.util.*;

/**
 * Created by Shai on 15/01/2016.
 */
public class Paragraph {
    String id;
    Paragraph father;
    Collection<Paragraph> Children;
    User user;
    String text;
    String title;

    public Paragraph(Paragraph father, String text, String title, User user) {
        this.id = UUID.randomUUID().toString();
        this.father = father;
        this.user = user;
        this.text = text;
        this.title = title;
        Children = new ArrayList<Paragraph>();
    }

    public String getText() {
        return text;
    }

    public Collection<Paragraph> getChildren() {
        return Children;
    }

    public Paragraph getFather() {
        return father;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return id;
    }

    public void addChild(Paragraph paragraph) {
        Children.add(paragraph);
    }

    public String getTitle() {
        return title;
    }
}
