package acceptance.tests;

import acceptance.core.LoggedInBaseAcceptance;
import acceptance.utils.StoryDetailForTest;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class ContinueStoryAcceptance extends LoggedInBaseAcceptance {
    String storyNumber;
    String rootParagraphNumber;
    String paragraphNumber;
    String paragraphTitle = StoryDetailForTest.FITST_PARAGRAPH_TITLE.getValue();
    String paragraphText = StoryDetailForTest.FITST_PARAGRAPH_TEXT.getValue();
    @Before
    public void init(){
        super.init();
    }

    @Test
    public void validParagraph_6_1() {
        storyNumber = bridge.createStory(storyTitle, storyTitle, storyText);
        rootParagraphNumber = bridge.getRootParagraph(storyNumber);
        assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == null);
        assertTrue("Story is not exists", isStoryExists(storyNumber));

        paragraphNumber = bridge.createParagraph(storyNumber, paragraphTitle, paragraphText, rootParagraphNumber);
        assertFalse("Could not create paragraph with Title: " + paragraphTitle + " and body: " + paragraphText, paragraphNumber == null);
        assertTrue("paragraph not exists", bridge.isParagraphExists(storyNumber, paragraphNumber));
    }

    @Test
    public void noStory_6_2() {
        storyNumber = "100"; // random number
        paragraphNumber = bridge.createParagraph(storyNumber, paragraphTitle, paragraphText, "3864");
        assertFalse("Could not create paragraph with Title: " + paragraphTitle + " and body: " + paragraphText, paragraphNumber == null);
        assertTrue("paragraph not exists", bridge.isParagraphExists(storyNumber, paragraphNumber));
    }

    @Test
    public void noTitle_6_3() {
        storyNumber = bridge.createStory(storyTitle, storyTitle, storyText);
        assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == null);
        assertTrue("Story is not exists", isStoryExists(storyNumber));

        paragraphNumber = bridge.createParagraph(storyNumber, "", paragraphText, rootParagraphNumber);
        assertFalse("Could not create paragraph with no Title",
                paragraphNumber == null);
        assertTrue("paragraph not exists", bridge.isParagraphExists(storyNumber, paragraphNumber));
    }

    @Test
    public void noText_6_4() {
        storyNumber = bridge.createStory(storyTitle, storyTitle, storyText);
        assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == null);
        assertTrue("Story is not exists", isStoryExists(storyNumber));

        paragraphNumber = bridge.createParagraph(storyNumber, paragraphTitle, "", rootParagraphNumber);
        assertFalse("Succeed to create paragraph with no Text", paragraphNumber == null);
        assertTrue("paragraph not exists", bridge.isParagraphExists(storyNumber, paragraphNumber));
    }

    @Test
     public void randomNumberOfparagraph_6_5() {
        int randomNumber = getRandomNumber();
        String[] paragraphsNumber = new String[randomNumber];

        System.out.println("will create " + randomNumber + " stories");

        storyNumber = bridge.createStory(storyTitle, storyTitle, storyText);
        assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == null);
        assertTrue("Story is not exists", isStoryExists(storyNumber));

        // save paragraphs number
        for (int i=1; i<=randomNumber; i++){
            paragraphsNumber[i-1] = bridge.createParagraph(storyNumber, paragraphTitle, paragraphText, rootParagraphNumber);
        }

        // assertions
        for (int i=0; i<randomNumber; i++){
            assertFalse("Could not create paragraph number " + i+1 , paragraphsNumber[i]== null);
            assertTrue("paragraph number" + i+1 + "is not exists", bridge.isParagraphExists(storyNumber, paragraphsNumber[i]));
        }
    }
}
