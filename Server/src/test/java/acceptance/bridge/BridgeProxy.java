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

    public String createStory(String title, String rootTitle, String rootText) {
        return "";
    }

    public int createStory(String title, String text) {
        return 1;
    }

    public Collection<String> browseStories() {
        return null;
    }
}
