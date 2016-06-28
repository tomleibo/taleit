package acceptance.utils;

import acceptance.core.LoggedInBaseAcceptance;
import model.Categories;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by sharonk on 1/18/2016
 */
public class DummyDB extends LoggedInBaseAcceptance {
//story title, root title, root text, catgory
    public static String[][] simpleStories = {
        {"Dumb and more dumb", "the rubber chicken", "He looked in the chicken eyes and kissed it gently", Categories.COMEDY.getValue()},
        {"King of the humus", "She wasn't the prettiest", "She gave me the puppy eyes in a gas station", Categories.COMEDY.getValue()},
        {"Timetravel is a bitch", "past and present", "Hold the door! Hold the door, holddoor, hodor...", Categories.DRAMA.getValue()},
        {"To kill to drink to die", "vodka is my only lover", "As a strong independent women who needed no men in here life, I was lonely", Categories.DRAMA.getValue()},
        {"Ender's new game", "Chess", "chess in space is not so fun", Categories.SCIFI.getValue()},
        {"A biography of the first Nazi cleaning lady", "Intro", "It all started in 1942", Categories.OTHERS.getValue()},
        {"Thoughts on drugs",
                "A Little Egoism Is Good",
                    "Fuck the big plan, Fuck your tinyness next to the entire universe, " +
                    "Fuck the meaningless of life. You matter to you, You are everything to you, " +
                    "Whatever you do is important...(not only, but also) to you!", Categories.OTHERS.getValue()},
    };

    public static String[][] Complicatedstories = {
            {"Dumb and more dumb", "the rubber chicken", "He looked in the chicken eyes and kissed it gently", Categories.COMEDY.getValue()}
    };

    @Test
  //  @Ignore
    public void eraseInjectDummyDB() {
        bridge.initServer();
        bridge.signUp(userName, password);
        bridge.login(userName,password);
        String storyId = null;
        for (String[] story : simpleStories) {
            if (storyId == null) {
                storyId = bridge.createStory(story[0], story[1], story[2], story[3]);
            }else{
                bridge.createStory(story[0], story[1], story[2], story[3]);
            }
        }
        String rootParagraph = bridge.getRootParagraph(storyId);
        String para = bridge.createParagraph(
                storyId,
                "the continue of the story title",
                "the continue of the story title",
                rootParagraph);
        InjectRandomStructureStoryTree(5);
    }

    private void InjectComplicatedStructureStories(){
        // Height = 2, children = 2
        // Height = 3, LinearStory
        // Height = 3, UnEvenTree, 2 children on second level, one child on third level
        // Height = 4, UnEvenTree, 4 children on second level, 4 children on third level from the same node, two children on forth level of the same node
    }

    private void InjectRandomStructureStoryTree(int size){
        String storyNumber = bridge.createStory("RandomStructureStoryTree", "Hey I'm A Main Paragraph Title", "And " +
                                                        "I'm The Maim paragraph sexy Body",
                                                Categories
                .HORROR
                .getValue());
        Set<String> nodes = new HashSet<>();
        nodes.add(bridge.getRootParagraph(storyNumber));
        for (int i = 1; i < size; i++){
            Object[] nodeArr = nodes.toArray();
            String node = (String) nodeArr[ThreadLocalRandom.current().nextInt(0 , nodes.size())];
            String paragraphNumber = bridge.createParagraph(storyNumber, "Title " + i, "Body " + i, node);
            nodes.add(paragraphNumber);
            paragraphNumber = bridge.createParagraph(storyNumber, "Second Title " + i, "Second Body " + i, node);
            nodes.add(paragraphNumber);
        }
    }
}