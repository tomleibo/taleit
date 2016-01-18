package usecases;

import lang.SafeObject;
import model.Model;
import model.Paragraph;
import model.Story;
import org.junit.Test;
import usecases.Stories.CreateStory;
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
        CreateStory usecase = new CreateStory(new SafeObject<Model>(model), StoryDetailForTest.TITLE.getValue(), "cookie-dough", StoryDetailForTest.BODY.getValue(), StoryDetailForTest.AUTHOR.getValue());
        usecase.perform();
        Story story = usecase.getStory();

        assertTrue(model.getStories().size() == 1);
        assertEquals(story.getUser().getUsername(), StoryDetailForTest.AUTHOR.getValue());

        Paragraph root = story.getRoot();

        assertEquals(root.getText(),StoryDetailForTest.BODY.getValue());
        assertNull(root.getFather());
//        assertEquals(root.getId(), 0);
        assertTrue(root.getChildren().isEmpty());
        assertEquals(root.getUser(), StoryDetailForTest.AUTHOR.getValue());
    }

//    @Test(expected = InvalidUseCaseParameterException.class)
//    public void TitleToLong() {
//        String longTitle = "";
//        for (int i = 0; i < CreateStory.MAX_TITLE_LENGTH +10; i++){
//            longTitle += "a";
//        }
//        CreateStory usecase = new CreateStory(new SafeObject<Model>(model), StoryDetailForTest.TITLE.getValue(), longTitle, StoryDetailForTest.AUTHOR.getValue());
//        usecase.perform();
//    }



}
