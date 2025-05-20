package com.week2.springbootwebtutorial.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.week2.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class EmployeeDTO {

  private   Long id;

  @NotEmpty(message = "Name of the employee cannot be blank")
  @Size(min = 3, max = 10 , message = "Number of characters in name should be in the range : [3,10]")
  private  String name;

    @NotEmpty(message = "Email of the employee cannot be blank")
  @Email(message = "Email should be a valid email ")
 private   String email;

    @NotNull(message = "Name of the employee cannot be blank")
  @Max(value = 80 , message = "Age cannot be greater than 80")
  @Min(value = 18 ,message = "Age cannot be less than 18")
 private   Integer age;
//
//  @NotBlank(message = "Role of the employee cannot be blank ")
//  @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can be USER or ADMIN")
    @EmployeeRoleValidation
  private String role ;//ADMIN | USER

 private   LocalDate dateOfJoining;
 @JsonProperty("isActive")
 private   Boolean isActive;



}
