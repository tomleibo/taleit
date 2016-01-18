package model;

import java.util.*;

/**
 * Created by Shai on 15/01/2016.
 */
public class Paragraph {
    private int id;
    private Paragraph father;
    private Set<Paragraph> Children;
    private String username;
    private String text;

    public Paragraph(int id, Paragraph father, String text, String username) {
        this.id = id;
        this.father = father;
        this.username = username;
        this.text = text;
        Children = new HashSet<Paragraph>();
    }

    public String getText() {
        return text;
    }

    public Set<Paragraph> getChildren() {
        return Children;
    }

    public Paragraph getFather() {
        return father;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void addChild(Paragraph paragraph) {
        Children.add(paragraph);
    }
}
