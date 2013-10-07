package sonique.bango;

import sky.sns.spm.domain.model.DomainAgent;
import sonique.bango.driver.ScenarioDriver;

public abstract class SupermanScenario {
    protected final ScenarioDriver scenarioDriver;
    protected final DomainAgent agent;

    public SupermanScenario(DomainAgent agent, ScenarioDriver scenarioDriver) {
        this.agent = agent;
        this.scenarioDriver = scenarioDriver;
    }

    public abstract void bindScenario();
}