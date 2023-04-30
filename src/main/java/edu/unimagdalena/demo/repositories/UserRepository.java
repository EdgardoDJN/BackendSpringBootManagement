package edu.unimagdalena.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.demo.entidades.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    //Esto es pa que no nos genere el error
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
