package sonique.bango;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.*;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import sky.sns.spm.domain.model.DomainAgent;
import sky.sns.spm.domain.model.refdata.Role;
import sky.sns.spm.domain.model.serviceproblem.DomainServiceProblem;
import sonique.bango.app.ScenarioDriver;
import sonique.bango.app.SupermanApp;
import sonique.bango.driver.panel.*;
import sonique.bango.driver.panel.navigation.AgentStatusPanel;
import sonique.bango.driver.panel.navigation.HeaderPanel;
import sonique.bango.driver.panel.navigation.MyQueuesPanel;
import sonique.bango.driver.panel.queuedashboard.QueueDashboardTab;
import sonique.bango.scenario.ScenarioGivensBuilder;
import sonique.bango.scenario.ServiceProblemScenario;
import spm.domain.model.refdata.DomainAgentBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static sonique.bango.matcher.IsDisplayed.isDisplayed;
import static sonique.datafixtures.PrimitiveDataFixtures.someString;

@RunWith(SpecRunner.class)
public abstract class BangoYatspecTest extends OncePerSuiteBangoTest implements WithTestState {

    private final TestState testState = new TestState();
    protected SupermanApp supermanApp;
    protected DomainAgent agentForTest;

    protected DomainAgent agentForTest() {
        return new DomainAgentBuilder().with(Role.ROLE_USER).withFirstName(someString()).withLastName("A").withPassword("a").build();
    }

    @Before
    public void beforeTest() throws Exception {
        supermanApp = bangoTestEnvironment.borrowSupermanApp();
        agentForTest = agentForTest();

        scenarioDriver().registerAgent(agentForTest);
        testState.interestingGivens.add("Agent", agentForTest);
    }

    @After
    public void afterTest() throws Exception {
        HeaderPanel headerPanel = supermanApp.appContainer().headerPanel();
        if(headerPanel.isDisplayed()) {
            headerPanel.logout();
        }
        bangoTestEnvironment.releaseSupermanApp(supermanApp);
        scenarioDriver().deRegisterAgent(agentForTest);
    }

    @Override
    public TestState testState() {
        return testState;
    }

    protected void loginAgent() {
        LoginDialog loginDialog = supermanApp.loginDialog();
        loginDialog.username().enter(agentForTest.getAgentCode());
        loginDialog.password().enter("a");

        loginDialog.loginButton().click();

        assertThat(supermanApp.appContainer().headerPanel(), isDisplayed());
    }

    protected ScenarioDriver scenarioDriver() {
        return bangoTestEnvironment.scenarioDriver();
    }

    public TestState given(GivensBuilder builder) throws Exception {
        return testState.given(builder);
    }

    public TestState and(GivensBuilder builder) throws Exception {
        return given(builder);
    }

    public TestState when(ActionUnderTest actionUndertest) throws Exception {
        return testState.when(actionUndertest);
    }

    public <ItemOfInterest> TestState then(StateExtractor<ItemOfInterest> extractor, Matcher<? super ItemOfInterest> matcher) throws Exception {
        return testState.then(extractor, matcher);
    }

    public <ItemOfInterest> TestState and(StateExtractor<ItemOfInterest> stateExtractor, Matcher<? super ItemOfInterest> matcher) throws Exception {
        return then(stateExtractor, matcher);
    }

    protected StateExtractor<QueueDashboardTab> theQueueDashboardTab() {
        return new StateExtractor<QueueDashboardTab>() {
            @Override
            public QueueDashboardTab execute(CapturedInputAndOutputs inputAndOutputs) throws Exception {
                return supermanApp.appContainer().queueDashboardTab();
            }
        };
    }

    protected StateExtractor<AgentStatusPanel> theAgentStatusPanel() {
        return new StateExtractor<AgentStatusPanel>() {
            @Override
            public AgentStatusPanel execute(CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                return supermanApp.appContainer().agentStatusPanel();
            }
        };
    }

    protected StateExtractor<MyQueuesPanel> theMyQueuesPanel() {
        return new StateExtractor<MyQueuesPanel>() {
            @Override
            public MyQueuesPanel execute(CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                return supermanApp.appContainer().myQueuesPanel();
            }
        };
    }

    protected ScenarioGivensBuilder scenarioGivensBuilderFor(DomainServiceProblem serviceProblem) {
        return new ScenarioGivensBuilder(new ServiceProblemScenario(scenarioDriver(), agentForTest, serviceProblem));
    }
}