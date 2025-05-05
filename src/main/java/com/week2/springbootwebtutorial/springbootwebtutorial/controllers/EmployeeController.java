package com.week2.springbootwebtutorial.springbootwebtutorial.controllers;

import com.week2.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path ="/employees")
public class EmployeeController {
    @GetMapping(path = "/getMessage")
    public String getMessage(){
        return "Yusuf Saiyad";
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(23, LocalDate.of( 2025 , 2,1),"yusuf@gmail.com",employeeId,true,"Yusuf");
    }

    @GetMapping
    public  String getAllEmployees(@RequestParam  (required=false) Integer age,
                                   @RequestParam   (required=false) String sortBy){
        return "Hi Yusuf "+age+" "+sortBy;
    }
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeDTO.setId(10L);
        return employeeDTO;
    }
    @PutMapping
    public String updateEmployeeId(){
        return "Hello From Put";
    }

}
