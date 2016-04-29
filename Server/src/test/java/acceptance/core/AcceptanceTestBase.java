package acceptance.core;

import acceptance.bridge.BridgeAPI;
import acceptance.bridge.BridgeProxy;
import acceptance.bridge.BridgeReal;
import acceptance.utils.UserDetailForTest;
import org.junit.Before;

import java.util.Collection;
import java.util.Random;

/**
 * Created by Sharon Kerzman on 12/05/2015.
 */
public class AcceptanceTestBase {

    protected BridgeAPI bridge;
    protected String userName = UserDetailForTest.FIRST_USERNAME_VALID.getValue();
    protected String password = UserDetailForTest.FIRST_PASSWORD_VALID.getValue();
    protected String invalidName = UserDetailForTest.USERNAME_INVALID.getValue();
    protected String shortPassword = UserDetailForTest.PASSWORD_TO_SHORT.getValue();
    protected String secondUserName = UserDetailForTest.SECOND_USERNAME.getValue();
    protected String secondPassword = UserDetailForTest.SECOND_PASSWORD_VALID.getValue();

    public void setRealBridge(String whichBridge) {
        if (whichBridge.equals("real")) {
            bridge = new BridgeReal();
        } else {
            bridge = new BridgeProxy();
        }
    }

    @Before
    public void init() {
//        setRealBridge("fake");
        setRealBridge("real");
        bridge.initServer();
    }

    //from 2 to 12
    public int getRandomNumber() {
        Random randomGenerator = new Random();
        return (randomGenerator.nextInt(10) + 2);
    }

    public boolean isStoryExists(String story){
        return bridge.browseStories().contains(story);
    }
}
