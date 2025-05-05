package com.week2.springbootwebtutorial.springbootwebtutorial.controllers;


import com.week2.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.week2.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path ="/employees")
public class EmployeeController {


    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/getMessage")
    public String getMessage(){
        return "Yusuf Saiyad";
    }

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name="employeeId") Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam  (required=false) Integer age,
                                @RequestParam   (required=false) String sortBy) {
        return employeeRepository.findAll();
    }
    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }



}
