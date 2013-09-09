package sonique.bango.store;

import sonique.bango.domain.Agent;
import sonique.bango.servlet.GTFOException;

import java.util.Map;

import static com.google.common.base.Functions.forMap;
import static com.google.common.collect.Maps.newHashMap;

public class AgentStore {

    private final Map<String, Agent> agentCodeToAgentMap = newHashMap();
    private final Map<String, Agent> sessionIdToAgentMap = newHashMap();

    public Agent agentFor(String sessionId) {
        Agent agent = forMap(sessionIdToAgentMap, null).apply(sessionId);
        if (agent == null) {
            throw new GTFOException(403, "No Agent!");
        }
        return agent;
    }

    public void login(String sessionId, String agentCode) {
        Agent agent = forMap(agentCodeToAgentMap, null).apply(agentCode);

        if (agent == null) {
            throw new GTFOException(403, "Unknown Agent!");
        }

        agent.login();

        sessionIdToAgentMap.put(sessionId, agent);
    }

    public void logout(String sessionId) {
        sessionIdToAgentMap.get(sessionId).logout();
        sessionIdToAgentMap.remove(sessionId);
    }

    public void registerAgent(Agent agent) {
        agentCodeToAgentMap.put(agent.agentCode(), agent);
    }
}