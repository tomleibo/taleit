package usecases;

/**
 * Created by sharonk on 12/23/2015
 */
public enum UserDetailForTest {
    USERNAME_VALID("sharon@gmail.com"),
    USERNAME_INVALID("sharon"),
    PASSWORD_VALID("123abc"),
    PASSWORD_TO_SHORT("123a");

    private final String value;

    UserDetailForTest(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
