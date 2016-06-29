package taleit.usecases;

import taleit.exceptions.StoryException;
import taleit.lang.SafeObject;
import taleit.model.Categories;
import taleit.model.Model;
import taleit.model.Paragraph;
import taleit.model.Story;
import org.junit.Before;
import taleit.usecases.Stories.ContinueStory;
import taleit.usecases.Stories.CreateStory;
import taleit.usecases.core.TestBase;
import org.junit.Test;
import taleit.usecases.utils.StoryDetailForTest;

import static org.junit.Assert.assertEquals;


/**
 * Created by Shai on 18/01/2016.
 */
public class ContinueStoryTest extends TestBase{
    Story story;
    Paragraph root;

    @Before
    public void story(){
        CreateStory usecaseCreate = new CreateStory(new SafeObject<Model>(model), StoryDetailForTest.TITLE.getValue()
                , "cookie dough",StoryDetailForTest.BODY.getValue(), StoryDetailForTest.AUTHOR.getValue(), Categories.ADULTS);
        usecaseCreate.perform();
        story = usecaseCreate.getStory();
        root = story.getRoot();
    }

    @Test
    public void add_paragraph_6_1(){
        ContinueStory usecaseContinue = new ContinueStory(new SafeObject<Model>(model), root, StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.PARAGRAPH_TEXT.getValue(), root.getUser(), story);
        usecaseContinue.perform();
        assertEquals(1, root.getChildren().size());
    }

    @Test(expected = StoryException.class)
    public void add_paragraph_father_null_6_2(){
        ContinueStory usecaseContinue = new ContinueStory(new SafeObject<Model>(model), null, StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.PARAGRAPH_TEXT.getValue(), null ,story);
        usecaseContinue.perform();
    }

    @Test
    public void add_2_paragraphs_in_a_line_6_3(){
        ContinueStory usecaseContinue = new ContinueStory(new SafeObject<Model>(model), root, StoryDetailForTest.TITLE.getValue() ,StoryDetailForTest.PARAGRAPH_TEXT.getValue(), root.getUser(), story);
        usecaseContinue.perform();
        Paragraph para2 = usecaseContinue.getParagraph();

        assertEquals(1, root.getChildren().size());

        ContinueStory usecaseContinue2 = new ContinueStory(new SafeObject<Model>(model), para2, StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.PARAGRAPH_TEXT_SECOND.getValue(), para2.getUser(), story);
        usecaseContinue2.perform();
        Paragraph para3 = usecaseContinue2.getParagraph();

        assertEquals(1, para2.getChildren().size());
        assertEquals(0, para3.getChildren().size());

    }

    @Test
    public void add_2_paragraphs_to_the_root_6_4(){
        ContinueStory usecaseContinue = new ContinueStory(new SafeObject<Model>(model), root, StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.PARAGRAPH_TEXT.getValue(), root.getUser(), story);
        usecaseContinue.perform();
        Paragraph para2 = usecaseContinue.getParagraph();

        assertEquals(1, root.getChildren().size());

        ContinueStory usecaseContinue2 = new ContinueStory(new SafeObject<Model>(model), root, StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.PARAGRAPH_TEXT_SECOND.getValue(), root.getUser(), story);
        usecaseContinue2.perform();
        Paragraph para3 = usecaseContinue2.getParagraph();

        assertEquals(2, root.getChildren().size());
        assertEquals(0, para2.getChildren().size());
        assertEquals(0, para3.getChildren().size());
    }
}