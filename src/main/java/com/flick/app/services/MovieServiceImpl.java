package com.flick.app.services;

import com.flick.app.models.Movie;
import com.flick.app.models.UserMovie;
import com.flick.app.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;
  private final RestTemplate restTemplate;

  @Autowired
  public MovieServiceImpl(MovieRepository movieRepository, RestTemplate restTemplate) {
    this.movieRepository = movieRepository;
    this.restTemplate = restTemplate;
  }

  @Override
  public Boolean saveMovie(Movie movie) {
    boolean status = false;
    if (movieRepository.existsById(movie.getUserId())) {
      Optional<UserMovie> userMovieOptional = movieRepository.findById(movie.getUserId());
      if (userMovieOptional.isPresent()) {
        List<Movie> movieList = userMovieOptional.get().getMovieList();
        if (!movieList.contains(movie)) {
          movieList.add(movie);
          userMovieOptional.get().setLastModifiedOn(LocalDate.now());
          movieRepository.save(userMovieOptional.get());
          status = true;
        }
      } else {
        System.out.println("user movie list does not exist in db");
      }
    }
    return status;
  }

  @Override
  public Boolean updateMovieById(Integer movieId, Movie movie) {
    return null;
  }

  @Override
  public Movie getMovieById(Integer movieId) throws Exception {
    return null;
  }

  @Override
  public Boolean deleteMovieById(Integer movieId) {
    return null;
  }

  @Override
  public List<Movie> searchMovieByName(String movieName) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

    return (List<Movie>) restTemplate.exchange("", HttpMethod.GET, entity, Movie.class);
  }
}
