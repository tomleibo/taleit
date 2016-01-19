package acceptance.utils;

/**
 * Created by Kerzman on 12/23/2015
 */
public enum StoryDetailForTest {
    FIRST_AUTHOR("B.J. Rollin"),
    FIRST_TITLE("True Detective"),
    FIRST_BODY("Once upon a time i was a girl"),

    FITST_PARAGRAPH_TITLE("positive thinking"),
    FITST_PARAGRAPH_TEXT("and it feels great"),
    SECOND_PARAGRAPH_TITLE("negative thinking"),
    FIRST_PARAGRAPH_TEXT_SECOND("and it feels awful");
    private final String value;

    StoryDetailForTest(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
