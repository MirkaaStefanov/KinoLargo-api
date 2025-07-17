package com.example.KinoLargo_api.repositories;

import com.example.KinoLargo_api.models.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    
}
