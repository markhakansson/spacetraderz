package com.markhakansson.spacetraderz.app.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/** A user's agent for SpaceTraders API. */
@Entity
@Table(name = "agents", schema = "game")
public class Agent {
  // TODO: use the received token from SpaceTraders API.
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "uuid", nullable = false, updatable = false)
  private UUID uuid;

  // TODO: set size constraints
  @Column(nullable = false)
  private String symbol;

  @Column(nullable = false)
  private String name;

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
