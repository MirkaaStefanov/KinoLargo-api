package com.example.KinoLargo_api.controllers;

import com.example.KinoLargo_api.models.dto.GenreDTO;
import com.example.KinoLargo_api.models.dto.MovieDTO;
import com.example.KinoLargo_api.services.impl.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/save")
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO, @RequestHeader(value = "Authorization", required = false) String auth) {
        return ResponseEntity.ok(movieService.save(movieDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> findAll(@RequestHeader(value = "Authorization", required = false) String auth) {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String auth) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movieDTO, @RequestHeader(value = "Authorization", required = false) String auth) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(movieService.update(id, movieDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String auth) throws ChangeSetPersister.NotFoundException {
        movieService.delete(id);
        return ResponseEntity.ok("Genre has been deleted successfully.");
    }
}
