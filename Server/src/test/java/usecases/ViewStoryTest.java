package usecases;

import lang.SafeObject;
import model.Model;
import model.Paragraph;
import model.Story;
import org.junit.Before;
import org.junit.Test;
import usecases.Stories.ContinueStory;
import usecases.Stories.CreateStory;
import usecases.Stories.ViewStory;
import usecases.core.TestBase;
import usecases.utils.StoryDetailForTest;

import static org.junit.Assert.assertEquals;

/**
 * Created by Shai on 18/01/2016.
 */
public class ViewStoryTest extends TestBase {

    private Story story;
    private Paragraph root;

    @Before
    public void story(){
        CreateStory usecaseCreate = new CreateStory(new SafeObject<Model>(model), StoryDetailForTest.TITLE.getValue(), "cookie-dough",StoryDetailForTest.BODY.getValue(), StoryDetailForTest.AUTHOR.getValue());
        usecaseCreate.perform();
        story = usecaseCreate.getStory();
        root = story.getRoot();
    }

    @Test
    public void go_through_linear_Story_tree() {
        ContinueStory usecaseContinue = new ContinueStory(new SafeObject<Model>(model), root, StoryDetailForTest.TITLE.getValue(),StoryDetailForTest.PARAGRAPH_TEXT.getValue(), root.getUser(), story);
        usecaseContinue.perform();
        Paragraph para2 = usecaseContinue.getParagraph();
        ContinueStory usecaseContinue2 = new ContinueStory(new SafeObject<Model>(model), para2, StoryDetailForTest.TITLE.getValue(), StoryDetailForTest.PARAGRAPH_TEXT_SECOND.getValue(), para2.getUser(), story);
        usecaseContinue2.perform();
        Paragraph para3 = usecaseContinue2.getParagraph();

        ViewStory useCaseView = new ViewStory(new SafeObject<Model>(model), story, null);
        useCaseView.perform();
        Paragraph paraAns1 = useCaseView.getParagraph();

        assertEquals(root.getId(), paraAns1.getId());

        ViewStory useCaseView2 = new ViewStory(new SafeObject<Model>(model), story, paraAns1.getChildren().iterator().next().getId());
        useCaseView2.perform();
        Paragraph paraAns2 = useCaseView2.getParagraph();

        assertEquals(para2.getId(), paraAns2.getId());

        ViewStory useCaseView3 = new ViewStory(new SafeObject<Model>(model), story, paraAns2.getChildren().iterator().next().getId());
        useCaseView3.perform();
        Paragraph paraAns3 = useCaseView3.getParagraph();

        assertEquals(para3.getId(), paraAns3.getId());

    }

    @Test
    public void get_root_with_null_param() {
        ViewStory useCaseView = new ViewStory(new SafeObject<Model>(model), story, null);
        useCaseView.perform();
        Paragraph paraAns1 = useCaseView.getParagraph();

        assertEquals(root.getId(), paraAns1.getId());
    }
}
