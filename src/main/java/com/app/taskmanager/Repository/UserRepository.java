package com.app.taskmanager.Repository;

import com.app.taskmanager.Models.Task;
import com.app.taskmanager.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    public Page<User> findAll(Pageable pageable);
}
