package acceptance.bridge;

import java.util.Collection;

/**
 * Created by Kerzman on 12/23/2015.
 */
public interface BridgeAPI {

    /////////// USERS//////////////////
    boolean signUp(String userName, String password);

    boolean login(String userName, String password);

    boolean logout();

    ///////////////////////////////////// STORIES //////////////////

    //returns id=0 when fails
    String createStory(String title, String rootTitle, String rootText, String category);

    Collection<String> browseStories();

    String createParagraph(String storyNumber, String paragraphTitle, String paragraphText, String rootParagraphId);

    boolean isParagraphExists(String storyNumber, String i);

    boolean initServer();

    String getRootParagraph(String storyNumber);

    //////////////////////////////////// Paragraphs /////////////////////////
}


