package com.example.KinoLargo_api.models.dto;

import com.example.KinoLargo_api.models.entity.Projection;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {

    public Long id;
    private int row;
    private int number;
    private List<ProjectionDTO> projections;

}
