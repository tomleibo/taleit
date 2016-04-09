package acceptance.bridge;

import java.util.Collection;

/**
 * Created by Kerzman on 12/23/2015.
 */
public interface BridgeAPI {

    /////////// USERS//////////////////
    boolean signUp(String userName, String password) throws Throwable;

    boolean login(String userName, String password) throws Throwable;

    boolean logout() throws Throwable;

    ///////////////////////////////////// STORIES //////////////////

    //returns id=0 when fails
    String createStory(String title, String rootTitle, String rootText) throws Throwable;

    Collection<String> browseStories() throws Throwable;

    String createParagraph(String storyNumber, String paragraphTitle, String paragraphText);

    boolean isParagraphExists(String storyNumber, String i);

    //////////////////////////////////// Paragraphs /////////////////////////
}


