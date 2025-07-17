package com.example.KinoLargo_api.models.dto;

import com.example.KinoLargo_api.enums.BookingType;
import com.example.KinoLargo_api.models.dto.auth.PublicUserDTO;
import com.example.KinoLargo_api.models.entity.Projection;
import com.example.KinoLargo_api.models.entity.Seat;
import com.example.KinoLargo_api.models.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    public Long id;

    private SeatDTO seat;

    private ProjectionDTO projection;

    private BookingType bookingType;

    private PublicUserDTO user;

}
