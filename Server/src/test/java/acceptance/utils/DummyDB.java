package acceptance.utils;

import acceptance.core.LoggedInBaseAcceptance;
import model.Categories;
import org.junit.Test;


/**
 * Created by sharonk on 1/18/2016
 */
public class DummyDB extends LoggedInBaseAcceptance {
    String storyNumber;
//story title, root title, root text
    public static String[][] stories = {
    {"Dumb and more dumb", "the rubber chicken", "He looked in the chicken eyes and kissed it gently", Categories.COMEDY.getValue()},
    {"King of the humus", "She wasn't the prettiest", "She gave me the puppy eyes in a gas station", Categories.COMEDY.getValue()},
    {"Timetravel is a bitch", "past and present", "Hold the door! Hold the door, holddoor, hodor...", Categories.DRAMA.getValue()},
    {"To kill to drink to die", "vodka is my only lover", "As a strong independent women who needed no men in here life, I was lonely", Categories.DRAMA.getValue()},
    {"Ender's new game", "Chess", "chess in space is not so fun", Categories.SCIFI.getValue()},
    {"A biography of the first Nazi cleaning lady", "Intro", "It all started in 1942", Categories.OTHERS.getValue()}};

    @Test
    public void eraseInjectDummyDB() {
        super.init();
        bridge.signUp(userName, password);
        bridge.login(userName,password);
        for (String[] story : stories) {
            bridge.createStory(story[0], story[1], story[2], story[3]);
        }
    }
}