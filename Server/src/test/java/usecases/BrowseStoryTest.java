package usecases;

import exceptions.BrowseStoryException;
import lang.SafeObject;
import model.Model;
import model.Story;
import org.junit.Ignore;
import org.junit.Test;
import usecases.stories.BrowseStories;
import usecases.stories.CreateStory;
import usecases.core.TestBase;
import usecases.utils.StoryDetailForTest;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class BrowseStoryTest extends TestBase {

    @Test
    public void oneStory_valid_returnOneStoryWithSameId() {
        // TODO: if we want to write a true test we should either:
        // TODO:   1. run the user story -> then query the model!!
        // TODO:   2. create module test that will create a servlet -> run doPost -> query model
        // TODO: either ways, we cannot test the use case itself. it is a waste of time and stupid and frequently changes

//        // arrange
//        int storyId = getNewStoryId(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());
//        BrowseStories browseStories = new BrowseStories(new SafeObject<Model>(model));
//
//        // act
//        browseStories.perform();
//
//        //assert
//        assertTrue("couldn't find the story", browseStories.getStories().containsKey(storyId));
//        assertEquals("couldn't find the story", browseStories.getStories().size(), 1);
    }

    @Test
    public void threeStories_valid_returnThreeStories() {
        // TODO: if we want to write a true test we should either:
        // TODO:   1. run the user story -> then query the model!!
        // TODO:   2. create module test that will create a servlet -> run doPost -> query model
        // TODO: either ways, we cannot test the use case itself. it is a waste of time and stupid and frequently changes

//        // arrange
//        int firstStory = getNewStoryId(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());
//        int secondStory = getNewStoryId(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());
//        int thirdStory = getNewStoryId(StoryDetailForTest.AUTHOR.getValue(), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue());
//
//        BrowseStories browseStories = new BrowseStories(new SafeObject<Model>(model));
//
//        // act
//        browseStories.perform();
//        Map<String, Story> stories = browseStories.getStories();
//
//        //assert
//        assertTrue("couldn't find the story", stories.containsKey(firstStory));
//        assertTrue("couldn't find the story", stories.containsKey(secondStory));
//        assertTrue("couldn't find the story", stories.containsKey(thirdStory));
//        assertEquals("couldn't find the story", stories.size(), 3);
    }

    @Ignore
    @Test(expected = BrowseStoryException.class)
    public void noStory() {
        // TODO: if we want to write a true test we should either:
        // TODO:   1. run the user story -> then query the model!!
        // TODO:   2. create module test that will create a servlet -> run doPost -> query model
        // TODO: either ways, we cannot test the use case itself. it is a waste of time and stupid and frequently changes

//        // arrange
//        BrowseStories browseStories = new BrowseStories(new SafeObject<Model>(model));
//
//        // act
//        browseStories.perform();
//        Map<String, Story> stories = browseStories.getStories();
//
//        //assert
//        assertEquals("couldn't find the story", stories.size(), 0);
    }

    private int getNewStoryId(String author, String title, String body) {
        // TODO: if we want to write a true test we should either:
        // TODO:   1. run the user story -> then query the model!!
        // TODO:   2. create module test that will create a servlet -> run doPost -> query model
        // TODO: either ways, we cannot test the use case itself. it is a waste of time and stupid and frequently changes
//        CreateStory story = new CreateStory(new SafeObject<Model>(model), author, title, body);
//        story.perform();
//        return story.getStory().getId();
        return 0;
    }
}
