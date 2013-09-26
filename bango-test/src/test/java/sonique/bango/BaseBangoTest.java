package sonique.bango;

import com.googlecode.yatspec.state.givenwhenthen.TestState;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import sonique.bango.domain.Agent;
import sonique.bango.driver.AppPool;
import sonique.bango.driver.ScenarioDriver;
import sonique.bango.driver.SupermanApp;

public class BaseBangoTest extends TestState {

    private static final int PORT = 8081;
    private static BangoTestRunner bangoTestRunner;
    private static AppPool appPool;

    protected SupermanApp supermanApp;

    @BeforeClass
    public static void startBango() throws Exception {
        bangoTestRunner = new BangoTestRunner(PORT);

        bangoTestRunner.start();
        appPool = new AppPool(PORT, 1);
    }

    @AfterClass
    public static void stopBango() throws Exception {
        appPool.shutdown();

        bangoTestRunner.stop();
    }

    @Before
    public void borrow() throws Exception {
        supermanApp = appPool.borrow();
    }

    @After
    public void giveBack() throws Exception {
        appPool.giveBack(supermanApp);
    }

    protected void register(Agent agent) {
        scenarioDriver().registerAgent(agent);
    }

    protected ScenarioDriver scenarioDriver() {
        return bangoTestRunner.scenarioDriver();
    }
}