package com.flick.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Password {
  private String userEmail;
  private String currentPassword;
  private String newPassword;
  private String confirmPassword;
}
