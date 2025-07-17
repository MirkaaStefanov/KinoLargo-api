package com.example.KinoLargo_api.repositories;

import com.example.KinoLargo_api.models.entity.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, UUID> {
}
