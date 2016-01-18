package model;

import exceptions.NoSuchParagraphIdException;
import exceptions.StoryException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shai on 23/12/2015.
 */
public class Story {
    static int storyCounter = -1;

    final private Map<Integer, Paragraph> paragraphs;
    private int id;
    private String username; // will be showed as author? maybe add another field
    private String title;
    private Paragraph root;
    int paragraphCounter;

    public Story(String username, String title, String text){
        this.username = username;
        this.title = title;
        this.id = storyCounter++;
        this.paragraphCounter = -1;
        this.root = new Paragraph(paragraphCounter++, null, text, username);
        this.paragraphs = new HashMap<Integer, Paragraph>();
        this.paragraphs.put(this.root.getId(), this.root);
    }

    public int getId() {
        return id;
    }

    public Paragraph getRoot() {
        return root;
    }

    public String getUsername() {
        return username;
    }

    public Paragraph addParagraph(Paragraph father, String text, String username) {
        if (father == null){
            throw new StoryException("father can't be null, only the father of the root paragraph is null");
        }
        Paragraph paragraph = new Paragraph(paragraphCounter++, father, text,username);
        paragraphs.put(paragraph.getId(), paragraph);
        father.addChild(paragraph);
        return paragraph;
    }

    public Paragraph getParagraphById(Integer paragraphId) {
        if (paragraphs.containsKey(paragraphId)) {
            return paragraphs.get(paragraphId);
        }else{
            throw new NoSuchParagraphIdException(paragraphId);
        }
    }
}
