package com.example.backend.Repository;

import java.util.Optional;

import com.example.backend.Model.ERole;
import com.example.backend.Model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepo extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByName(ERole name);
}
