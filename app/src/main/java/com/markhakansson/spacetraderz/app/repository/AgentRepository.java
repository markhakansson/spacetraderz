package com.markhakansson.spacetraderz.app.repository;

import com.markhakansson.spacetraderz.app.data.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgentRepository extends JpaRepository<Agent, UUID> {
}