package model;


import java.util.HashMap;
import java.util.UUID;

import exceptions.NoSuchParagraphIdException;
import exceptions.StoryException;

/**
 * Created by Shai on 23/12/2015.
 */
public class Story {
    private final HashMap<String, Paragraph> paragraphs;
    private String id;
    private String title;
    private Paragraph root;

    public Story(String title, Paragraph root){
        this.title = title;
        this.id = UUID.randomUUID().toString();
        this. root = root;
        this.paragraphs = new HashMap<String, Paragraph>();
    }

    public String getId() {
        return id;
    }

    public Paragraph getRoot() {
        return root;
    }

    public User getUser() {
        return root.getUser();
    }

    public Paragraph addParagraph(Paragraph father, String title, String text, User user) {
        if (father == null){
            throw new StoryException("father can't be null, only the father of the root paragraph is null");
        }

        Paragraph paragraph = new Paragraph(father, text, title, user);
        paragraphs.put(paragraph.getId(), paragraph); // TODO: make paragraphs a collection of paragraph only. implement equals & hashcode by id only
        father.addChild(paragraph);
        return paragraph;
    }

    public Paragraph getParagraphById(String paragraphId) {
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
}
