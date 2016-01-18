package acceptance.core;


import acceptance.kakaka.BridgeAPI;
import acceptance.kakaka.BridgeProxy;
import acceptance.kakaka.BridgeReal;
import org.junit.Before;

/**
 * Created by Sharon Kerzman on 12/05/2015.
 */
public class TestBase {

    protected BridgeAPI bridge;

    public void setRealBridge(String whichBridge) {
        if (whichBridge.equals("real")) {
            bridge = new BridgeReal();
        } else {
            bridge = new BridgeProxy();
        }
    }

    @Before
    public void init() {
        setRealBridge("fake");
    }
}
