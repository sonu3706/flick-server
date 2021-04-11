package com.flick.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserMovie {
  @Id
  private String userId;
  private List<Movie> movieList;
  private LocalDate createdOn;
  private LocalDate lastModifiedOn;
}
