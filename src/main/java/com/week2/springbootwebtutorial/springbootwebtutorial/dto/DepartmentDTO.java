package com.week2.springbootwebtutorial.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

        @NotNull(message = "Department ID cannot be null")
        private Long departmentId;

        @NotEmpty(message = "Title cannot be empty")
        @Size(min = 3, max = 10, message = "Number of characters in title should be in the range: [3,10]")
        private String title;

        @NotNull(message = "Active status must be specified")
        @JsonProperty("isActive")
        private Boolean isActive;

        @PastOrPresent(message = "Creation date cannot be in the future")
        private LocalDate createdAt;

    }


