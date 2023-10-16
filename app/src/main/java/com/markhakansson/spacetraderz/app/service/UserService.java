package com.markhakansson.spacetraderz.app.service;

import com.markhakansson.spacetraderz.app.data.User;
import com.markhakansson.spacetraderz.app.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public List<User> findAll() {
    return this.repository.findAll();
  }

  public Optional<User> findById(UUID uuid) {
    return this.repository.findById(uuid);
  }

  public void save(User user) {
    this.repository.save(user);
  }
}
