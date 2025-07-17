package com.example.KinoLargo_api.services.impl;

import com.example.KinoLargo_api.models.dto.MovieDTO;
import com.example.KinoLargo_api.models.entity.Movie;
import com.example.KinoLargo_api.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieDTO save(MovieDTO dto) {
        Movie entity = modelMapper.map(dto, Movie.class);
        if (dto.getImage() != null) {
            entity.setImageData(Base64.getDecoder().decode(dto.getImage()));
        }
        return modelMapper.map(movieRepository.save(entity), MovieDTO.class);
    }

    public List<MovieDTO> findAll() {
        List<Movie> items = movieRepository.findAll();

        return items.stream()
                .map(item -> {
                    MovieDTO dto = modelMapper.map(item, MovieDTO.class);
                    dto.setImage(item.getBase64Image());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public MovieDTO update(Long id, MovieDTO dto) {
        Movie existing = movieRepository.findById(id).orElseThrow();
        modelMapper.map(dto, existing);
        if (dto.getImage() != null) {
            existing.setImageData(Base64.getDecoder().decode(dto.getImage()));
        }
        return modelMapper.map(movieRepository.save(existing), MovieDTO.class);
    }

    public MovieDTO findById(Long id) {
        Movie item = movieRepository.findById(id).orElseThrow();
        MovieDTO dto = modelMapper.map(item, MovieDTO.class);
        dto.setImage(item.getBase64Image());
        return dto;
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

}
