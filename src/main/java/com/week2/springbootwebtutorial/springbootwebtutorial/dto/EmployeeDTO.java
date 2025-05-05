package com.week2.springbootwebtutorial.springbootwebtutorial.dto;

import java.time.LocalDate;

public class EmployeeDTO {
  private   Long id;
  private  String name;
 private   String email;
 private   Integer age;
 private   LocalDate dateOfJoining;
 private   Boolean isActive;


 public  EmployeeDTO(){

 }

    public EmployeeDTO(Integer age, LocalDate dateOfJoining, String email, Long id, Boolean isActive, String name) {
        this.age = age;
        this.dateOfJoining = dateOfJoining;
        this.email = email;
        this.id = id;
        this.isActive = isActive;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
