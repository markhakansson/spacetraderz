package com.markhakansson.spacetraderz.app.controller;

import com.markhakansson.spacetraderz.app.data.User;
import com.markhakansson.spacetraderz.app.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping
  public List<User> findAll() {
    return this.service.findAll();
  }

  @GetMapping(path = "/{id}")
  public User findById(@PathVariable("id") UUID uuid) {
    return this.service
        .findById(uuid)
        .orElseThrow(() -> new NoSuchElementException("No user with id %s found".formatted(uuid)));
  }

  @PostMapping
  public User create(@RequestBody User user) {
    var newUser = new User();
    newUser.setUuid(UUID.randomUUID());
    newUser.setUsername(user.getUsername());
    newUser.setPassword(user.getPassword());
    newUser.setEmailAddress(user.getEmailAddress());

    // save newUser

    return newUser;
  }
}
