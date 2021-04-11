package com.flick.app.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Getter
@Setter
@ToString
public class User {
  @Id
  private String userEmail;
  private String firstName;
  private String lastName;
  private String password;
  private Boolean userActive;
  private LocalDate userAddedOn;


}
