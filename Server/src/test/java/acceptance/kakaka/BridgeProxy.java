package acceptance.kakaka;

import java.util.Collection;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class BridgeProxy implements BridgeAPI {

    public boolean signUp(String userName, String password) {
        return true;
    }

    public boolean login(String userName, String password) {
        return true;
    }

    public boolean logout() {
        return true;
    }

    public int createStory(String userName, String title, String text) {
        return 1;
    }

    public Collection<Integer> browseStories() {
        return null;
    }

    public boolean isStoryExists(int storyId) {
        return true;
    }

    public boolean getStoryRoot(int storyId) {
        return true;
    }

    public int createParagraph(int storyId, String userName, String title, String text) {
        return 1;
    }

    public boolean isParagraphExists(int storyId, int paragraphId) {
        return true;
    }

    public boolean getNextParagrph(int storyId, int currentParagraphId) {
        return true;
    }

    public boolean getpreviusParagrph(int storyId, int currentParagraphId) {
        return true;
    }
}
