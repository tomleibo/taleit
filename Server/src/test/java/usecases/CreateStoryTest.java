package usecases;

import exceptions.InvalidUseCaseParameterException;
import lang.SafeObject;
import model.Categories;
import model.Model;
import model.Paragraph;
import model.Story;
import model.User;
import org.junit.Test;
import usecases.Stories.CreateStory;
import usecases.core.TestBase;
import usecases.utils.StoryDetailForTest;
import usecases.utils.UserDetailForTest;

import static org.junit.Assert.*;


/**
 * Created by Shai on 15/01/2016.
 */
public class CreateStoryTest extends TestBase{
    @Test
    public void create_Story_Simple_4_1() {
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        String pass = UserDetailForTest.PASSWORD_VALID.getValue();
        model.addUser(new User(userName, pass));
        String cookie = model.loginUser(userName,pass);

        CreateStory usecase = new CreateStory(new SafeObject<Model>(model), StoryDetailForTest.TITLE.getValue(),
                                              cookie, StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY
                                                      .getValue(), Categories.ADULTS);
        usecase.perform();
        Story story = usecase.getStory();

        assertTrue(model.getStories().size() == 1);
        assertEquals(story.getUser().getUsername(), userName);

        Paragraph root = story.getRoot();

        assertEquals(root.getText(),StoryDetailForTest.BODY.getValue());
        assertNull(root.getFather());
        assertTrue(root.getChildren().isEmpty());
        assertEquals(root.getUser().getUsername(), userName);
    }

    @Test(expected = InvalidUseCaseParameterException.class)
    public void TitleToLong() {
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        String pass = UserDetailForTest.PASSWORD_VALID.getValue();
        model.addUser(new User(userName, pass));
        String cookie = model.loginUser(userName,pass);

        String longTitle = "";
        for (int i = 0; i < CreateStory.MAX_TITLE_LENGTH +10; i++){
            longTitle += "a";
        }
        CreateStory usecase = new CreateStory(new SafeObject<Model>(model), longTitle, cookie, StoryDetailForTest
                .TITLE.getValue(), StoryDetailForTest.PARAGRAPH_TEXT.getValue(), Categories.ADULTS);
        usecase.perform();
    }



}
