package edu.unimagdalena.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.demo.entities.ERole;
import edu.unimagdalena.demo.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
