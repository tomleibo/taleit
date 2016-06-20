package model;


import db.DbHandler;
import exceptions.NoSuchParagraphIdException;
import exceptions.StoryException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Shai on 23/12/2015.
 */
public class Story {
    private final HashMap<String, Paragraph> paragraphs;
    private String id;
    private String title;
    private String root;
    private Categories category;
    private Set<User> likes;

    public Story(String title, Paragraph root, Categories category){
        this.title = title;
        this.id = UUID.randomUUID().toString();
        this.root = root.getId();
        this.category = category;
        this.paragraphs = new HashMap<String, Paragraph>();
        this.paragraphs.put(root.getId(), root);
        this.likes = new HashSet<>();
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
        DbHandler.getInstance().InsertParagraph(paragraph);
        paragraphs.put(paragraph.getId(), paragraph); // TODO: make paragraphs a collection of paragraph only. implement equals & hashcode by id only
        return paragraph;
    }

    public Paragraph getParagraphById(String paragraphId) {
        DbHandler.getInstance().queryParagraph("ID", root);
        if (paragraphs.containsKey(paragraphId)) {
            return paragraphs.get(paragraphId);
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

    public void addLike(User user) {
        this.likes.add(user);
    }

    public int getLikes(){
        return this.likes.size();
    }
}
