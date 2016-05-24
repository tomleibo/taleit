package acceptance.utils;

import acceptance.core.LoggedInBaseAcceptance;
import ioc.Server;
import model.Model;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by sharonk on 1/18/2016
 */
public class DummyDB extends LoggedInBaseAcceptance {
    String storyNumber;

    @Test
    public void eraseInjectDummyDB() {
        super.init();
        bridge.signUp(userName, password);
        bridge.login(userName,password);
        storyNumber = bridge.createStory(storyTitle, storyTitle, storyText, "");











    }


}