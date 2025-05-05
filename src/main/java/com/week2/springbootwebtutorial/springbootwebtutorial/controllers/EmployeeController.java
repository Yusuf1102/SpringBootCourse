package com.week2.springbootwebtutorial.springbootwebtutorial.controllers;


import com.week2.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.week2.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.week2.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.week2.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path ="/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/getMessage")
    public String getMessage(){
        return "Yusuf Saiyad";
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam  (required=false) Integer age,
                                @RequestParam   (required=false) String sortBy) {
        return employeeService.getAllEmployees();
    }
    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createEmployee(employeeDTO);
    }



}
