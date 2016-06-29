package taleit.usecases;

import taleit.lang.SafeObject;
import taleit.model.Categories;
import taleit.model.Model;
import taleit.model.Story;
import taleit.model.User;
import org.junit.Test;
import taleit.usecases.Stories.BrowseStory;
import taleit.usecases.Stories.CreateStory;
import taleit.usecases.core.TestBase;
import taleit.usecases.utils.StoryDetailForTest;
import taleit.usecases.utils.UserDetailForTest;

import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class BrowseStoryTest extends TestBase {

    @Test
    public void oneStory_valid_returnOneStoryWithSameId_5_1() {
        // arrange
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        String pass = UserDetailForTest.PASSWORD_VALID.getValue();
        model.addUser(new User(userName, pass));
        String cookie = model.loginUser(userName,pass);


        Story story = getNewStory(cookie, StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());
        BrowseStory browseStory = new BrowseStory(new SafeObject<Model>(model), null);

        // act
        browseStory.perform();

        //assert
        assertTrue("couldn't find the story", browseStory.getStories().contains(story));
        assertEquals("couldn't find the story", browseStory.getStories().size(), 1);
    }

    @Test
    public void threeStories_valid_returnThreeStories_5_2() {
        // arrange
        Story firstStory = getNewStory(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());
        Story secondStory = getNewStory(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());
        Story thirdStory = getNewStory(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());

        BrowseStory browseStory = new BrowseStory(new SafeObject<Model>(model), null);

        // act
        browseStory.perform();
        Collection<Story> stories = browseStory.getStories();

        //assert
        assertTrue("couldn't find the story", stories.contains(firstStory));
        assertTrue("couldn't find the story", stories.contains(secondStory));
        assertTrue("couldn't find the story", stories.contains(thirdStory));
        assertEquals("couldn't find the story", stories.size(), 3);
    }

    @Test
    public void noStory_5_3() {
        // arrange
        BrowseStory browseStory = new BrowseStory(new SafeObject<Model>(model), null);

        // act
        browseStory.perform();
        Collection<Story> stories = browseStory.getStories();

        //assert
        assertEquals("couldn't find the story", stories.size(), 0);
    }

    private Story getNewStory(String cookie, String title, String body) {
        CreateStory story = new CreateStory(new SafeObject<Model>(model), title, cookie, title, body, Categories.ADULTS);
        story.perform();
        return story.getStory();
    }
}
