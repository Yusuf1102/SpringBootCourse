package com.week2.springbootwebtutorial.springbootwebtutorial.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Department")
public class DepartmentEntity {
            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            private    Long departmentId;

           private   String  title;
            @JsonProperty("isActive")
           private   Boolean  isActive;
           private    LocalDate createdAt;

    public void setId(Long departmentId) {
    }
}
