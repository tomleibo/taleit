package acceptance.utils;

/**
 * Created by Kerzman on 12/23/2015
 */
public enum UserDetailForTest {
    // usernames
    FIRST_USERNAME_VALID("sharon@gmail.com"),
    USERNAME_INVALID("gur"),
    SECOND_USERNAME("shairip@hotmale.co.il"),

    // passwords
    FIRST_PASSWORD_VALID("123abc"),
    SECOND_PASSWORD_VALID("IlikeU"),
    PASSWORD_INCORRECT("abc123"),
    PASSWORD_TO_SHORT("123a");

    private final String value;

    UserDetailForTest(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
