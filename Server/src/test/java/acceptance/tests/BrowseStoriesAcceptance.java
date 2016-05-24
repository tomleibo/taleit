package acceptance.tests;

import acceptance.core.LoggedInBaseAcceptance;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Categories;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class BrowseStoriesAcceptance extends LoggedInBaseAcceptance {
    String storyNumber;
    @Before
    public void init(){
        super.init();
    }

    @Test
    public void noStories_5_1() {
        assertNull("There are stories in browser without creating one" , bridge.browseStories());
    }

    @Test
    public void oneStory_5_2() {
        storyNumber = bridge.createStory(storyTitle, storyTitle, storyText, null);
        assertFalse("Could not create story ", storyNumber == null);
        //assert for browse
        assertTrue("Could not find story in browser", bridge.browseStories().contains(storyNumber));
    }

    @Test
     public void randomNumberOfStories_5_3() {
        int randomNumber = getRandomNumber();
        String[] storiesNumber = new String[randomNumber];

        System.out.println("will create " + randomNumber + " stories");

        // save story number
        for (int i=1; i<=randomNumber; i++){
            storiesNumber[i-1] = bridge.createStory(storyTitle, storyTitle, storyText, null);
        }

        // assertions
        for (int i=0; i<randomNumber; i++){
            assertFalse("Could not create a story number: " + i+1 , storiesNumber[i]== null);
            assertTrue("Could not find story number: " + i+1 + " in browser", bridge.browseStories().contains(storiesNumber[i]));
        }
    }
}