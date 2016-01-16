package model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Shai on 15/01/2016.
 */
public class Paragraph {
    String id;
    Paragraph father;
    Map<Integer, Paragraph> Children;
    String username;
    String text;
    String title;

    public Paragraph(Paragraph father, String text, String title, String username) {
        this.id = UUID.randomUUID().toString();
        this.father = father;
        this.username = username;
        this.text = text;
        this.title = title;
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

    public String getId() {
        return id;
    }
}
