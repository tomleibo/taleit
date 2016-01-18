package usecases;

import exceptions.StoryException;
import lang.SafeObject;
import model.Model;
import model.Paragraph;
import model.Story;
import org.junit.Before;
import usecases.Stories.ContinueStory;
import usecases.Stories.CreateStory;
import usecases.core.TestBase;
import org.junit.Test;
import usecases.utils.StoryDetailForTest;

import static org.junit.Assert.assertEquals;


/**
 * Created by Shai on 18/01/2016.
 */
public class ContinueStoryTest extends TestBase{
    Story story;
    Paragraph root;

    @Before
    public void story(){
        CreateStory usecaseCreate = new CreateStory(new SafeObject<Model>(model), StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.BODY.getValue(), StoryDetailForTest.AUTHOR.getValue());
        usecaseCreate.perform();
        story = usecaseCreate.getStory();
        root = story.getRoot();
    }

    @Test
    public void add_paragraph(){

        ContinueStory usecaseContinue = new ContinueStory(new SafeObject<Model>(model), root, StoryDetailForTest.PARAGRAPH_TEXT.getValue(), StoryDetailForTest.AUTHOR.getValue(), story);
        usecaseContinue.perform();
        assertEquals(1, root.getChildren().size());
    }

    @Test(expected = StoryException.class)
    public void add_paragraph_father_null(){
        ContinueStory usecaseContinue = new ContinueStory(new SafeObject<Model>(model), null, StoryDetailForTest.PARAGRAPH_TEXT.getValue(), StoryDetailForTest.AUTHOR.getValue(), story);
        usecaseContinue.perform();

    }

    @Test
    public void add_2_paragraphs_in_a_line(){
        ContinueStory usecaseContinue = new ContinueStory(new SafeObject<Model>(model), root, StoryDetailForTest.PARAGRAPH_TEXT.getValue(), StoryDetailForTest.AUTHOR.getValue(), story);
        usecaseContinue.perform();
        Paragraph para2 = usecaseContinue.getParagraph();

        assertEquals(1, root.getChildren().size());

        ContinueStory usecaseContinue2 = new ContinueStory(new SafeObject<Model>(model), para2, StoryDetailForTest.PARAGRAPH_TEXT_SECOND.getValue(), StoryDetailForTest.AUTHOR.getValue(), story);
        usecaseContinue2.perform();
        Paragraph para3 = usecaseContinue2.getParagraph();

        assertEquals(1, para2.getChildren().size());
        assertEquals(0, para3.getChildren().size());

    }

    @Test
    public void add_2_paragraphs_to_the_root(){
        ContinueStory usecaseContinue = new ContinueStory(new SafeObject<Model>(model), root, StoryDetailForTest.PARAGRAPH_TEXT.getValue(), StoryDetailForTest.AUTHOR.getValue(), story);
        usecaseContinue.perform();
        Paragraph para2 = usecaseContinue.getParagraph();

        assertEquals(1, root.getChildren().size());

        ContinueStory usecaseContinue2 = new ContinueStory(new SafeObject<Model>(model), root, StoryDetailForTest.PARAGRAPH_TEXT_SECOND.getValue(), StoryDetailForTest.AUTHOR.getValue(), story);
        usecaseContinue2.perform();
        Paragraph para3 = usecaseContinue2.getParagraph();

        assertEquals(2, root.getChildren().size());
        assertEquals(0, para2.getChildren().size());
        assertEquals(0, para3.getChildren().size());
    }


}
