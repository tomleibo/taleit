package acceptance.tests;

import acceptance.core.LoggedInBaseAcceptance;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class CreateStoryAcceptance extends LoggedInBaseAcceptance {
    String storyNumber;
    @Before
    public void init(){
        super.init();
    }

    @Test
    public void validStroy_4_1() {
        storyNumber = bridge.createStory(storyTitle, storyTitle, storyText);
        assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == null);
        // assertTrue("Story is not exists", bridge.isStoryExists(storyNumber));
    }

    @Test
    public void noTitle_4_2() {
        storyNumber = bridge.createStory("", "", storyText);
        assertTrue("succeeded to create a story with no title", storyNumber == null);
        //assertFalse("Story exists with no title", bridge.isStoryExists(storyNumber));
    }

    @Test
    public void noText_4_3() {
        storyNumber = bridge.createStory(storyTitle, storyTitle, "");
        assertTrue("succeeded to create a story with no text", storyNumber == null);
        //assertFalse("Story exists with no text", bridge.isStoryExists(storyNumber));
    }

    @Test
     public void randomNumberOfStories_4_4() {
        int randomNumber = getRandomNumber();
        String[] storiesNumber = new String[randomNumber];

        System.out.println("will create " + randomNumber + " stories");

        // save story number
        for (int i=1; i<=randomNumber; i++){
            storiesNumber[i-1] = bridge.createStory(storyTitle, storyTitle, storyText);
        }

        // assertions
        for (int i=0; i<randomNumber; i++){
            assertFalse("Could not create a story number " + i+1 , storiesNumber[i]== null);
            //assertTrue("Story numer" + i+1 + "is not exists", bridge.isStoryExists(storiesNumber[i]));
        }
    }

}
