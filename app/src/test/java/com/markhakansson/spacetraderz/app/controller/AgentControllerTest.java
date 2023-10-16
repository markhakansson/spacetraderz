package com.markhakansson.spacetraderz.app.controller;

import com.markhakansson.spacetraderz.app.data.Agent;
import com.markhakansson.spacetraderz.app.repository.AgentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AgentController.class)
public class AgentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AgentRepository repository;
    private static final String AGENTS_URI = "/api/v1/agents";

    @Test
    public void findAllAgentsWithoutDataReturnsOk() throws Exception {
        this.mockMvc.perform(get(AGENTS_URI))
                .andExpect(status().isOk());
    }

    @Test
    public void postEmptyRegistrationReturnsNotOk() throws Exception {
        this.mockMvc.perform(post(AGENTS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void postNewRegistrationReturnsOk() throws Exception {
        this.mockMvc.perform(post(AGENTS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Spaceman\", \"symbol\": \"SPACE\" }"))
                .andExpect(status().isOk());
    }

    private Agent createAgent(UUID uuid, String symbol, String name) {
        var agent = new Agent();
        agent.setUuid(uuid);
        agent.setSymbol(symbol);
        agent.setName(name);

        return agent;
    }

    @Test
    public void getAgentIfPresentReturnsOk() throws Exception {
        var uuid = UUID.fromString("id");
        var agent = createAgent(uuid, "SYMBOL", "NAME");

        when(this.repository.findById(uuid)).thenReturn(Optional.of(agent));

        this.mockMvc.perform(get("%s/%s".formatted(AGENTS_URI, uuid)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(
                                "{ 'id': '%s', 'symbol': '%s', 'name': '%s' }"
                                        .formatted(agent.getUuid(), agent.getSymbol(), agent.getName())
                        )
                );
    }
}

