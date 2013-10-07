package sonique.bango.store;

import sky.sns.spm.domain.model.AgentDashboardEntry;
import sky.sns.spm.domain.model.AgentState;
import sky.sns.spm.domain.model.DomainAgent;
import sky.sns.spm.infrastructure.repository.DomainAgentRepository;
import spm.domain.QueueId;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class AgentStore implements DomainAgentRepository {

    private final Map<String, DomainAgent> agentCodeToAgentMap = newHashMap();

    public void registerAgent(DomainAgent agent) {
        agentCodeToAgentMap.put(agentCode(agent), agent);
    }

    public void removeAgent(DomainAgent agent) {
        agentCodeToAgentMap.remove(agentCode(agent));
    }

    private String agentCode(DomainAgent agent) {
        return agent.getAgentCode().toUpperCase();
    }

    @Override
    public DomainAgent findByAgentCode(String agentCode) {
        return agentCodeToAgentMap.get(agentCode.toUpperCase());
    }

    @Override
    public AgentState getAgentState(DomainAgent agent) {
        throw new UnsupportedOperationException("Method AgentStore getAgentState() not yet implemented");
    }

    @Override
    public DomainAgent insert(DomainAgent agent) {
        throw new UnsupportedOperationException("Method AgentStore insert() not yet implemented");
    }

    @Override
    public List<DomainAgent> findAssignableAgents(QueueId queueId) {
        throw new UnsupportedOperationException("Method AgentStore findAssignableAgents() not yet implemented");
    }

    @Override
    public List<AgentDashboardEntry> getAgentDashboardEntries() {
        throw new UnsupportedOperationException("Method AgentStore getAgentDashboardEntries() not yet implemented");
    }

    @Override
    public List<DomainAgent> getAllAgents() {
        throw new UnsupportedOperationException("Method AgentStore getAllAgents() not yet implemented");
    }

    @Override
    public boolean agentExists(String agentCode) {
        throw new UnsupportedOperationException("Method AgentStore agentExists() not yet implemented");
    }

    @Override
    public void markAllAgentsOffline() {
        throw new UnsupportedOperationException("Method AgentStore markAllAgentsOffline() not yet implemented");
    }
}