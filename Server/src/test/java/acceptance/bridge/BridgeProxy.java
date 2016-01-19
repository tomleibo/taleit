package acceptance.bridge;

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

    public int createStory(String title, String text) {
        return 1;
    }

    public Collection<Integer> browseStories() {
        return null;
    }
    public int getStoryRoot(int storyId) {
        return 1;
    }

    public int createParagraph(int storyId, String title, String text) {
        return 1;
    }

    public boolean isParagraphExists(int storyId, int paragraphId) {
        return true;
    }

    public int getNextParagrph(int storyId, int currentParagraphId) {
        return 1;
    }

    public int getpreviusParagrph(int storyId, int currentParagraphId) {
        return 1;
    }
}
