package com.example.KinoLargo_api.models.dto;

import com.example.KinoLargo_api.models.entity.Movie;
import com.example.KinoLargo_api.models.entity.Seat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectionDTO {

    private UUID id;

    private Date date;

    private List<SeatDTO> seats;

    private MovieDTO movie;

}
