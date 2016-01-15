package usecases.utils;

import model.Paragraph;

/**
 * Created by Kerzman on 12/23/2015
 */
public enum StoryDetailForTest {
    AUTHOR("B.J. Rollin"),
    TITLE("True Detective"),
    BODY("Once upon a time i was a girl"),

    PARAGRAPH_TEXT("and it feels great"),
    PARAGRAPH_TEXT_SECOND("and it feels awful");
    private final String value;

    StoryDetailForTest(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
