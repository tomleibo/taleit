package acceptance.tests;

import acceptance.core.LoggedInBaseAcceptance;
import acceptance.utils.StoryDetailForTest;
import model.Paragraph;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class ContinueStoryAcceptance extends LoggedInBaseAcceptance {
    int storyNumber;
    int paragraphNumber;
    String paragraphTitle = StoryDetailForTest.FITST_PARAGRAPH_TITLE.getValue();
    String paragraphText = StoryDetailForTest.FITST_PARAGRAPH_TEXT.getValue();
    @Before
    public void init(){
        super.init();
    }

    @Test
    public void validParagraph_6_1() {
        storyNumber = bridge.createStory(storyTitle, storyText);
        assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == 0);
        assertTrue("Story is not exists", bridge.isStoryExists(storyNumber));

        paragraphNumber = bridge.createParagraph(storyNumber, paragraphTitle, paragraphText);
        assertFalse("Could not create paragraph with Title: " + paragraphTitle + " and body: " + paragraphText, paragraphNumber == 0);
        assertTrue("paragraph not exists", bridge.isParagraphExists(storyNumber, paragraphNumber));
    }

    @Test
    public void noStory_6_2() {
        storyNumber = 100; // random number
        paragraphNumber = bridge.createParagraph(storyNumber, paragraphTitle, paragraphText);
        assertFalse("Could not create paragraph with Title: " + paragraphTitle + " and body: " + paragraphText, paragraphNumber == 0);
        assertTrue("paragraph not exists", bridge.isParagraphExists(storyNumber, paragraphNumber));
    }

    @Test
    public void noTitle_6_3() {
        storyNumber = bridge.createStory(storyTitle, storyText);
        assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == 0);
        assertTrue("Story is not exists", bridge.isStoryExists(storyNumber));

        paragraphNumber = bridge.createParagraph(storyNumber, "", paragraphText);
        assertFalse("Could not create paragraph with no Title",
                paragraphNumber == 0);
        assertTrue("paragraph not exists", bridge.isParagraphExists(storyNumber, paragraphNumber));
    }

    @Test
    public void noText_6_4() {
        storyNumber = bridge.createStory(storyTitle, storyText);
        assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == 0);
        assertTrue("Story is not exists", bridge.isStoryExists(storyNumber));

        paragraphNumber = bridge.createParagraph(storyNumber, paragraphTitle, "");
        assertFalse("Succeed to create paragraph with no Text", paragraphNumber == 0);
        assertTrue("paragraph not exists", bridge.isParagraphExists(storyNumber, paragraphNumber));
    }

    @Test
     public void randomNumberOfparagraph_6_5() {
        int randomNumber = getRandomNumber();
        int[] paragraphsNumber = new int[randomNumber];

        System.out.println("will create " + randomNumber + " stories");

        storyNumber = bridge.createStory(storyTitle, storyText);
        assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == 0);
        assertTrue("Story is not exists", bridge.isStoryExists(storyNumber));

        // save paragraphs number
        for (int i=1; i<=randomNumber; i++){
            paragraphsNumber[i-1] = bridge.createParagraph(storyNumber, paragraphTitle, paragraphText);
        }

        // assertions
        for (int i=0; i<randomNumber; i++){
            assertFalse("Could not create paragraph number " + i+1 , paragraphsNumber[i]== 0);
            assertTrue("paragraph number" + i+1 + "is not exists", bridge.isParagraphExists(storyNumber, paragraphsNumber[i]));
        }
    }

}
