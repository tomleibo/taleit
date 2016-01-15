package usecases.utils;

/**
 * Created by Kerzman on 12/23/2015
 */
public enum UserDetailForTest {
    // usernames
    USERNAME_VALID("sharon@gmail.com"),
    USERNAME_INVALID("gur"),
    USERNAME_FIRST_TIME("shairip@hotmale.com"),

    // passwords
    PASSWORD_VALID("123abc"),
    PASSWORD_INCORRECT("abc123"),
    PASSWORD_TO_SHORT("123a"),;

    private final String value;

    UserDetailForTest(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
