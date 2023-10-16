package com.markhakansson.spacetraderz.app.controller;

import com.markhakansson.spacetraderz.app.data.Agent;
import com.markhakansson.spacetraderz.app.data.Registration;
import com.markhakansson.spacetraderz.app.service.AgentService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {
  private final AgentService service;

  public AgentController(AgentService service) {
    this.service = service;
  }

  // TODO not so secure right now (anyone can fetch agents)
  @GetMapping
  public List<Agent> findAll() {
    return this.service.findAll();
  }

  @PostMapping
  public Agent create(@RequestBody Registration registration) {
    var newAgent = new Agent();
    newAgent.setUuid(UUID.randomUUID());
    newAgent.setSymbol(registration.symbol());
    newAgent.setName(registration.name());

    this.service.save(newAgent);

    return newAgent;
  }

  @PutMapping
  public Agent update(@RequestBody Agent agent) {
    var agentId = agent.getUuid();
    var saved = this.service.findById(agentId);

    if (saved.isEmpty()) {
      throw new NoSuchElementException("Agent with id %s does not exist".formatted(agentId));
    }

    var updated = new Agent();
    updated.setUuid(agent.getUuid());
    updated.setSymbol(agent.getSymbol());
    updated.setName(agent.getName());

    // For now, we just save the new agent, but we may want to
    // not override certain fields in the future.
    this.service.save(updated);

    return updated;
  }

  @GetMapping(path = "/{id}")
  public Agent findById(@PathVariable("id") UUID id) {
    return this.service
        .findById(id)
        .orElseThrow(() -> new NoSuchElementException("No agent with id %s found".formatted(id)));
  }

  @DeleteMapping(path = "/{id}")
  public void deleteById(@PathVariable("id") UUID id) {
    if (id != null) {
      this.service.deleteById(id);
    }
    ;
  }
}
