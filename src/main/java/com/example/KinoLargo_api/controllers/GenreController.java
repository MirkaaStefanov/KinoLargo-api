package com.example.KinoLargo_api.controllers;

import com.example.KinoLargo_api.models.dto.GenreDTO;
import com.example.KinoLargo_api.models.entity.Genre;
import com.example.KinoLargo_api.services.impl.GenreService;
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
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/save")
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genreDTO, @RequestHeader(value = "Authorization", required = false) String auth) {
        return ResponseEntity.ok(genreService.save(genreDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GenreDTO>> findAll(@RequestHeader(value = "Authorization", required = false) String auth) {
        return ResponseEntity.ok(genreService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<GenreDTO> findById(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String auth) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(genreService.findById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO genreDTO, @RequestHeader(value = "Authorization", required = false) String auth) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(genreService.update(id, genreDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String auth) throws ChangeSetPersister.NotFoundException {
        genreService.delete(id);
        return ResponseEntity.ok("Genre has been deleted successfully.");
    }
}
