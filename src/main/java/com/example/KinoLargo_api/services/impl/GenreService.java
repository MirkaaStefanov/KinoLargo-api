package com.example.KinoLargo_api.services.impl;

import com.example.KinoLargo_api.models.dto.GenreDTO;
import com.example.KinoLargo_api.models.entity.Genre;
import com.example.KinoLargo_api.repositories.GenreRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public GenreDTO save(GenreDTO genreDTO) {
        Genre genre = modelMapper.map(genreDTO, Genre.class);
        Genre savedGenre = genreRepository.save(genre);
        return modelMapper.map(savedGenre, GenreDTO.class);
    }

    public List<GenreDTO> findAll() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream()
                .map(region -> modelMapper.map(region, GenreDTO.class))
                .collect(Collectors.toList());
    }

    public GenreDTO findById(Long id) throws NotFoundException {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        return modelMapper.map(genre, GenreDTO.class);
    }

    public GenreDTO update(Long id, GenreDTO genreDTO) throws NotFoundException {
        Genre existingGenre = genreRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        Genre genreToUpdate = modelMapper.map(genreDTO, Genre.class);
        genreToUpdate.setId(id);
        Genre updatedGenre = genreRepository.save(genreToUpdate);
        return modelMapper.map(updatedGenre, GenreDTO.class);
    }

    public void delete(Long id) throws NotFoundException {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        genreRepository.delete(genre);
    }

}
