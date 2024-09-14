package org.example.ewatch.repository;

import org.example.ewatch.entity.Role;
import org.example.ewatch.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleType roleName);
}
