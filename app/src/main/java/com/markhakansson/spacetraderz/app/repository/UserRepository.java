package com.markhakansson.spacetraderz.app.repository;

import com.markhakansson.spacetraderz.app.data.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
}
