package com.flick.app.services;

import com.flick.app.models.Movie;

import java.util.List;

public interface MovieService {
  Boolean saveMovie(Movie movie);

  Boolean updateMovieById(Integer movieId, Movie movie);

  Movie getMovieById(Integer movieId) throws Exception;

  Boolean deleteMovieById(Integer movieId);

  List<Movie> searchMovieByName(String movieName);
}
