package com.example.KinoLargo_api.models.dto;

import com.example.KinoLargo_api.enums.MovieType;
import com.example.KinoLargo_api.models.entity.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Long id;

    private String name;

    private int durationMins;

    private String description;

    private MovieType movieType;

    private GenreDTO genre;

    private double price;

    private String image;
    @JsonIgnore
    private transient MultipartFile imageFile;
}
