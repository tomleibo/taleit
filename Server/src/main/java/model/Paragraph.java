package model;

import db.DbHandler;

import java.util.*;

/**
 * Created by Shai on 15/01/2016.
 */
public class Paragraph {
    String id;
    String father;
    String user;
    String text;
    String title;

    public Paragraph(Paragraph father, String text, String title, User user) {
        this.id = UUID.randomUUID().toString();
        if (father != null) {
            this.father = father.getId();
        }else{
            this.father = null;
        }
        if (user == null){
            this.user = null;
        }else {
            this.user = user.getUsername();
        }
        this.text = text;
        this.title = title;
    }

    public Paragraph(){

    }

    public String getText() {
        return text;
    }

    public Collection<Paragraph> getChildren()
    {
        Collection<Paragraph> children = (DbHandler.getInstance()).queryParagraph("FATHER", id);
        return children;
    }

    public Paragraph getFather() {
        if (this.father == null){
            return null;
        }
        Paragraph father = (Paragraph) (DbHandler.getInstance()).queryParagraph("ID", this.father).toArray()[0];
        return father;
    }

    public User getUser() {
        User user = DbHandler.getInstance().queryUser("USERNAME", this.user);
        return user;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean hasChildren(){
        return getChildren().isEmpty();
    }

    public Paragraph setId(String id) {
        this.id = id;
        return this;
    }

    public Paragraph setFather(String father) {
        this.father = father;
        return this;
    }

    public Paragraph setUser(String user) {
        this.user = user;
        return this;
    }

    public Paragraph setText(String text) {
        this.text = text;
        return this;
    }

    public Paragraph setTitle(String title) {
        this.title = title;
        return this;
    }
}
