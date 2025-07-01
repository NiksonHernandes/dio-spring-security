package com.dio.spring.security.repository;

import com.dio.spring.security.model.Role;
import com.dio.spring.security.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNome(RoleName nome);
}
