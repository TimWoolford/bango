package sonique.bango;

import com.google.common.collect.Lists;
import sky.sns.spm.domain.model.DomainAgent;
import sky.sns.spm.domain.model.serviceproblem.DomainServiceProblem;
import sky.sns.spm.interfaces.shared.PagedSearchResults;
import sonique.bango.driver.ScenarioDriver;
import sonique.bango.service.SearchApiService;
import spm.domain.ServiceProblemId;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class NoServiceProblemsScenario extends SupermanScenario {

    public NoServiceProblemsScenario(ScenarioDriver scenarioDriver, DomainAgent agent) {
        super(scenarioDriver, agent);
    }

    @Override
    public void bindScenario() {
        PagedSearchResults<DomainServiceProblem> emptyList = new PagedSearchResults<DomainServiceProblem>(Lists.<DomainServiceProblem>newArrayList(), 0L);

        SearchApiService searchApiService = scenarioDriver.searchApiServiceFor(agent);

        when(searchApiService.serviceProblemById(any(ServiceProblemId.class))).thenReturn(emptyList);
    }
}
