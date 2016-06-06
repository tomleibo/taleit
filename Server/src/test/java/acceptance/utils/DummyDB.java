package acceptance.utils;

import acceptance.core.LoggedInBaseAcceptance;
import model.Categories;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


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
    public void eraseInjectDummyDB() {
        super.init();
        bridge.signUp(userName, password);
        bridge.login(userName,password);
        String storyNumber = null;
        for (String[] story : simpleStories) {
            if (storyNumber == null) {
                storyNumber = bridge.createStory(story[0], story[1], story[2], story[3]);
            }else{
                bridge.createStory(story[0], story[1], story[2], story[3]);
            }
        }
        String para = bridge.createParagraph(storyNumber, simpleStories[0][1], simpleStories[0][2], bridge.getRootParagraph(storyNumber));
        InjectComplicatedStructureStories();
    }

    private void InjectComplicatedStructureStories(){
        // Height = 2, children = 2
        // Height = 3, LinearStory
        // Height = 3, UnEvenTree, 2 children on second level, one child on third level
        // Height = 4, UnEvenTree, 4 children on second level, 4 children on third level from the same node, two children on forth level of the same node
    }

    private void InjectRandomStructureStoryTree(int size){
        String storyNumber = bridge.createStory("RandomStructureStoryTree", "1", "1", Categories.HORROR.getValue());
        Set<String> nodes = new HashSet<String>();
        nodes.toArray();
//        for (int i = 1; i < size; i++){
//            //choose a node father
//            String paragraphNumber = bridge.createParagraph(storyNumber, father, father, bridge.getRootParagraph(storyNumber));
//        }


    }

}