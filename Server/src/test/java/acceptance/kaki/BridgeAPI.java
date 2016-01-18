package acceptance.kaki;

import java.util.Collection;

/**
 * Created by Sharon Kerzman on 23/06/2015.
 */
public interface BridgeAPI {

    /////////// USERS//////////////////
    boolean signUp(String userName, String password);

    boolean login(String userName, String password);

    boolean logout();

    ///////////////////////////////////// STORIES //////////////////

    //returns id=0 when fails
    int createStory(String userName, String title, String text);

    Collection<Integer> browseStories();

    boolean isStoryExists(int storyId);

    boolean getStoryRoot(int storyId);

    //////////////////////////////////// Paragraphs /////////////////////////

    //returns id=0 when fails
    int createParagraph(int storyId, String userName, String title, String text);

    boolean isParagraphExists(int storyId, int paragraphId);

    boolean getNextParagrph(int storyId, int currentParagraphId);

    boolean getpreviusParagrph(int storyId, int currentParagraphId);
}


