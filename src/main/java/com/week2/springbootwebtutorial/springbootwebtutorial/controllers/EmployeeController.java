package com.week2.springbootwebtutorial.springbootwebtutorial.controllers;


import com.week2.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.week2.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.week2.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path ="/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping(path = "/getMessage")
//    public String getMessage(){
//        return "Yusuf Saiyad";
//    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="employeeId")@Valid Long id){
     Optional <EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
       return  employeeDTO
               .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
               .orElseThrow(()-> new ResourceNotFoundException("Employee not found with id :"+id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam  (required=false) @Valid Integer age,
                                @RequestParam   (required=false) String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
         return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,
                                     @PathVariable Long employeeId){
        return ResponseEntity.ok( employeeService.updateEmployeeById(employeeId,employeeDTO));

    }
  @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable @Valid Long employeeId ) {
      boolean gotDeleted =  employeeService.deleteEmployeeById(employeeId);
      if(gotDeleted) return  ResponseEntity.ok(true);
      return ResponseEntity.notFound().build();
  }
  @PatchMapping(path = "{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String , Object> updates,
                                                 @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId,updates);
        if (employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
  }

}
