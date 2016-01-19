package usecases;

import exceptions.BrowseStoryException;
import lang.SafeObject;
import model.Model;
import model.Story;
import org.junit.Test;
import usecases.Stories.BrowseStory;
import usecases.Stories.CreateStory;
import usecases.core.TestBase;
import usecases.utils.StoryDetailForTest;

import java.util.Collection;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class BrowseStoryTest extends TestBase {

    @Test
    public void oneStory_valid_returnOneStoryWithSameId_5_1() {
        // arrange
        String storyId = getNewStoryId(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());
        BrowseStory browseStory = new BrowseStory(new SafeObject<Model>(model));

        // act
        browseStory.perform();

        //assert
        assertTrue("couldn't find the story", browseStory.getStories().contains(storyId));
        assertEquals("couldn't find the story", browseStory.getStories().size(), 1);
    }

    @Test
    public void threeStories_valid_returnThreeStories_5_2() {
        // arrange
        String firstStory = getNewStoryId(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());
        String secondStory = getNewStoryId(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());
        String thirdStory = getNewStoryId(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());

        BrowseStory browseStory = new BrowseStory(new SafeObject<Model>(model));

        // act
        browseStory.perform();
        Collection<Story> stories = browseStory.getStories();

        //assert
        assertTrue("couldn't find the story", stories.contains(firstStory));
        assertTrue("couldn't find the story", stories.contains(secondStory));
        assertTrue("couldn't find the story", stories.contains(thirdStory));
        assertEquals("couldn't find the story", stories.size(), 3);
    }

    @Test(expected = BrowseStoryException.class)
    public void noStory_5_3() {
        // arrange
        BrowseStory browseStory = new BrowseStory(new SafeObject<Model>(model));

        // act
        browseStory.perform();
        Collection<Story> stories = browseStory.getStories();

        //assert
        assertEquals("couldn't find the story", stories.size(), 0);
    }

    private String getNewStoryId(String author, String title, String body) {
        CreateStory story = new CreateStory(new SafeObject<Model>(model), title, "cookie-dough", title, body);
        story.perform();
        return story.getStory().getId();
    }
}
