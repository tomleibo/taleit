package model;


import db.DbHandler;
import exceptions.NoSuchParagraphIdException;
import exceptions.StoryException;

import java.util.*;

/**
 * Created by Shai on 23/12/2015.
 */
public class Story {
    private String id;
    private String title;
    private String root;
    private Categories category;

    public Story(String title, Paragraph root, Categories category){
        this.title = title;
        this.id = UUID.randomUUID().toString();
        this.root = root.getId();
        this.category = category;
        root.setStory(this.getId());
        DbHandler.getInstance().updateParagraph(root);
    }

    public Story(){

    }

    public String getId() {
        return id;
    }

    public Paragraph getRoot() {
        return (DbHandler.getInstance().queryParagraph("ID", root)).iterator().next();
    }

    public User getUser() {
        return getRoot().getUser();
    }

    public Categories getCategory(){
        return category;
    }

    public Paragraph addParagraph(Paragraph father, String title, String text, User user) {
        if (father == null){
            throw new StoryException("father can't be null, only the father of the root paragraph is null");
        }

        Paragraph paragraph = new Paragraph(father, text, title, user);
        paragraph.setStory(this.getId());
        DbHandler.getInstance().InsertParagraph(paragraph);
        return paragraph;
    }

    public Paragraph getParagraphById(String paragraphId) {
        Set<Paragraph> paragraphs = DbHandler.getInstance().queryParagraph("STORY", this.getId());
        for(Paragraph para: paragraphs){
            if (Objects.equals(para.getId(), paragraphId)){
                return para;
            }
        }
        throw new NoSuchParagraphIdException(paragraphId);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals (Object other){
        if (other == this) return true;
        if (other == null) return false;
        if (!getClass().equals(other.getClass())) return false;

        return getId().equals(((Story) other).getId());
    }

    @Override
    public int hashCode(){
        return getId().hashCode();
    }

    public Story setId(String id) {
        this.id = id;
        return this;
    }

    public Story setTitle(String title) {
        this.title = title;
        return this;
    }

    public Story setRoot(String root) {
        this.root = root;
        return this;
    }

    public Story setCategory(Categories category) {
        this.category = category;
        return this;
    }
}
