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
    int createStory(String title, String text);

    Collection<Integer> browseStories();

    int getStoryRoot(int storyId);

    //////////////////////////////////// Paragraphs /////////////////////////

    //returns id=0 when fails
    int createParagraph(int storyId, String title, String text);

    boolean isParagraphExists(int storyId, int paragraphId);

    int getNextParagrph(int storyId, int currentParagraphId);

    int getpreviusParagrph(int storyId, int currentParagraphId);
}


