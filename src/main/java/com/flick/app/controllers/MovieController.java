package com.flick.app.controllers;

import com.flick.app.models.Movie;
import com.flick.app.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/movies")
public class MovieController {

  private MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @PostMapping()
  public ResponseEntity<?> saveMovieData(@RequestBody Movie movie) {
    ResponseEntity<?> responseEntity = null;
    try {
      responseEntity = ResponseEntity.status(201).body(movieService.saveMovie(movie));
    } catch (Exception exception) {
      responseEntity = ResponseEntity.status(409).body(exception.getMessage());
    }
    return responseEntity;
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<?> updateMovieById(@RequestBody Movie movie, @PathVariable("id") Integer id) {
    ResponseEntity<?> responseEntity = null;
    try {
      responseEntity = ResponseEntity.status(200).body(movieService.updateMovieById(id, movie));
    } catch (Exception exception) {
      responseEntity = ResponseEntity.status(409).body(exception.getMessage());
    }
    return responseEntity;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getMovieById(@PathVariable("id") Integer id) {
    ResponseEntity<?> responseEntity = null;
    try {
      responseEntity = ResponseEntity.status(200).body(movieService.getMovieById(id));
    } catch (Exception exception) {
      responseEntity = ResponseEntity.status(409).body(exception.getMessage());
    }
    return responseEntity;
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deleteMovieById(@PathVariable("id") Integer id) {
    ResponseEntity<?> responseEntity = null;
    try {
      responseEntity = ResponseEntity.status(200).body(movieService.deleteMovieById(id));
    } catch (Exception exception) {
      responseEntity = ResponseEntity.status(409).body(exception.getMessage());
    }
    return responseEntity;
  }
}
