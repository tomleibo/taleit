package usecases;

/**
 * Created by Kerzman on 12/23/2015
 */
public enum UserDetailForTest {
    // usernames
    USERNAME_VALID("sharon@gmail.com"),
    USERNAME_INVALID("sharon"),
    USERNAME_FIRST_TIME("shairip@homale.com"),

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
