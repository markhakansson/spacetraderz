package com.markhakansson.spacetraderz.app.service;

import com.markhakansson.spacetraderz.app.data.Agent;
import com.markhakansson.spacetraderz.app.repository.AgentRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

// Service layer can be used to abstract the domain logic,
// and also define set boundaries that the application logic
// has over the database operations.
@Service
public class AgentService {
  private AgentRepository repository;

  public List<Agent> findAll() {
    return this.repository.findAll();
  }

  public Optional<Agent> findById(UUID uuid) {
    return this.repository.findById(uuid);
  }

  public void save(Agent agent) {
    this.repository.save(agent);
  }

  public void deleteById(UUID uuid) {
    this.repository.deleteById(uuid);
  }
}
