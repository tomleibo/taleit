package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shai on 15/01/2016.
 */
public class Paragraph {
    private int id;
    private Paragraph father;
    private Map<Integer, Paragraph> Children;
    private String username;
    private String text;

    public Paragraph(int id, Paragraph father, String text, String username) {
        this.id = id;
        this.father = father;
        this.username = username;
        this.text = text;
        Children = new HashMap<Integer, Paragraph>();
    }

    public String getText() {
        return text;
    }

    public Map<Integer, Paragraph> getChildren() {
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
        Children.put(paragraph.getId(), paragraph);
    }
}
