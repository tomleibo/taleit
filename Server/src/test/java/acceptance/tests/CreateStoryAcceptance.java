package acceptance.tests;

import acceptance.core.LoggedInBaseAcceptance;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class CreateStoryAcceptance extends LoggedInBaseAcceptance {
    int storyNumber;
    @Before
    public void init(){
        super.init();
    }

    @Test
    public void validStroy() {
        storyNumber = bridge.createStory(storyTitle, storyText);
        assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == 0);
        assertTrue("Story is not exists", bridge.isStoryExists(storyNumber));
    }

    @Test
    public void noTitle() {
        storyNumber = bridge.createStory("", storyText);
        assertTrue("succeeded to create a story with no title", storyNumber == 0);
        assertFalse("Story exists with no title", bridge.isStoryExists(storyNumber));
    }

    @Test
    public void noText() {
        storyNumber = bridge.createStory(storyTitle, "");
        assertTrue("succeeded to create a story with no text", storyNumber == 0);
        assertFalse("Story exists with no text", bridge.isStoryExists(storyNumber));
    }

    @Test
     public void randomNumberOfStories() {
        int randomNumber = getRandomNumber();
        int[] storiesNumber = new int[randomNumber];

        System.out.println("will create " + randomNumber + " stories");

        // save story number
        for (int i=1; i<=randomNumber; i++){
            storiesNumber[i-1] = bridge.createStory(storyTitle, storyText);
        }

        // assertions
        for (int i=0; i<randomNumber; i++){
            assertFalse("Could not create a story number " + i+1 , storiesNumber[i]== 0);
            assertTrue("Story numer" + i+1 + "is not exists", bridge.isStoryExists(storiesNumber[i]));
        }
    }

}
