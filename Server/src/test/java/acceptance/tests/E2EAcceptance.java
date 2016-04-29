package acceptance.tests;

import acceptance.core.AcceptanceTestBase;
import acceptance.utils.StoryDetailForTest;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class E2EAcceptance extends AcceptanceTestBase {
    private String[] users;
    private String[] stories;
    private String[][] paragraphs;
    private int randomNumber;

    protected String storyTitle = StoryDetailForTest.FIRST_TITLE.getValue();
    protected String storyText = StoryDetailForTest.FIRST_BODY.getValue();

    String paragraphTitle = StoryDetailForTest.FITST_PARAGRAPH_TITLE.getValue();
    String paragraphText = StoryDetailForTest.FITST_PARAGRAPH_TEXT.getValue();

    @Before
    public void init(){
        super.init();
        randomNumber = getRandomNumber();
        users = new String[randomNumber];
        stories = new String[randomNumber];
        paragraphs = new String[randomNumber][randomNumber];

        String userName = "guri";
        String userPostfix = "@gmail.com";

        System.out.println("will perform " + randomNumber + " of Sign ups");
        for (int i=1; i<=randomNumber; i++){
            String actualUsername = userName+i+userPostfix;
            users[i-1]= actualUsername;
            assertTrue("faild to perform Signup number " + i ,
                    bridge.signUp(actualUsername, password));
        }

    }

    @Test
    public void loginCreateStoryAndLogout_8_1(){

        for (int i = 0; i<randomNumber; i++){
            assertTrue("faild to perform Signup number " + i ,
                    bridge.login(users[i], password));

            String storyNumber = bridge.createStory(storyTitle, storyTitle, storyText);
            stories[i] = storyNumber;
            assertFalse("Could not create a story with Title: " + storyTitle + " and body: " + storyText, storyNumber == null);

            assertTrue("failed to logout without login: " + userName, bridge.logout());

        }
        for (int i = 0; i<randomNumber; i++) {
            assertTrue("Story number " + i+1 + " is not exists", isStoryExists(stories[i]));
        }
    }

//    @Test
//    public void loginCreateStoryLogoutContinueEachStory_8_2(){
//        loginCreateStoryAndLogout_8_1();
//        for (int i = 0; i<randomNumber; i++){
//            assertTrue("faild to perform login number " + i ,
//                    bridge.login(users[i], password));
//
//            for (int j = 0; j<randomNumber; j++){
//                String paragraphNumber = bridge.createParagraph(stories[j], paragraphTitle, paragraphText, rootParagraphNumber);
//                paragraphs[i][j] = paragraphNumber;
//                assertFalse("Could not create paragraph with Title: " + paragraphTitle + " and body: " + paragraphText, paragraphNumber == null);
//            }
//
//            assertTrue("failed to logout without login: " + userName, bridge.logout());
//
//        }
//        for (int i = 0; i<randomNumber; i++) {
//            for (int j = 0 ; j<randomNumber; j++){
//                assertTrue("paragraph not exists", bridge.isParagraphExists(stories[i], paragraphs[i][j]));
//            }
//        }
//    }

}
