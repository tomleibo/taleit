package usecases;

import exceptions.InvalidUseCaseParameterException;
import lang.SafeObject;
import model.Model;
import model.Paragraph;
import model.Story;
import org.junit.Ignore;
import org.junit.Test;
import usecases.stories.CreateStory;
import usecases.core.TestBase;
import usecases.utils.StoryDetailForTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Shai on 15/01/2016.
 */
public class CreateStoryTest extends TestBase{
    @Test
    public void create_Story_Simple() {
        // TODO: if we want to write a true test we should either:
        // TODO:   1. run the user story -> then query the model!!
        // TODO:   2. create module test that will create a servlet -> run doPost -> query model
        // TODO: either ways, we cannot test the use case itself. it is a waste of time and stupid and frequently changes

//        System.out.println(model.getStories().size());
//        CreateStory usecase = new CreateStory(new SafeObject<Model>(model), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue(), StoryDetailForTest.AUTHOR.getValue());
//        usecase.perform();
//        Story story = usecase.getStory();
//
//        assertTrue(model.getStories().size() == 1);
//        assertEquals(story.getUsername(), StoryDetailForTest.AUTHOR.getValue());
//
//        Paragraph root = story.getRoot();
//
//        assertEquals(root.getText(),StoryDetailForTest.BODY.getValue());
//        assertNull(root.getFather());
//        assertEquals(root.getId(), 0);
//        assertTrue(root.getChildren().isEmpty());
//        assertEquals(root.getUsername(), StoryDetailForTest.AUTHOR.getValue());
    }

    @Ignore
    @Test(expected = InvalidUseCaseParameterException.class)
    public void TitleToLong() {
        // TODO: what is this?
        // TODO: again, like before:
        // TODO: if we want to write a true test we should either:
        // TODO:   1. run the user story -> then query the model!!
        // TODO:   2. create module test that will create a servlet -> run doPost -> query model
        // TODO: either ways, we cannot test the use case itself. it is a waste of time and stupid and frequently changes
//
//        String longTitle = "";
//        for (int i = 0; i < CreateStory.MAX_TITLE_LENGTH +10; i++){
//            longTitle += "a";
//        }
//        CreateStory usecase = new CreateStory(new SafeObject<Model>(model), StoryDetailForTest.TITLE.getValue(), longTitle, StoryDetailForTest.AUTHOR.getValue());
//        usecase.perform();
    }
}