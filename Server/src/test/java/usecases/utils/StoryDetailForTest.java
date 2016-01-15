package usecases.utils;

/**
 * Created by Kerzman on 12/23/2015
 */
public enum StoryDetailForTest {
    // usernames
    AUTHOR("B.J. Rollin"),
    TITLE("True Detective"),
    BODY("Once upon a time i was a girl");
    private final String value;

    StoryDetailForTest(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
