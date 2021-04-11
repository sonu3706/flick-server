package com.flick.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Movie {
  private String userId;
  private Integer movieId;
  private String movieTitle;
  private String addedBy;
  private String adultType;
  private String backDropPath;
  private String originalLanguage;
  private String originalTitle;
  private String title;
  private String overview;
  private Boolean video;

}
